package com.micah.lms.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

import com.micah.lms.entity.User;
import com.micah.lms.entity.Book;

@Data
@Entity
@Table(name = "record")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="uid")
    private User user;

    @ManyToOne
    @JoinColumn(name="bid")
    private Book book; 

    private String borrowDate;

    private String expiryDate;

    private String returnDate;   
}
