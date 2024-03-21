package com.octanner.graphql101.graphql;

import com.octanner.graphql101.books.Book;
import com.octanner.graphql101.books.BookService;
import com.octanner.graphql101.books.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BooksResolver {

    private final BookService bookService;

    @QueryMapping
    public List<Book> getBooks() {
        return bookService.getAll();
    }

    @QueryMapping
    public Book getBookById(@Argument Integer id) { return bookService.getById(id).get(); }

    @QueryMapping
    public List<Book> getBooksByGenre(@Argument Genre genre) {
        return bookService.getAllByGenre(genre);
    }
}
