package com.nisrinekane.mvcdemo.services;

import com.nisrinekane.mvcdemo.models.Book;
import com.nisrinekane.mvcdemo.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    // adding the book repository as a dependency
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    // returns all the books
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    // creates a book
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    // deletes a book
    public Book deleteBook(long id) {
        bookRepository.deleteById(id);
        return null;
    }

    // retrieves a book
    public Book findBook(Long id) {
        //optional: could exist or not
        Optional<Book> optionalBook = bookRepository.findById(id);
        //old version:
//        if(optionalBook.isPresent()) {
//            return optionalBook.get();
//        } else {
//            return null;
//        }
        //new version:
        return optionalBook.orElse(null);
    }

    public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
        Book book = findBook(id);
        book.setTitle(title);
        book.setDescription(desc);
        book.setNumberOfPages(numOfPages);
        book.setLanguage(lang);
        return bookRepository.save(book);
    }
}
