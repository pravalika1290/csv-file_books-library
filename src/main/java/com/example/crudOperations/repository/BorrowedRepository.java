package com.example.crudOperations.repository;

import com.example.crudOperations.model.Borrowed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowedRepository extends JpaRepository<Borrowed, Long> {
}
