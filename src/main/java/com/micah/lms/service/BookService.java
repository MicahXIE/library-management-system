package com.micah.lms.service;

import java.util.List;

import com.micah.lms.entity.Book;


public interface BookService {
	
	public void addBook(Book book);
	
	public Book getBook(Integer bookId);
	
	public Book updateBook(Integer bookId, Book book);

	public void deleteBook(Integer bookId);

	public List<Book> getAllBooks();

	public List<Book> findByBookName(String bookName);

	public List<Book> findByISBN(String isbn);
}
