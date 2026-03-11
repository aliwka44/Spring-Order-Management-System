package com.example.springorderidtech1.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {
    public void payMonthlyFree(){
        System.out.println("Every scheduled 10 seconds");
        System.out.println(LocalDateTime.now());


    }
}
