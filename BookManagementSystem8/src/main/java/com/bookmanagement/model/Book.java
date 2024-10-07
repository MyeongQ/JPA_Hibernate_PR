package com.bookmanagement.model;

import javax.persistence.*;

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
    @Column(name = "author")
    private String author;
    @Column(name = "genre")
    private String genre;
    @Column(name = "pagecount")
    private int pagecount;

    public Book() {
    }

    // constructors with parameters
    public Book(String title, String author, String genre, int pagecount) {
        this.title = title;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    // .toString method
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", pagecount=" + pagecount +
                '}';
    }
}
