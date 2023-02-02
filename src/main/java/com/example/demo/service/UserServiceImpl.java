package com.example.demo.service;


import com.example.demo.pojo.entity.UserInfo;
import com.example.demo.pojo.entity.UserTransactions;
import com.example.demo.repository.TransactionRepository;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Integer> getRewards(String id, Date endDate) {
        LocalDate end = endDate.toLocalDate();
        LocalDate start = end.minusMonths(3);
        LocalDate second = end.minusMonths(2);
        LocalDate third = end.minusMonths(1);
        UserInfo userInfo = userRepository.getUserInfoById(id);
        List<UserTransactions> userTransactionsList1 = this.transactionRepository.getUserTransactionsByDateBetweenAndUserInfo(
                Date.valueOf(start), Date.valueOf(second), userInfo);
        List<UserTransactions> userTransactionsList2 = this.transactionRepository.getUserTransactionsByDateBetweenAndUserInfo(
                Date.valueOf(second), Date.valueOf(third), userInfo);
        List<UserTransactions> userTransactionsList3 = this.transactionRepository.getUserTransactionsByDateBetweenAndUserInfo(
                Date.valueOf(third), Date.valueOf(end), userInfo);

        int firstMonth = userTransactionsList1.stream().map(transactions -> (transactions.getAmount())).reduce(0, Integer::sum);
        int secondMonth = userTransactionsList2.stream().map(transactions -> (transactions.getAmount())).reduce(0, Integer::sum);
        int thirdMonth = userTransactionsList3.stream().map(transactions -> (transactions.getAmount())).reduce(0, Integer::sum);
        List<Integer> awards = new ArrayList<>();
        awards.add(firstMonth);
        awards.add(secondMonth);
        awards.add(thirdMonth);

        awards.stream().map(a -> Math.max(a - 50, 0) + Math.max(a - 100, 0) * 2).collect(Collectors.toList());
        return awards;
    }
}
