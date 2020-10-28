package com.micah.lms.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;


@Data
@Entity
@Table(name = "book")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Book {

    @Id
    @Column(name = "id")
	private int id;
	
	private String isbn;

	private String bookName;

	private String author;

	private String publishDate;

	private String summary;

	private Boolean available;

	// private Boolean status;
	
}

