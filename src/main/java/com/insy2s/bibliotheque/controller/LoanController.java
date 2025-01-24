package com.insy2s.bibliotheque.controller;

import com.insy2s.bibliotheque.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/loans")
public class LoanController {
    /*@PostMapping("/{bookUuid}/{memberUuid}")
    public ResponseEntity<Void> post(@PathVariable UUID bookUuid, @PathVariable UUID memberUuid) {
        return LoanService.post(bookUuid, memberUuid) ?
                ResponseEntity.status(HttpStatus.CREATED).build() :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{bookUuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID bookUuid) {
        return LoanService.delete(bookUuid) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }*/
}
