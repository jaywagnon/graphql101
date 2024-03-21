package com.octanner.graphql101.books;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> getById(Integer id) {
        return bookRepository.findById(String.valueOf(id));
    }

    public List<Book> getAllByGenre(Genre genre) {
        return bookRepository.findAllByGenre(genre.name());
    }

    public Book save(Book book) {
        Book _book = book;

        if (_book.getId() != null) {
            Optional<Book> bookOptional = getById(book.getId());
            if (bookOptional.isPresent()) {
                _book = bookOptional.get();

                _book.setName(book.getName());
                _book.setGenre(book.getGenre());
                _book.setAuthor(book.getAuthor());
            };
        }

        return bookRepository.save(_book);
    }
}
