package com.demo.bookstore_app.service;

import com.demo.bookstore_app.models.Book;
import com.demo.bookstore_app.repositories.BookRepository;
import com.demo.bookstore_app.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles({"test"})
public class BookServiceTest {
    @Autowired
    private BookService service;

    @Autowired
    private BookRepository repo;


    @Test
    public void testUpdateBook() throws Exception {
        Long bookId = 1L;
        Book existingBook = new Book();
        existingBook.setBookId(bookId);
        existingBook.setBookName("The Little Prince");

        Book updateDetails = new Book();
        updateDetails.setBookName("Vol de nuit");

        when(repo.findById(bookId)).thenReturn(Optional.of(existingBook));

        when(repo.save(any(Book.class))).thenAnswer(i -> i.getArguments() [0]);

        Book result = service.updateBook(bookId, updateDetails);

        assertNotNull(result);
        assertEquals("Vol de nuit", result.getBookName());

        verify(repo).findById(bookId);
    }
}
