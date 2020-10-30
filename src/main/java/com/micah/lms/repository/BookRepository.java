package com.micah.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.micah.lms.entity.Book;


@Transactional
public interface BookRepository extends CrudRepository<Book, Integer> {
	@Transactional
	List<Book> findByBookName(String bookName);

	@Query("SELECT b FROM Book b WHERE isbn=:isbn")
	List<Book> findByISBN(@Param("isbn") String isbn);
}