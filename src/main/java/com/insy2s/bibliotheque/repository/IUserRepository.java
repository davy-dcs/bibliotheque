package com.insy2s.bibliotheque.repository;

import com.insy2s.bibliotheque.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUuid(UUID uuid);
}
