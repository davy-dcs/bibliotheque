package com.insy2s.bibliotheque.dto;

import java.util.UUID;

public record BookResponseGet(UUID uuid, String title, String author, boolean isAvailable) {
}
