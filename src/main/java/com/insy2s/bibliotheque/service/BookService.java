package com.insy2s.bibliotheque.service;

import com.insy2s.bibliotheque.domain.Book;
import com.insy2s.bibliotheque.dto.BookRequestPost;
import com.insy2s.bibliotheque.dto.BookRequestUpdate;
import com.insy2s.bibliotheque.dto.BookResponseGet;
import com.insy2s.bibliotheque.dto.mapper.BookMapper;
import com.insy2s.bibliotheque.exception.BookNotFoundException;
import com.insy2s.bibliotheque.repository.IBookRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookService {
    private final IBookRepository bookRepository;

    public Book getByUuid(UUID uuid) {
        Optional<Book> bookOptional = bookRepository.findByUuid(uuid);
        if (bookOptional.isPresent()) {
            return bookOptional.get();
        }
        throw new BookNotFoundException("Book not found");
    }

    public List<BookResponseGet> getAllBooks(boolean isAvailable) {
        return BookMapper.booksResponseGet(bookRepository.findByIsAvailable(isAvailable));
    }

    public void bookRequestPost(BookRequestPost brp) {
        createBook(BookMapper.bookRequestPost(brp));
    }

    private void createBook(@Valid Book book) {
        bookRepository.save(book);
    }

    public void bookRequestUpdate(BookRequestUpdate bru) {
        Book book = getByUuid(bru.uuid());
        if (bru.title() != null) {
            book.setTitle(bru.title());
        }
        if (bru.author() != null) {
            book.setAuthor(bru.author());
        }
        book.setAvailable(bru.isAvailable());
        bookRepository.save(book);
    }

    public void deleteBook(UUID uuid) {
        bookRepository.delete(getByUuid(uuid));
    }
}
