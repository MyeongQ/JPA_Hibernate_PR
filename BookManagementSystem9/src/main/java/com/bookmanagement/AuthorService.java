package com.bookmanagement;

import com.bookmanagement.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AuthorService {
    private final Scanner input = new Scanner(System.in);
    private final EntityManager em;

    public AuthorService () {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookManagementSystem");
        em = entityManagerFactory.createEntityManager();
    }

    public Author getAuthorById(int id) {
        return em.find(Author.class, id);
    }

    public List<Author> getAuthorsByBookId(int bookId) {
        return em.createQuery("select a from Author a where a.book.id = :book_id", Author.class)
                .setParameter("book_id", bookId)
                .getResultList();
    }

    public void updateAuthorById(int id) {
        Author author = getAuthorById(id);
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.persist(author);
        System.out.println("New Name :");
        author.setName(input.nextLine());
        em.flush();
        tx.commit();
    }

    public void deleteAuthorById(int id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.find(Author.class, id));
        tx.commit();
    }

    public void deleteAuthorsByBookId(int bookId) {

        List<Author> authors = getAuthorsByBookId(bookId);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        if (!authors.isEmpty()) {
            authors.forEach(em::remove);
        }
        tx.commit();
    }
}
