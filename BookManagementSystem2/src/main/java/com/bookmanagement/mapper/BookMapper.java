package com.bookmanagement.mapper;

import com.bookmanagement.model.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper {
    final String getAll = "SELECT * FROM BOOK";
    final String getById = "SELECT ID, TITLE, AUTHOR, GENRE, PAGECOUNT FROM BOOK WHERE ID = #{id}";
    final String deleteById = "DELETE from BOOK WHERE ID = #{id}";
    final String insert = "INSERT INTO BOOK (TITLE, AUTHOR, GENRE, PAGECOUNT ) VALUES (#{title}, #{author}, #{genre}, #{pagecount})";
    final String update = "UPDATE BOOK SET TITLE = #{title}, AUTHOR = #{author}, GENRE = #{genre}, PAGECOUNT = #{pagecount} WHERE ID = #{id}";

    @Select(getAll)
    List<Book> getAllBooks();

    //@MapKey("id")
    //Map<Integer, Book> getAllBooks();

    @Select(getById)
    Book getBook(int id);

    @Insert(insert)
    public void saveBook(Book book);

    @Delete(deleteById)
    public void deleteBook(int id);

    @Update(update)
    public void updateBook(Book book);
}
