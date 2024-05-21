package com.assignment.newaccount.entities;

import com.assignment.newaccount.vo.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private TransactionType transactionType;
    private double transactionAmt;
    private LocalDateTime transactionDt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;
}
