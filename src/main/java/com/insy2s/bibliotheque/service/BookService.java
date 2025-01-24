package com.insy2s.bibliotheque.service;

import com.insy2s.bibliotheque.domain.Author;
import com.insy2s.bibliotheque.domain.Book;
import lombok.Getter;

import java.util.ArrayList;
import java.util.UUID;

public class BookService {
    @Getter private final ArrayList<Book> books = new ArrayList<>();

    public boolean add(Book book) {
        return books.add(book);
    }

    public Book getByUuid(UUID uuid) {
        for (Book book : books) {
            if (book.getUuid().equals(uuid)) {
                return book;
            }
        }
        return null;
    }

    public boolean put(UUID uuid, Book book) {
        for (Book b : books) {
            if (b.getUuid().equals(uuid)) {
                if (book.getTitle() != null) {
                    b.setTitle(book.getTitle());
                }
                if (book.getAuteur() != null) {
                    b.setAuteur(book.getAuteur());
                }
                if (book.getYear() != null) {
                    b.setYear(book.getYear());
                }
                return true;
            }
        }
        return false;
    }

    public boolean delete(UUID uuid) {
        return books.removeIf(book -> book.getUuid().equals(uuid));
    }
}
