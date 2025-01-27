package com.insy2s.bibliotheque.dto;

import java.time.LocalDate;

public record BorrowingResponseGet(String title, String author, LocalDate borrowDate) {
}
