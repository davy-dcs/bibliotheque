package com.insy2s.bibliotheque.repository;

import com.insy2s.bibliotheque.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    List<Book> findByIsAvailable(boolean isAvailable);
    Optional<Book> findByUuid(UUID uuid);
}
