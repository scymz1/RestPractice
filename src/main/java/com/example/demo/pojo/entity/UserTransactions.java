package com.example.demo.pojo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "userTransactions")
@NoArgsConstructor
@AllArgsConstructor
public class UserTransactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    UserInfo userInfo;


    @Column(name = "date")
    Date date;

    @Column(name = "TransactionAmount")
    int amount;


}
