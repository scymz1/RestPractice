package com.example.demo.repository;

import com.example.demo.pojo.entity.UserInfo;
import com.example.demo.pojo.entity.UserTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<UserTransactions, Integer> {
    List<UserTransactions> getUserTransactionsByDateBetween(Date date, Date date2);
}
