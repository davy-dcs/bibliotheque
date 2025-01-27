package com.insy2s.bibliotheque.dto.mapper;

import com.insy2s.bibliotheque.domain.Borrowing;
import com.insy2s.bibliotheque.dto.BorrowingResponseGet;

import java.util.ArrayList;
import java.util.List;

public class BorrowingMapper {
    public static BorrowingResponseGet borrowingResponseGet(Borrowing borrowing) {
        return new BorrowingResponseGet(borrowing.getBook().getTitle(), borrowing.getBook().getAuthor(), borrowing.getBorrowDate());
    }
    public static List<BorrowingResponseGet> borrowingsResponseGet(List<Borrowing> borrowings) {
        List<BorrowingResponseGet> response = new ArrayList<>();
        for (Borrowing borrowing : borrowings) {
            response.add(borrowingResponseGet(borrowing));
        }
        return response;
    }
}
