package com.example.demo.pojo.entity;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "UserInfo")
//@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @Getter
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Getter
    @Column(name = "firstname")
    String firstName;

    @Getter
    @Column(name = "lastName")
    String lastName;

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<UserTransactions> userTransactionsList;
}
