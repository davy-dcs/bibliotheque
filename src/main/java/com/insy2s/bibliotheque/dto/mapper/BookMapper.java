package com.insy2s.bibliotheque.dto.mapper;

import com.insy2s.bibliotheque.domain.Book;
import com.insy2s.bibliotheque.dto.BookRequestPost;
import com.insy2s.bibliotheque.dto.BookResponseGet;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {
    public static Book bookRequestPost(BookRequestPost brp) {
        Book book = new Book();
        book.setTitle(brp.title());
        book.setAuthor(brp.author());
        return book;
    }

    public static BookResponseGet bookResponseGet(Book book) {
        return new BookResponseGet(book.getUuid(), book.getTitle(), book.getAuthor(), book.isAvailable());
    }

    public static List<BookResponseGet> booksResponseGet(List<Book> books) {
        List<BookResponseGet> response = new ArrayList<>();
        for (Book book : books) {
            response.add(bookResponseGet(book));
        }
        return response;
    }

}
