package com.insy2s.bibliotheque.service;

import com.insy2s.bibliotheque.domain.Book;
import com.insy2s.bibliotheque.domain.Borrowing;
import com.insy2s.bibliotheque.domain.User;
import com.insy2s.bibliotheque.dto.BookRequestUpdate;
import com.insy2s.bibliotheque.dto.BorrowingRequestPost;
import com.insy2s.bibliotheque.dto.BorrowingRequestUpdate;
import com.insy2s.bibliotheque.dto.BorrowingResponseGet;
import com.insy2s.bibliotheque.dto.mapper.BorrowingMapper;
import com.insy2s.bibliotheque.exception.*;
import com.insy2s.bibliotheque.repository.IBookRepository;
import com.insy2s.bibliotheque.repository.IBorrowingRepository;
import com.insy2s.bibliotheque.repository.IUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BorrowingService {
    private final IBorrowingRepository borrowingRepository;
    private final UserService userService;
    private final BookService bookService;


    public void createBorrowing(BorrowingRequestPost brp) {
        User u = userService.getByUuid(brp.user());
        Book b = bookService.getByUuid(brp.book());

        if (borrowingRepository.findByUserAndReturnDateNull(u).size() < 3) {
            borrowingRepository.findByBookAndReturnDateNull(b).ifPresentOrElse(
                bookUnavailable -> {throw new BookUnavailableException("This book is unavailable.");},
                    () -> {
                        Borrowing borrowing = new Borrowing();
                        borrowing.setBook(b);
                        borrowing.setUser(u);
                        borrowingRepository.save(borrowing);
                        BookRequestUpdate bru = new BookRequestUpdate(b.getUuid(), null, null, false);
                        bookService.bookRequestUpdate(bru);
                    }
            );
        } else {
            throw new BorrowingLimitExceededException("This user has 3 outstanding loans.");
        }
    }


    public void updateBorrowing(UUID user, UUID book, BorrowingRequestUpdate bru) {
        User u = userService.getByUuid(user);
        Book b = bookService.getByUuid(book);

        borrowingRepository.findByUserAndBookAndReturnDateNull(u, b).ifPresentOrElse(
            borrowingFound -> {
                    if (bru.returnDate() != null) {
                        borrowingFound.setReturnDate(bru.returnDate());
                        borrowingRepository.save(borrowingFound);
                        BookRequestUpdate bkru = new BookRequestUpdate(b.getUuid(), null, null, true);
                        bookService.bookRequestUpdate(bkru);
                    }
                },
                () -> {throw new BorrowingNotFoundException("This borrowing wasn't found.");}
        );

    }

    public List<BorrowingResponseGet> getAllBorrowingsByUser(UUID user) {
        User u = userService.getByUuid(user);
        return BorrowingMapper.borrowingsResponseGet(borrowingRepository.findByUserAndReturnDateNull(u));
    }
}
