package com.example.crudOperations.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Borrowed")
public class Borrowed {


    @CsvBindByName(column = "borrower")
    private String borrower;
    @Id
    @CsvBindByName(column = "book")
    private String book;
    @CsvBindByName(column = "borrowed from")
    @CsvDate("dd/MM/yyyy")
    private Date borrowedFrom;
    @CsvBindByName(column = "borrowed to")
    @CsvDate("dd/MM/yyyy")
    private Date borrowedTo;
}
