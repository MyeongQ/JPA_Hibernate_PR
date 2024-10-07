package com.bookmanagement.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BOOK")
public class Book {
    // Create a POJO

    // field to be created : id, title, author, genre, pageCount
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Author> authors;
    @Column(name = "genre")
    private String genre;
    @Column(name = "pagecount")
    private int pagecount;

    public Book() {
    }

    // constructors with parameters
    public Book(String title, Set<Author> authors, String genre, int pagecount) {
        this.title = title;
        this.authors = authors;
        this.genre = genre;
        this.pagecount = pagecount;
    }

    // getter &  setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthor() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    // .toString method
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + authors + '\'' +
                ", genre='" + genre + '\'' +
                ", pagecount=" + pagecount +
                '}';
    }
}
