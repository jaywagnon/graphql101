package com.octanner.graphql101.graphql;

import com.octanner.graphql101.authors.Author;
import com.octanner.graphql101.authors.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorsResolver {

    private final AuthorRepository authorRepository;

    @QueryMapping
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @QueryMapping
    public Author getAuthorById(@Argument Integer id) {
        return authorRepository.findById(String.valueOf(id)).get();
    }
}
