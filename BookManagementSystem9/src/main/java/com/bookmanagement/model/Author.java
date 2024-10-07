package com.bookmanagement.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "AUTHOR")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @ManyToOne
    @JoinColumn(name = "book_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Book book;

    public Author() {}
    public Author(String name) {
        this.name = name;
    }
    public Author(String name, Book book) {
        this.name = name;
        this.book = book;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", book='" + book.getId() + '\'' +
                '}';
    }
}
