package com.example.demo.pojo.dto;

import lombok.AllArgsConstructor;

import java.time.Month;
import java.util.List;

@AllArgsConstructor
public class RewardsDTO {
    public List<RewardsEachMonthDTO> rewardsEachMonthDTOS;

    @AllArgsConstructor
    public static class RewardsEachMonthDTO {

        public Month month;

        public List<CustomerRewardsEachMothDTO> customerRewardsEachMothDTOS;

        @AllArgsConstructor
        public static class CustomerRewardsEachMothDTO{
            public int userId;
            public Long Rewards;
        }
    }
}
