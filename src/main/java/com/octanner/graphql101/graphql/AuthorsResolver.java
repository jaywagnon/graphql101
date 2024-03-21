package com.octanner.graphql101.graphql;

import com.octanner.graphql101.authors.Author;
import com.octanner.graphql101.authors.AuthorInput;
import com.octanner.graphql101.authors.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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

    @MutationMapping
    public Author addAuthor(@Argument AuthorInput input) {
        Author author = new Author();

        author.setName(input.getName());

        return authorRepository.save(author);
    }

    @MutationMapping
    public Author editAuthor(@Argument Integer id, @Argument AuthorInput input) {
        Author author = getAuthorById(id);

        author.setName(input.getName());

        return authorRepository.save(author);
    }
}
