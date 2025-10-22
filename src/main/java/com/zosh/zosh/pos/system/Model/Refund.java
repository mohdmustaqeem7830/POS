package com.zosh.zosh.pos.system.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stripe.model.PaymentMethod;
import com.zosh.zosh.pos.system.Domain.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Order order;

    private Double amount;

    @ManyToOne
    @JsonIgnore
    private ShiftReport shiftReport;

    @ManyToOne
    private User cashier;
    private String reason;
    @ManyToOne
    private Branch branch;

    private PaymentType paymentType;
    @Column(columnDefinition = "datetime")
    private LocalDateTime createdAt;


    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
