package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    public List<Book> fetchBooks() {
        return bookRepository.findAll();
    }

    public void addBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            updatedBook.setId(id);
            return bookRepository.save(updatedBook);
        }
            throw new NoSuchElementException("No book found with id: " + id);
    }

    public void deleteBook(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            bookRepository.deleteById(id);
        }
        else {
            throw new NoSuchElementException("No book found with id: " + id);
        }
    }

    public List<Book> searchBooks(String title, String author, String genre, String isbn) {
        return bookRepository.search(title, author, genre, isbn);
    }
}
