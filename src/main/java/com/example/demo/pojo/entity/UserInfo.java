package com.example.demo.pojo.entity;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "UserInfo")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    @Column(name = "firstname")
    String firstName;

    @Column(name = "lastName")
    String lastName;

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<UserTransactions> userTransactionsList;
}
