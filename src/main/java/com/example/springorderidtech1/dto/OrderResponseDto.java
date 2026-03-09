package com.example.springorderidtech1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponseDto {
    private String cardNumber;
    private String productName;
    private Double amount;
    private Double cardBalance;

}
