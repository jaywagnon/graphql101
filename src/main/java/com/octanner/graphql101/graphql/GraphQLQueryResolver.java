package com.octanner.graphql101.graphql;

import com.octanner.graphql101.authors.Author;
import com.octanner.graphql101.authors.AuthorRepository;
import com.octanner.graphql101.books.Book;
import com.octanner.graphql101.books.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GraphQLQueryResolver {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @QueryMapping
    public Boolean healthCheck() {
        return true;
    }

    @QueryMapping
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @QueryMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
