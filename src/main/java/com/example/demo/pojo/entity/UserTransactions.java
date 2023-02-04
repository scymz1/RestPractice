package com.example.demo.pojo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

//@Data
@Entity
@Table(name = "userTransactions")
@NoArgsConstructor
@AllArgsConstructor
public class UserTransactions {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @Getter
    @Setter
    UserInfo userInfo;


    @Column(name = "date")
    @Getter
    Date date;

    @Getter
    @Column(name = "TransactionAmount")
    Long amount;


}
