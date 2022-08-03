package com.example.crudOperations.dataLoader;

import com.example.crudOperations.model.Book;
import com.example.crudOperations.model.User;
import com.example.crudOperations.model.Borrowed;
import com.example.crudOperations.repository.BookRepository;
import com.example.crudOperations.repository.BorrowedRepository;
import com.example.crudOperations.repository.UserRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
    private BookRepository bookRepository;
    private UserRepository userRepository;
    private BorrowedRepository borrowedRepository;

    //contructors
    @Autowired
    public DataLoader(
            BookRepository bookRepository,
            UserRepository userRepository,
            BorrowedRepository borrowedRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.borrowedRepository = borrowedRepository;
    }

    @Autowired
    ResourceLoader resourceLoader;

    public void run(ApplicationArguments args) throws IOException {
        BookTable();
        UserTable();
        BorrowedTable();
    }

    public void BookTable() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:books.csv");
        File file = resource.getFile();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            CsvToBean<Book> csvReader = new CsvToBeanBuilder(br)
                    .withType(Book.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Book> results = csvReader.parse();
            bookRepository.saveAll(results);
        } catch (
                Exception e) {
            System.out.println(e);
        }
    }

    //...........................................User..................................................
    public void UserTable() throws IOException {
        Resource userResource = resourceLoader.getResource("classpath:user.csv");
        File userFile = userResource.getFile();
        try (BufferedReader br = new BufferedReader(new FileReader(userFile))) {
            CsvToBean<User> csvUserFileReader = new CsvToBeanBuilder(br)
                    .withType(User.class)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<User> results = csvUserFileReader.parse();
            userRepository.saveAll(results);
        } catch (
                Exception e) {
            System.out.println(e);
        }
    }

    //............................borrowed...................................
    public void BorrowedTable() throws IOException {
        Resource borrowedResource = resourceLoader.getResource("classpath:borrowed.csv");
        File borrowedFile = borrowedResource.getFile();
        try (BufferedReader br = new BufferedReader(new FileReader(borrowedFile))) {
            CsvToBean<Borrowed> csvBorrowedFileReader = new CsvToBeanBuilder(br)
                    .withType(Borrowed.class)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Borrowed> results = csvBorrowedFileReader.parse();
            borrowedRepository.saveAll(results);

        } catch (
                Exception e) {
            System.out.println(e);
        }
    }
}
