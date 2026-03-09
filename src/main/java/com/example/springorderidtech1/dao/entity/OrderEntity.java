    package com.example.springorderidtech1.dao.entity;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.time.LocalDateTime;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Table(name = "orders")
    @Entity
    public class OrderEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;
        @Column(name = "product_id")
        private Long productId;
        @Column(name = "card_id")
        private Long cardId;
        @Column(name = "quantity")
        private Integer quantity;
        @Column(name = "total_amount")
        private Double totalAmount;
        @Column(name = "order_data")
        private LocalDateTime orderDate;


    }
