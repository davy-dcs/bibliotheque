package com.insy2s.bibliotheque.service;

import com.insy2s.bibliotheque.domain.Book;
import com.insy2s.bibliotheque.domain.Borrowing;
import com.insy2s.bibliotheque.domain.User;
import com.insy2s.bibliotheque.exception.*;
import com.insy2s.bibliotheque.repository.IBookRepository;
import com.insy2s.bibliotheque.repository.IBorrowingRepository;
import com.insy2s.bibliotheque.repository.IUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BorrowingService {
    private final IBorrowingRepository borrowingRepository;
    private final IUserRepository userRepository;
    private final IBookRepository bookRepository;

    public void createBorrowing(long userId, long bookId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isEmpty()) {
            throw new BookNotFoundException("This book doesn't exist.");
        }

        userOptional.ifPresentOrElse(
                userFound -> {
                    if (borrowingRepository.findByUserAndReturnDateNull(userFound).size() < 3) {
                        bookOptional.ifPresentOrElse(
                                bookFound -> {
                                    borrowingRepository.findByBookAndReturnDateNull(bookFound).ifPresentOrElse(
                                            bookUnavailable -> {throw new BookUnavailableException("This book is unavailable.");},
                                            () -> {
                                                Borrowing borrowing = new Borrowing();
                                                borrowing.setBook(bookFound);
                                                borrowing.setUser(userFound);
                                                borrowingRepository.save(borrowing);
                                                bookFound.setAvailable(false);
                                                bookRepository.save(bookFound);
                                            }
                                    );
                                },
                                () -> {throw new BookNotFoundException("This book doesn't exist.");}
                        );
                    } else {
                        throw new BorrowingLimitExceededException("This user has 3 outstanding loans.");
                    }
                },
                () -> {throw new UserNotFoundException("This user doesn't exist.");}
        );
    }

    public void updateBorrowing(long userId, long bookId, @Valid Borrowing borrowing) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        userOptional.ifPresentOrElse(
                userFound -> {
                    bookOptional.ifPresentOrElse(
                            bookFound -> {
                                borrowingRepository.findByUserAndBookAndReturnDateNull(userFound, bookFound).ifPresentOrElse(
                                        borrowingFound -> {
                                            if (borrowing.getReturnDate() != null) {
                                                borrowingFound.setReturnDate(borrowing.getReturnDate());
                                                borrowingRepository.save(borrowingFound);
                                                bookFound.setAvailable(true);
                                                bookRepository.save(bookFound);
                                            }
                                        },
                                        () -> {throw new BorrowingNotFoundException("This borrowing wasn't found.");}
                                );
                            },
                            () -> {throw new BookNotFoundException("This book doesn't exist.");}
                    );
                },
                () -> {throw new UserNotFoundException("This user doesn't exist.");}
        );
    }

    public List<Borrowing> getAllBorrowingsByUser(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return borrowingRepository.findByUserAndReturnDateNull(userOptional.get());
        } else {
            throw new UserNotFoundException("This user doesn't exist.");
        }
    }
}
