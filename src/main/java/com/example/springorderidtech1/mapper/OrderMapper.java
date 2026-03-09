package com.example.springorderidtech1.mapper;


import com.example.springorderidtech1.dao.entity.CardEntity;
import com.example.springorderidtech1.dao.entity.OrderEntity;
import com.example.springorderidtech1.dao.entity.ProductEntity;
import com.example.springorderidtech1.dto.OrderRequestDto;
import com.example.springorderidtech1.dto.OrderResponseDto;

import java.time.LocalDateTime;

public class OrderMapper {

    public static OrderEntity mapToOrderEntity(OrderRequestDto orderRequestDto) {
        var entity = new OrderEntity();
        entity.setOrderDate(LocalDateTime.now());
        entity.setQuantity(orderRequestDto.getProductCount());
        entity.setCardId(orderRequestDto.getCardId());
        entity.setProductId(orderRequestDto.getProductId());
        entity.setTotalAmount(orderRequestDto.getAmount());
        return entity;
    }

    public static OrderResponseDto mapEntitiyToOrderResponseDto(CardEntity card, ProductEntity product,
                                                                OrderEntity order) {
        var response = new OrderResponseDto();
        response.setAmount(order.getTotalAmount());
        response.setCardBalance(card.getBalance());
        response.setCardNumber(card.getCardNumber());
        response.setProductName(product.getName());
        return response;
    }
}
