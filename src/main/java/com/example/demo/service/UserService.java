package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface UserService {


    List<Integer> getRewards(String id, Date endDate);
}
