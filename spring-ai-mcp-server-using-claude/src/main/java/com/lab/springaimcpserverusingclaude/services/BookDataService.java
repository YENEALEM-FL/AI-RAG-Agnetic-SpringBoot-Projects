package com.lab.springaimcpserverusingclaude.services;

import com.lab.springaimcpserverusingclaude.model.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookDataService {
    private final List<Book> bookData = new ArrayList<>();

    @Tool(name = "get_all_books", description = "Get all books for all authors")
    public List<Book> getAllBooks() {
        return bookData;
    }

    // method to get a books by its author
    @Tool(name="get_books_by_author", description="Get all books written by an author.")
    public List<Book> getBooksByAuthor(String author) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book book : bookData) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                booksByAuthor.add(book);
            }
        }
        return booksByAuthor;
    }

    @PostConstruct
    public void loadBookData() {
        bookData.add(new Book("Bright and dark", "Richard Wagner", null, 2015));
        bookData.add(new Book("The Killer", "Tom Wood", null, 2013));
        bookData.add(new Book("The lost girl", "D. H. Lawrence", null, 1920));
        bookData.add(new Book("Love and Life", "William H. Cosby", null, 2007));
        bookData.add(new Book("Vendetta", "Iris Johansen", null, 1999));}
}
