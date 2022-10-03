package com.nisrinekane.mvcdemo.controllers;

import com.nisrinekane.mvcdemo.models.Book;
import com.nisrinekane.mvcdemo.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BooksAPI {
    private final BookService bookService;

    public BooksAPI(BookService bookService) {
        this.bookService = bookService;
    }

    //route to get all books
    @RequestMapping("api/books")
    public List<Book> index() {
        return bookService.allBooks();
    }

    //post method to create a book
    @PostMapping(value="/api/books")
    public Book create(@RequestParam(value="title") String title,
                       @RequestParam(value="description") String desc,
                       @RequestParam(value="language") String lang,
                       @RequestParam(value="pages") Integer numOfPages) {
        Book book = new Book(title, desc, lang, numOfPages);
        return bookService.createBook(book);
    }

    @PutMapping(value="/api/books/{id}")
    public Book update(
            @PathVariable("id") Long id,
            @RequestParam(value="title") String title,
            @RequestParam(value="description") String desc,
            @RequestParam(value="language") String lang,
            @RequestParam(value="pages") Integer numOfPages) {
        return bookService.updateBook(id, title, desc, lang, numOfPages);
    }

    @DeleteMapping(value="/api/books/{id}")
    public void destroy(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
}
