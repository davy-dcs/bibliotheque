package com.insy2s.bibliotheque.service;

import com.insy2s.bibliotheque.domain.Book;
import com.insy2s.bibliotheque.exception.BookNotFoundException;
import com.insy2s.bibliotheque.repository.IBookRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService {
    private final IBookRepository bookRepository;

    public List<Book> getAllBooks(boolean isAvailable) {
        return bookRepository.findByIsAvailable(isAvailable);
    }


    public void createBook(@Valid Book book) {
        bookRepository.save(book);
    }

    public void updateBook(long id, @Valid Book book) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        bookOptional.ifPresentOrElse(
                bookFound -> {
                    if (book.getAuthor() != null) {
                        bookFound.setAuthor(book.getAuthor());
                    }
                    if (book.getTitle() != null) {
                        bookFound.setTitle(book.getTitle());
                    }
                    bookRepository.save(bookFound);
                },
                () -> {throw new BookNotFoundException("This book doesn't exist");}
        );
    }

    public void deleteBook(long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        bookOptional.ifPresentOrElse(
                bookRepository::delete,
                () -> {throw new BookNotFoundException("This book doesn't exist");}
        );
    }
}
