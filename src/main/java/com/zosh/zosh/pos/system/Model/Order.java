package com.zosh.zosh.pos.system.Model;


import com.razorpay.Payment;
import com.zosh.zosh.pos.system.Domain.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private Double totalAmount;

    @Column(columnDefinition = "datetime")
    private LocalDateTime createdAt;


    @ManyToOne()
    private Branch branch;

    @ManyToOne()
    private  User cashier;

    @ManyToOne()
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> items;



    private PaymentType paymentType;


    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();

    }

}
