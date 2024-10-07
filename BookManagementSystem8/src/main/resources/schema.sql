CREATE TABLE IF NOT EXISTS BOOK
(
    id SERIAL,
    title VARCHAR(255),
    genre VARCHAR(128),
    pagecount INT
);

CREATE TABLE IF NOT EXISTS AUTHOR
 (
    id SERIAL,
    name VARCHAR(255),
    book_id INT
 );
--INSERT INTO BOOK (title, author, genre, pagecount) VALUES ('The C Programming Language', 'K&R', 'CS', 218);