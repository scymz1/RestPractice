package com.example.demo.controller;

import com.example.demo.pojo.dto.RewardsDTO;
import com.example.demo.pojo.entity.UserInfo;
import com.example.demo.pojo.entity.UserTransactions;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/customer/")
    ResponseEntity<Integer> insertCustomers(@RequestBody UserInfo customer) {
        return new ResponseEntity(userService.insertUser(customer), HttpStatus.OK);
    }

    @PostMapping("/orders/")
    ResponseEntity<Integer> insertOrders(@RequestBody UserTransactions userTransactions) {
        return new ResponseEntity(userService.insertTransactions(userTransactions), HttpStatus.OK);
    }



    @GetMapping("/userReward/")
    ResponseEntity<RewardsDTO> getRewards(@RequestParam Date startDate, @RequestParam Date endDate) {
        return new ResponseEntity(userService.getRewardsOfEachMonth(startDate, endDate), HttpStatus.OK);
    }
}
