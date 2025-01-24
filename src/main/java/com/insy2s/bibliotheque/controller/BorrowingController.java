package com.insy2s.bibliotheque.controller;

import com.insy2s.bibliotheque.domain.Borrowing;
import com.insy2s.bibliotheque.service.BorrowingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/borrowings")
public class BorrowingController {
    private final BorrowingService borrowingService;

    @PostMapping("/{userId}/{bookId}")
    public ResponseEntity<Void> post(@PathVariable long userId, @PathVariable long bookId) {
        borrowingService.createBorrowing(userId, bookId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{userId}/{bookId}")
    public ResponseEntity<Void> put(@PathVariable long userId, @PathVariable long bookId, @Valid @RequestBody Borrowing borrowing) {
        borrowingService.updateBorrowing(userId, bookId, borrowing);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Borrowing>> getBorrowings(@PathVariable long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(borrowingService.getAllBorrowingsByUser(userId));
    }
}
