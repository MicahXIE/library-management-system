package com.micah.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micah.lms.entity.Record;
import com.micah.lms.repository.RecordRepository;


@Service 
public class RecordServiceImpl implements RecordService {
	
	@Autowired
	private RecordRepository recordRepository;

	@Override
	public void addRecord(Record record) {
		recordRepository.save(record);
	}

	@Override
	@Transactional
	public Record getRecord(Integer recordId) {
		return recordRepository.findOne(recordId);
	}

	@Override
	@Transactional
	public Record updateRecord(Integer recordId, Record record) {
		return recordRepository.save(record);
	}

	@Override
	public List<Record> getAllRecords() {
		List<Record> records = new ArrayList<>();
		recordRepository.findAll().forEach(records::add);
		return records;
	}

	@Override
	public void deleteAll() {
		recordRepository.deleteAll();
	}

	@Override
	public Record findRecordByUserBookId(Integer userId, Integer bookId) {
		Record record = recordRepository.findRecordByUserBookId(userId, bookId);
		return record;
	}

}
