package com.example.demo.service;

import com.example.demo.pojo.dto.RewardsDTO;
import com.example.demo.pojo.entity.UserInfo;
import com.example.demo.pojo.entity.UserTransactions;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {


    RewardsDTO getRewardsOfEachMonth(Date startDate, Date endDate);

    Integer insertTransactions(UserTransactions userTransactions);

    int insertUser(UserInfo customer);
}
