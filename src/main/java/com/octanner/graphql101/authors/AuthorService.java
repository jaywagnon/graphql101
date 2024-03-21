package com.octanner.graphql101.authors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Optional<Author> getById(Integer id) {
        return authorRepository.findById(String.valueOf(id));
    }

    public Author save(Author author) {
        Author _author = author;

        if (_author.getId() != null) {
            Optional<Author> authorOptional = getById(author.getId());
            if (authorOptional.isPresent()) {
                _author = authorOptional.get();

                _author.setName(author.getName());
                _author.setBooks(author.getBooks());
            };
        }

        return authorRepository.save(_author);
    }
}
