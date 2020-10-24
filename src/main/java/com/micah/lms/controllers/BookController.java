package com.micah.lms.controllers;


import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.micah.lms.entity.Book;
import com.micah.lms.service.BookServiceImpl;


@RestController
public class BookController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private BookServiceImpl bookServiceImpl;

	@RequestMapping(value="/api/books", method=RequestMethod.GET, headers="Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getAllBooks() {
        List<Book> bookList = bookServiceImpl.getAllBooks();
        List<JSONObject> books = new ArrayList<JSONObject>();

	    for (Book b : bookList) {
	        JSONObject book = new JSONObject();
	        book.put("id", b.getId());
	        book.put("ISBN", b.getIsbn());
	        book.put("bookName", b.getBookName());
	        book.put("author", b.getAuthor());
	        book.put("publishDate", b.getPublishDate());
	        book.put("summary", b.getSummary());
	        book.put("status", b.getAvailable());
	        
	        books.add(book);
	    }

        return new ResponseEntity<Object>(books, HttpStatus.OK);
    }

	@RequestMapping(value="/api/addBook", method=RequestMethod.POST, headers="Accept=application/json")
    @ResponseBody
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        bookServiceImpl.addBook(book);
        HttpHeaders header=new HttpHeaders();

        return new ResponseEntity<Book>(header, HttpStatus.CREATED);
    }

	@RequestMapping(value="/api/updateBook/{id}", method=RequestMethod.POST, headers="Accept=application/json")
    @ResponseBody
    public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book) {
        bookServiceImpl.updateBook(id, book);
        HttpHeaders header=new HttpHeaders();

        return new ResponseEntity<Book>(header, HttpStatus.OK);
    }

	@RequestMapping(value="/api/deleteBook/{id}", method=RequestMethod.POST, headers="Accept=application/json")
    @ResponseBody
    public ResponseEntity<Book> deleteBook(@PathVariable("id") int id) {
        bookServiceImpl.deleteBook(id);
        HttpHeaders header=new HttpHeaders();

        return new ResponseEntity<Book>(header, HttpStatus.OK);
    }

	@RequestMapping(value="/api/book/searchByName/{name}", method=RequestMethod.GET, headers="Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> searchBookByName(@PathVariable("name") String name) {
        List<Book> bookList = bookServiceImpl.findByBookName(name);
        List<JSONObject> books = new ArrayList<JSONObject>();

	    for (Book b : bookList) {
	        JSONObject book = new JSONObject();
	        book.put("id", b.getId());
	        book.put("ISBN", b.getIsbn());
	        book.put("bookName", b.getBookName());
	        book.put("author", b.getAuthor());
	        book.put("publishDate", b.getPublishDate());
	        book.put("summary", b.getSummary());
	        book.put("status", b.getAvailable());
	        
	        books.add(book);
	    }

        return new ResponseEntity<Object>(books, HttpStatus.OK);
    }

	@RequestMapping(value="/api/book/searchByISBN/{isbn}", method=RequestMethod.GET, headers="Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> searchBookByISBN(@PathVariable("isbn") String isbn) {
        List<Book> bookList = bookServiceImpl.findByISBN(isbn);
        List<JSONObject> books = new ArrayList<JSONObject>();

	    for (Book b : bookList) {
	        JSONObject book = new JSONObject();
	        book.put("id", b.getId());
	        book.put("isbn", b.getIsbn());
	        book.put("bookName", b.getBookName());
	        book.put("author", b.getAuthor());
	        book.put("publishDate", b.getPublishDate());
	        book.put("summary", b.getSummary());
	        book.put("status", b.getAvailable());
	        
	        books.add(book);
	    }

        return new ResponseEntity<Object>(books, HttpStatus.OK);
    }
	
}
