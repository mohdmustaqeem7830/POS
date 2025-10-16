package com.zosh.zosh.pos.system.Model;

import com.zosh.zosh.pos.system.Domain.StoreStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(nullable = false)
     private String name;

    @Column(nullable = false,unique = true)
     private String sku;

    private String description;

    private Double mrp;


    private Double sellingPrice;

    private String brand;

    private String image;

    @ManyToOne
    private Category category;

    @ManyToOne
    private  Store store ;

    @Column(columnDefinition = "datetime")
     private LocalDateTime createdAt;

    @Column(columnDefinition = "datetime")
     private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();

    }

    @PreUpdate
    protected  void onUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
