package com.octanner.graphql101.graphql;

import com.octanner.graphql101.authors.Author;
import com.octanner.graphql101.authors.AuthorInput;
import com.octanner.graphql101.authors.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthorsResolver {

    private final AuthorService authorService;

    @QueryMapping
    public List<Author> getAuthors() {
        return authorService.getAll();
    }

    @QueryMapping
    public Author getAuthorById(@Argument Integer id) { return authorService.getById(id).get(); }

    @MutationMapping
    public Author addAuthor(@Argument AuthorInput input) {
        Author author = new Author();

        author.setName(input.getName());

        return authorService.save(author);
    }

    @MutationMapping
    public Author editAuthor(@Argument Integer id, @Argument AuthorInput input) {
        Author author = getAuthorById(id);

        author.setName(input.getName());

        return authorService.save(author);
    }
}
