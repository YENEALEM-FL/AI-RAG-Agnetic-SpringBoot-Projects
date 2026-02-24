package com.lab.springaimcpserverusingclaude.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Book {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;

    // Constructor with fields
    public Book(String title, String author, String isbn, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }

}