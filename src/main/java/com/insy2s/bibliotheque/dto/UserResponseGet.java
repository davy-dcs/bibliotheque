package com.insy2s.bibliotheque.dto;

import java.util.UUID;

public record UserResponseGet (UUID uuid, String name, String email) {
}
