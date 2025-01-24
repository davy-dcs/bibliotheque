package com.insy2s.bibliotheque.repository;

import com.insy2s.bibliotheque.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    List<Book> findByIsAvailable(boolean isAvailable);
}
