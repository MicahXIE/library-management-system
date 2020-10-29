package com.micah.lms.controllers;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.micah.lms.entity.Record;
import com.micah.lms.entity.User;
import com.micah.lms.result.Result;
import com.micah.lms.result.ResultFactory;
import com.micah.lms.service.BookServiceImpl;
import com.micah.lms.service.RecordServiceImpl;
import com.micah.lms.service.UserServiceImpl;
import com.micah.lms.utils.StringUtils;


@RestController
public class RecordController {
	
    @Value("${book.duration}")
	private int duration;

    public static Logger logger = LogManager.getLogger(RecordController.class.getName());

	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	@Autowired
	private RecordServiceImpl recordServiceImpl;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping(value="/api/records", method=RequestMethod.GET, headers="Accept=application/json")
    public Result getAllRecords() {
        List<Record> recordList = recordServiceImpl.getAllRecords();
        List<JSONObject> records = new ArrayList<JSONObject>();

	    for (Record r : recordList) {
	        JSONObject record = new JSONObject();
	        record.put("id", r.getId());
	        record.put("uid", r.getUser().getId());
	       	record.put("bid", r.getBook().getId());
	        record.put("borrowDate", r.getBorrowDate());
	        record.put("expiryDate", r.getExpiryDate());
	        record.put("returnDate", r.getReturnDate());
	        
	        records.add(record);
	    }
        return ResultFactory.buildSuccessResult(records);
    }

	@RequestMapping(value="/api/borrow/{userId}/{bookId}", method=RequestMethod.POST, headers="Accept=application/json")
    public Result addRecord(@PathVariable("userId") int userId, @PathVariable("bookId") int bookId) {

    	Record record = new Record();
        User user = userServiceImpl.getUser(userId);
        Book book = bookServiceImpl.getBook(bookId);

        if (user == null) {
        	logger.error("user {} doesn't exist.", userId);
            return ResultFactory.buildFailResult("user doesn't exist.");
        }

        if (book == null) {
        	logger.error("book {} doesn't exist.", bookId);
            return ResultFactory.buildFailResult("book doesn't exist.");
        }

        int count = user.getCount();
        if (count <= 0) {
        	logger.error("user {} already reach the maximum limit.", user.getId());
            return ResultFactory.buildFailResult("user reach the maximum limit.");
        } else {
        	user.setCount(count - 1);
        	logger.info("user {} still can borrow {} books.", user.getId(), count - 1);
        }

        boolean available = book.getAvailable();
        if (!available) {
        	logger.error("book {} have already been borrowed ", book.getId());
            return ResultFactory.buildFailResult("book is not available now.");
        } else {
        	book.setAvailable(false);
        	logger.info("book {} is brrowed successfully.", book.getId());
        }

        record.setUser(user);
        record.setBook(book);

        String borrow = StringUtils.getCurrentDateStr();
        String expiry = StringUtils.getExpiryDateStr(borrow, duration);
        record.setBorrowDate(borrow);
        record.setExpiryDate(expiry);

        userServiceImpl.updateUser(userId, user);
        bookServiceImpl.updateBook(bookId, book);
        recordServiceImpl.addRecord(record);

        return ResultFactory.buildSuccessResult(record);
    }

	@RequestMapping(value="/api/return/{userId}/{bookId}", method=RequestMethod.POST, headers="Accept=application/json")
    public Result updateRecord(@PathVariable("userId") int userId, @PathVariable("bookId") int bookId) {
    	
        User user = userServiceImpl.getUser(userId);
        Book book = bookServiceImpl.getBook(bookId);

        if (user == null) {
        	logger.error("user {} doesn't exist.", userId);
            return ResultFactory.buildFailResult("user doesn't exist.");
        }

        if (book == null) {
        	logger.error("book {} doesn't exist.");
            return ResultFactory.buildFailResult("book doesn't exist.");
        }

        Record record = recordServiceImpl.findRecordByUserBookId(userId, bookId);
        if (record == null) {
        	logger.error("record doesn't exist.");
            return ResultFactory.buildFailResult("record doesn't exist.");
        }

        int recordId = record.getId();
        String returnDate = StringUtils.getCurrentDateStr();
        record.setReturnDate(returnDate);

        int count = user.getCount();
        user.setCount(count + 1);
        book.setAvailable(true);

        userServiceImpl.updateUser(userId, user);
        bookServiceImpl.updateBook(bookId, book);
        recordServiceImpl.updateRecord(recordId, record);

        return ResultFactory.buildSuccessResult(recordServiceImpl.getRecord(recordId));
    }

}
