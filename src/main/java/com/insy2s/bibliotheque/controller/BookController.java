package com.insy2s.bibliotheque.controller;

import com.insy2s.bibliotheque.domain.Book;
import com.insy2s.bibliotheque.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Void> post(@Valid @RequestBody Book book) {
        bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable long id, @Valid @RequestBody Book book) {
        bookService.updateBook(id, book);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailable() {
      return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks(true));
    }

    @GetMapping("/unavailable")
    public ResponseEntity<List<Book>> getLoan() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks(false));
    }
}
