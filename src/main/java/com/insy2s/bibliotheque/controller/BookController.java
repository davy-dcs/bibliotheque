package com.insy2s.bibliotheque.controller;

import com.insy2s.bibliotheque.domain.Book;
import com.insy2s.bibliotheque.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {
    private final ArrayList<Book> books = new ArrayList<>();
    //public static final BookService books = new BookService();

    @GetMapping
    public ResponseEntity<ArrayList<Book>> get() {
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody Book book) {
        return books.add(book) ?
                ResponseEntity.status(HttpStatus.CREATED).build() :
                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
/*
    @GetMapping("/{uuid}")
    public ResponseEntity<Book> getByUuid(@PathVariable UUID uuid) {
        if (books.getByUuid(uuid) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(books.getByUuid(uuid));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Void> put(@PathVariable UUID uuid, @RequestBody Book book) {
        return books.put(uuid, book) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        return books.delete(uuid) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }*/
}
