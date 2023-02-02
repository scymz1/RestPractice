package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public interface UserService {



    ArrayList<HashMap<String, Integer>> getRewards(Date endDate);
}
