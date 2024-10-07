package com.bookmanagement;

import com.bookmanagement.model.Author;
import com.bookmanagement.model.Book;

import javax.persistence.EntityTransaction;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.io.InputStream;
import java.util.Set;

public class Runner {
/*
MiniProject: Book Management System
    1. Create Book Management System that can be used by any library or book store
    2. User (Admin) can: CRUD operations
        - register book (id, title, author, genre, pageCount fields)
        - list/display books
        - update book by id
        - delete book by id
*/

// 1. Create menu for the app
// 2. Create book class (entity)
// 3. Create methods for books
// 4. Create class to connect database

    public static void main(String[] args) throws IOException {
        // Create a SqlSessionFactory
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        start();
    }

    public static void start () {
        Scanner input = new Scanner(System.in);

        // Create an instance of service class
        BookService bookService = new BookService();
        AuthorService authorService = new AuthorService();

        int select;

        do {
            System.out.println("------------------------");
            System.out.println("--- Book Admin Panel ---");
            System.out.println("1- Register Book");
            System.out.println("2- List All Books");
            System.out.println("3- Delete Book By ID");
            System.out.println("4- Update Book");
            System.out.println("5- Find Book By ID");
            System.out.println("6- Find Authors By ID");
            System.out.println("7- Find Authors By BookID");
            System.out.println("8- Update Author");
            System.out.println("9- Delete Authors By ID");
            System.out.println("0- Exit ");
            System.out.println("Please Select Your Activity ");
            select = input.nextInt();
            input.nextLine(); // consume new line

            int id;

            switch (select){
                case 1:
                    bookService.saveBook();
                    break;
                case 2:
                    List<Book> books = bookService.getAllBooks();
                    for (Book book: books) {
                        book.setAuthors(Set.copyOf(authorService.getAuthorsByBookId(book.getId())));
                        System.out.println(book);
                    }
                    System.out.println("Books are listed");
                    break;
                case 3:
                    id = getBookID(input);
                    // author 먼저 삭제
                    authorService.deleteAuthorsByBookId(id);
                    bookService.deleteBookByID(id);
                    break;
                case 4:
                    id = getBookID(input);
                    bookService.updateBook(id);
                    break;
                case 5:
                    id = getBookID(input);
                    Book book = bookService.getBookByID(id);
                    System.out.println(book);
                    break;
                case 6:
                    id = getAuthorID(input);
                    System.out.println("author = " + authorService.getAuthorById(id));
                    break;
                case 7:
                    id = getBookID(input);
                    List<Author> authors = authorService.getAuthorsByBookId(id);
                    if (!authors.isEmpty()) {
                        authors.forEach(author -> {
                            System.out.println("author = " + author);
                        });
                    }
                    else {
                        System.out.println("There's no Authors");
                    }
                    break;
                case 8:
                    id = getAuthorID(input);
                    authorService.updateAuthorById(id);
                case 9:
                    id = getAuthorID(input);
                    authorService.deleteAuthorById(id);
                case 0:
                    System.out.println("Thank You For Using the App");
                    break;
                default:
                    System.out.println("Please enter a number between 0 and 5");
                    break;
            }
        } while (select != 0);
    }

    private static int getBookID (Scanner input){
        System.out.println("Please Enter Book ID: ");
        int id = input.nextInt();
        input.nextLine();

        return id;
    }

    private static int getAuthorID (Scanner input){
        System.out.println("Please Enter Author ID: ");
        int id = input.nextInt();
        input.nextLine();

        return id;
    }
}
