package com.insy2s.bibliotheque.controller;

import com.insy2s.bibliotheque.domain.Borrowing;
import com.insy2s.bibliotheque.dto.BorrowingRequestUpdate;
import com.insy2s.bibliotheque.service.BorrowingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/borrowings")
public class BorrowingController {
    private final BorrowingService borrowingService;

    @PostMapping("/{user}/{book}")
    public ResponseEntity<Void> post(@PathVariable UUID user, @PathVariable UUID book) {
        borrowingService.createBorrowing(user, book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{user}/{book}")
    public ResponseEntity<Void> put(@PathVariable UUID user, @PathVariable UUID book, @RequestBody BorrowingRequestUpdate bru) {
        borrowingService.updateBorrowing(user, book, bru);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Borrowing>> getBorrowings(@PathVariable UUID user) {
        return ResponseEntity.status(HttpStatus.OK).body(borrowingService.getAllBorrowingsByUser(user));
    }
}
