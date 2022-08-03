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
@Table(name = "Users")
public class User {
    @Id
    @CsvBindByName(column = "Name")
    private String name;
    @CsvBindByName(column = "First name")
    private String firstName;
    @CsvBindByName(column = "Member since")
    @CsvDate("dd/MM/yyyy")
    private Date memberSince;
    @CsvBindByName(column = "Member till")
    @CsvDate("dd/MM/yyyy")
    private Date memberTill;
    @CsvBindByName(column = "Gender")
    private String gender;

}
