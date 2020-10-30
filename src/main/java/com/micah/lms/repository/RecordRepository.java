package com.micah.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.micah.lms.entity.Record;


public interface RecordRepository extends CrudRepository<Record, Integer> {

	@Query("SELECT r FROM Record r WHERE uid=:userId AND bid=:bookId AND return_date is null")
	Record findRecordByUserBookId(@Param("userId") Integer userId, @Param("bookId") Integer bookId);
}