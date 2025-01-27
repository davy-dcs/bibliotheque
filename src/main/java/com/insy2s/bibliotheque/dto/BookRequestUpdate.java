package com.insy2s.bibliotheque.dto;

import java.util.UUID;

public record BookRequestUpdate(UUID uuid, String title, String author, boolean isAvailable) {
}
