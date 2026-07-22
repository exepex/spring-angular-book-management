package com.daniellaera.backend.controller;

import com.daniellaera.backend.dao.BookDTO;
import com.daniellaera.backend.dao.response.PageResponse;
import com.daniellaera.backend.model.User;
import com.daniellaera.backend.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("/api/v3/book")
@Slf4j
public class BookController {

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "id", "title", "description", "author", "isbn", "genre",
            "createdDate", "publishedDate", "averageRating", "isAvailable"
    );

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public PageResponse<BookDTO> getAllBooks(
            Pageable pageable,
            @RequestParam(required = false) String search
    ) {
        pageable.getSort().forEach(order -> {
            if (!ALLOWED_SORT_FIELDS.contains(order.getProperty())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Invalid sort field: " + order.getProperty());
            }
        });
        Page<BookDTO> page = bookService.getAllBooks(pageable, search);
        return PageResponse.of(page);
    }

    @GetMapping("{bookId}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Integer bookId) {
        // return bookService.findBookById(bookId)
        //        .map(ResponseEntity::ok)
        //        .orElseGet(() -> ResponseEntity.notFound().build());
        return bookService.findBookById(bookId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        // BookDTO book = bookService.findBookById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        // return ResponseEntity.ok().body(book);
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(
            @RequestBody BookDTO book,
            @AuthenticationPrincipal User currentUser // Get the logged-in user
    ) {

        if (currentUser.getEmail() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userEmail = currentUser.getEmail();

        BookDTO createdBook = bookService.createBook(book, userEmail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @DeleteMapping("{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer bookId, Authentication authentication) {

        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }
}
