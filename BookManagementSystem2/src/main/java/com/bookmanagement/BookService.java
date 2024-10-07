package com.bookmanagement;

import com.bookmanagement.model.Book;

import java.util.List;
import java.util.Scanner;

public class BookService {
    private final Scanner input = new Scanner(System.in);


    // Method to register book:
    public void saveBook() {
        System.out.println("Title: ");
        String title = input.nextLine();

        System.out.println("Author: ");
        String author = input.nextLine();

        System.out.println("Genre: ");
        String genre = input.nextLine();

        System.out.println("Page Count: ");
        int pageCount = input.nextInt();

        input.nextLine(); // consume new line

        // After getting all data from the user we create a new object using constructor
        Book newBook = new Book(0, title, author, genre, pageCount);

//        bookMapper.saveBook(newBook);
    }

    // bring all listed books
    public List<Book> getAllBooks() {
//        return bookMapper.getAllBooks();
        return null;
    }

    // find book by id
    public Book getBookByID(int id) {
//        Book book = bookMapper.getBook(id);
//        if (book == null) {
//            System.out.println("There is no such a book with this id: " + id);
//        }
//        return book;
        return null;
    }

    // method to delete book by id:
    public void deleteBookByID(int id) {
//        bookMapper.deleteBook(id);
    }

    // method to update:
    public void updateBook(int id) {
        // find book by id from the table:
        Book existBook = getBookByID(id);

        if (existBook == null) {
            System.out.println("Book with ID: " + id + "is not found.");
        } else {
            // Ask all data from the user:
            System.out.println("Title: ");
            String title = input.nextLine();

            System.out.println("Author: ");
            String author = input.nextLine();

            System.out.println("Genre: ");
            String genre = input.nextLine();

            System.out.println("Page Count: ");
            int pagecount = input.nextInt();

            // Start updating existing book:
            existBook.setTitle(title);
            existBook.setAuthor(author);
            existBook.setGenre(genre);
            existBook.setPagecount(pagecount);

            // We can leave id unchanged.
//            bookMapper.updateBook(existBook);
        }
    }
}
