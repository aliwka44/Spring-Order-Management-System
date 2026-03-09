package com.example.springorderidtech1.controller;

import com.example.springorderidtech1.dto.OrderRequestDto;
import com.example.springorderidtech1.dto.OrderResponseDto;
import com.example.springorderidtech1.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void addOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        orderService.addOrder(orderRequestDto);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDto getOrderId(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

}
