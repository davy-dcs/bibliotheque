package com.insy2s.bibliotheque.repository;

import com.insy2s.bibliotheque.domain.Book;
import com.insy2s.bibliotheque.domain.Borrowing;
import com.insy2s.bibliotheque.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBorrowingRepository extends JpaRepository<Borrowing, Long> {
    List<Borrowing> findByUserAndReturnDateNull(User user);
    Optional<Borrowing> findByBookAndReturnDateNull(Book book);
    Optional<Borrowing> findByUserAndBookAndReturnDateNull(User user, Book book);
}
