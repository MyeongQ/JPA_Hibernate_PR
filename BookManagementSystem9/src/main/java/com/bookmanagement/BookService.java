package com.bookmanagement;

import com.bookmanagement.model.Author;
import com.bookmanagement.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.*;

public class BookService {
    private final Scanner input = new Scanner(System.in);
    private final EntityManager em;

    public BookService() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookManagementSystem");
        em = entityManagerFactory.createEntityManager();
    }

    // Method to register book:
    public Book saveBook() {
        System.out.println("Title: ");
        String title = input.nextLine();

        // 저자 등록
        Set<Author> authors = new HashSet<>();
        while(authors.size() < 5) {
            System.out.printf("Author (up to 5, %d/5)%n", authors.size()+1);
            String authorName = input.nextLine();
            if (authorName.isEmpty()) {
                break;
            }
            authors.add(new Author(authorName));
        }

        System.out.println("Genre: ");
        String genre = input.nextLine();

        System.out.println("Page Count: ");
        int pageCount = input.nextInt();

        input.nextLine(); // consume new line

        // After getting all data from the user we create a new object using constructor

        // transaction 생성
        EntityTransaction tx = em.getTransaction();
        // transaction 시작
        tx.begin();

        // Book 객체 생성
        Book newBook = new Book(title, new HashSet<>(), genre, pageCount);
        em.persist(newBook);

        // Author 저장
        if (!authors.isEmpty()) {
            authors.forEach(author -> {
                author.setBook(newBook);
                em.persist(author);
            });
        }

        // transaction 커밋
        tx.commit();
        return newBook;
    }

    // bring all listed books
    public List<Book> getAllBooks() {
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }

    // find book by id
    public Book getBookByID(int id) {
        return em.find(Book.class, id);
    }

    // method to delete book by id:
    public void deleteBookByID(int id) {
        // transaction 생성
        EntityTransaction tx = em.getTransaction();
        // transaction 시작
        tx.begin();

        Book book = em.find(Book.class, id);
        // 만약 book이 존재하면 remove
        if(book != null) {
            // 기존 저자 삭제
            List<Author> existAuthors = em.createQuery("select a from Author a where a.book.id = :book_id", Author.class)
                    .setParameter("book_id", book.getId())
                    .getResultList();

            if (!existAuthors.isEmpty()) {
                existAuthors.forEach(em::remove);
            }

            em.remove(book);
        }
        tx.commit();
    }

    // method to update:
    public void updateBook(int id) {
        // find book by id from the table:
        // transaction 생성
        EntityTransaction tx = em.getTransaction();
        // transaction 시작
        tx.begin();

        Book existBook = getBookByID(id);

        if (existBook == null) {
            System.out.println("Book with ID: " + id + "is not found.");
        } else {
            // Ask all data from the user:
            System.out.println("Title: ");
            String title = input.nextLine();

            em.persist(existBook);

            // 기존 저자 삭제
            List<Author> existAuthors = em.createQuery("select a from Author a where a.book.id = :book_id", Author.class)
                    .setParameter("book_id", existBook.getId())
                    .getResultList();

            if (!existAuthors.isEmpty()) {
                existAuthors.forEach(em::remove);
            }

            // 저자 등록
            Set<Author> authors = new HashSet<>();
            while(authors.size() < 5) {
                System.out.printf("Author (up to 5, %d/5)%n", authors.size()+1);
                String authorName = input.nextLine();
                if (authorName.isEmpty()) {
                    break;
                }
                authors.add(new Author(authorName));
            }

            System.out.println("Genre: ");
            String genre = input.nextLine();

            System.out.println("Page Count: ");
            int pagecount = input.nextInt();

            // Start updating existing book:
            existBook.setTitle(title);
            existBook.setGenre(genre);
            existBook.setPagecount(pagecount);

            // Author 저장
            if (!authors.isEmpty()) {
                authors.forEach(author -> {
                    author.setBook(existBook);
                    em.persist(author);
                });
            }

            // We can leave id unchanged.
//            bookMapper.updateBook(existBook);
            // 변경 반영
            em.flush();
            tx.commit();


        }
    }
}
