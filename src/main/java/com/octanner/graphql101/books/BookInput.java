package com.octanner.graphql101.books;

import lombok.Data;

@Data
public class BookInput {
    private String name;
    private String genre;
    private Integer authorId;
}
