package com.insy2s.bibliotheque.service;

import com.insy2s.bibliotheque.controller.BookController;
import com.insy2s.bibliotheque.domain.Book;
import com.insy2s.bibliotheque.domain.Member;
import lombok.Getter;

import java.util.HashMap;
import java.util.UUID;

public class LoanService {
    @Getter private static final HashMap<Book, Member> loans = new HashMap<>();
/*
    public static boolean post(UUID bookUuid, UUID memberUuid) {
        if (BookController.books.getByUuid(bookUuid) != null && MemberService.getByUuid(memberUuid) != null) {
            if (loans.containsKey(BookController.books.getByUuid(bookUuid))) {
                return false;
            } else {
                loans.put(BookController.books.getByUuid(bookUuid), MemberService.getByUuid(memberUuid));
                return true;
            }
        }
        return false;
    }

    public static boolean delete(UUID bookUuid) {
        if (loans.containsKey(BookController.books.getByUuid(bookUuid))) {
            loans.remove(BookController.books.getByUuid(bookUuid));
            return true;
        }
        return false;
    }*/
}
