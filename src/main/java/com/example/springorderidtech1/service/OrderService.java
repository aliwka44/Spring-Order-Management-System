package com.example.springorderidtech1.service;


import com.example.springorderidtech1.dao.entity.CardEntity;
import com.example.springorderidtech1.dao.entity.OrderEntity;
import com.example.springorderidtech1.dao.entity.ProductEntity;
import com.example.springorderidtech1.dao.repository.CardRepository;
import com.example.springorderidtech1.dao.repository.OrderRepository;
import com.example.springorderidtech1.dao.repository.ProductRepository;
import com.example.springorderidtech1.dto.OrderRequestDto;
import com.example.springorderidtech1.dto.OrderResponseDto;
import com.example.springorderidtech1.exceptions.*;
import com.example.springorderidtech1.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.springorderidtech1.mapper.OrderMapper.mapToOrderEntity;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CardRepository cardRepository;

    @Transactional(noRollbackFor = DataMismatchException.class)
    public void addOrder(OrderRequestDto orderRequestDto) {

        // check product details
        log.info("ActionLog.addOrder.start for cardId" + orderRequestDto.getCardId());
        var product = fetchProductIfExist(orderRequestDto.getProductId());
        checkProductAvailability(product, orderRequestDto.getProductCount());
        // check card details
        var card = fetchCardIfExist(orderRequestDto.getCardId());
        checkCardBalance(card, orderRequestDto.getAmount());

        product.setStock(product.getStock() - orderRequestDto.getProductCount());
        card.setBalance(card.getBalance() - orderRequestDto.getAmount());
        cardRepository.save(card);
        productRepository.save(product);
        if(orderRequestDto.getProductCount() == 1){
            throw new DataMismatchException("test transactional exception");
        }
        orderRepository.save(mapToOrderEntity(orderRequestDto));
        log.info("ActionLog.addOrder end for cardId" + orderRequestDto.getCardId());

    }

    public OrderResponseDto getOrderById(Long orderId) {
        var order = fetchOrderIfExist(orderId);
        var card = fetchCardIfExist(order.getCardId());
        var product = fetchProductIfExist(order.getProductId());
        return OrderMapper.mapEntitiyToOrderResponseDto(card, product, order);
    }

    private ProductEntity fetchProductIfExist(Long productId) {
        var product = productRepository.findById(productId);
        if (product.isEmpty()) {
            log.error("Product not found with id " + productId);
            throw new ProductNotFoundExceptions("product not found");
        }
        return product.get();
    }

    private void checkProductAvailability(ProductEntity product, Integer orderCount) {
        if (product.getStock() < orderCount) {
            throw new ProductNotAvailabilityExceptions("product not Availability");
        }
    }

    private CardEntity fetchCardIfExist(Long cardId) {
        var card = cardRepository.findById(cardId);
        if (card.isEmpty()) {
            throw new CardNotFoundExceptions("card not found");
        }
        return card.get();
    }

    private void checkCardBalance(CardEntity card, Double amount) {
        if (card.getBalance() < amount) {
            throw new CardNotBalanceExceptions("card not enough balance");
        }
    }

    private OrderEntity fetchOrderIfExist(Long orderId) {
        var order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new OrderNotFoundExceptions("Order Not Found");
        }
        return order.get();
    }

}
