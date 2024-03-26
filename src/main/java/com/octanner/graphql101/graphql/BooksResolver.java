package com.octanner.graphql101.graphql;

import com.octanner.graphql101.authors.AuthorRepository;
import com.octanner.graphql101.books.Book;
import com.octanner.graphql101.books.BookInput;
import com.octanner.graphql101.books.BookService;
import com.octanner.graphql101.books.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BooksResolver {

    private final BookService bookService;

    private final AuthorsResolver authorsResolver;

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

    @MutationMapping
    public Book addBook(@Argument BookInput input) {
        Book Book = new Book();

        Book.setName(input.getName());
        Book.setGenre(input.getGenre());
        Book.setAuthor(authorsResolver.getAuthorById(input.getAuthorId()));

        return bookService.save(Book);
    }

    @MutationMapping
    public Book editBook(@Argument Integer id, @Argument BookInput input) {
        Book Book = getBookById(id);

        Book.setName(input.getName());
        Book.setGenre(input.getGenre());
        Book.setAuthor(authorsResolver.getAuthorById(input.getAuthorId()));

        return bookService.save(Book);
    }
}
