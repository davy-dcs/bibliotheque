package com.insy2s.bibliotheque.dto;

import java.util.UUID;

public record BorrowingRequestPost(UUID user, UUID book) {
}
