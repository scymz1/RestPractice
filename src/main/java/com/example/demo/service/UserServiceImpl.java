package com.example.demo.service;


import com.example.demo.pojo.entity.UserTransactions;
import com.example.demo.repository.TransactionRepository;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
    public ArrayList<HashMap<String, Integer>> getRewards(Date endDate) {
        LocalDate end = endDate.toLocalDate();
        LocalDate start = end.minusMonths(3);
        LocalDate second = end.minusMonths(2);
        LocalDate third = end.minusMonths(1);
        List<UserTransactions> userTransactionsList1 = this.transactionRepository.getUserTransactionsByDateBetween(
                Date.valueOf(start), Date.valueOf(second));
        List<UserTransactions> userTransactionsList2 = this.transactionRepository.getUserTransactionsByDateBetween(
                Date.valueOf(second), Date.valueOf(third));
        List<UserTransactions> userTransactionsList3 = this.transactionRepository.getUserTransactionsByDateBetween(
                Date.valueOf(third), Date.valueOf(end));

        ArrayList<List<UserTransactions>> transactionLists = new ArrayList<>();
        transactionLists.add(userTransactionsList1);
        transactionLists.add(userTransactionsList2);
        transactionLists.add(userTransactionsList3);

        ArrayList<HashMap<String, Integer>> rewards = new ArrayList<>();
        for(List<UserTransactions> transactionList: transactionLists) {
            HashMap<String, Integer> rewardsEachMonth = new HashMap<>();
            transactionList.stream().forEach(transaction -> {
                String id = transaction.getUserInfo().getId();
                rewardsEachMonth.put(id, rewardsEachMonth.getOrDefault(id, 0) + transaction.getAmount());
            });

            for(String id: rewardsEachMonth.keySet()) {
                int amount = rewardsEachMonth.get(id);
                rewardsEachMonth.put(id, Math.max(amount - 50, 0) + Math.max(amount - 100, 0) * 2);
            }

            rewards.add(rewardsEachMonth);
        }


//        int firstMonth = userTransactionsList1.stream().map(transactions -> (transactions.getAmount())).reduce(0, Integer::sum);
//        int secondMonth = userTransactionsList2.stream().map(transactions -> (transactions.getAmount())).reduce(0, Integer::sum);
//        int thirdMonth = userTransactionsList3.stream().map(transactions -> (transactions.getAmount())).reduce(0, Integer::sum);
//        List<Integer> awards = new ArrayList<>();
//        awards.add(firstMonth);
//        awards.add(secondMonth);
//        awards.add(thirdMonth);

        //awards.stream().map(a -> Math.max(a - 50, 0) + Math.max(a - 100, 0) * 2).collect(Collectors.toList());
        return rewards;
    }
}
