package com.example.demo.service;


import com.example.demo.pojo.dto.RewardsDTO;
import com.example.demo.pojo.entity.UserInfo;
import com.example.demo.pojo.entity.UserTransactions;
import com.example.demo.repository.TransactionRepository;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RewardsDTO getRewardsOfEachMonth(Date startDate, Date endDate) {
        LocalDate start = startDate.toLocalDate();
        LocalDate end = endDate.toLocalDate();
        List<RewardsDTO.RewardsEachMonthDTO> rewardsEachMonthDTOList = new ArrayList<>();
        while (start.isBefore(end)) {
            LocalDate nextMonth = start.plusMonths(1);
            System.out.println(Date.valueOf(start));
            System.out.println(Date.valueOf(nextMonth));
            System.out.println(this.transactionRepository.getUserTransactionsByDateBetween(
                    Date.valueOf(start), Date.valueOf(nextMonth)));

            List<UserTransactions> userTransactionsList = this.transactionRepository.getUserTransactionsByDateBetween(
                    Date.valueOf(start), Date.valueOf(nextMonth));

            HashMap<Integer, Long> rewardsEachMonth = new HashMap<>();
            userTransactionsList.stream().forEach(transaction -> {
                int id = transaction.getUserInfo().getId();
                rewardsEachMonth.put(id, rewardsEachMonth.getOrDefault(id, Long.valueOf(0)) + transaction.getAmount());
                for(int userId: rewardsEachMonth.keySet()) {
                    Long amount = rewardsEachMonth.get(userId);
                }
            });
            List<RewardsDTO.RewardsEachMonthDTO.CustomerRewardsEachMothDTO> rewardsEachMonthDTO = new ArrayList<>();
            for(int id: rewardsEachMonth.keySet()) {
                Long amount = rewardsEachMonth.get(id);
                amount = Math.max(amount - 50, 0) + Math.max(amount - 100, 0) * 2;
                rewardsEachMonthDTO.add(new RewardsDTO.RewardsEachMonthDTO.CustomerRewardsEachMothDTO(id, amount));
            }
            rewardsEachMonthDTOList.add(new RewardsDTO.RewardsEachMonthDTO(start.getMonth(), rewardsEachMonthDTO));
            start = nextMonth;
        }


        return new RewardsDTO(rewardsEachMonthDTOList);
    }

    @Override
    public Integer insertTransactions(UserTransactions userTransactions) {
        this.transactionRepository.save(userTransactions);
        return userTransactions.getTransactionId();
    }

    @Override
    public int insertUser(UserInfo customer) {
        this.userRepository.save(customer);
        return customer.getId();
    }
}
