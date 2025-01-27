package com.insy2s.bibliotheque.controller;

import com.insy2s.bibliotheque.dto.BookRequestPost;
import com.insy2s.bibliotheque.dto.BookRequestUpdate;
import com.insy2s.bibliotheque.dto.BookResponseGet;
import com.insy2s.bibliotheque.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping("/available")
    public ResponseEntity<List<BookResponseGet>> getAvailable() {
      return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks(true));
    }

    @GetMapping("/unavailable")
    public ResponseEntity<List<BookResponseGet>> getLoan() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks(false));
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody BookRequestPost brp) {
        bookService.bookRequestPost(brp);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{book}")
    public ResponseEntity<Void> put(@RequestBody BookRequestUpdate bru) {
        bookService.bookRequestUpdate(bru);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{book}")
    public ResponseEntity<Void> delete(@PathVariable UUID book){
        bookService.deleteBook(book);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
