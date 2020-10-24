package com.micah.lms.service;

import java.util.List;

import com.micah.lms.entity.Record;


public interface RecordService {
	
	public void addRecord(Record record);

	public Record updateRecord(Integer recordId, Record record);

	public List<Record> getAllRecords();

	public Record findRecordByUserBookId(Integer userId, Integer bookId);

}
