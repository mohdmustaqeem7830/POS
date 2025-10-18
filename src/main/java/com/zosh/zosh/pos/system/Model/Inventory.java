package com.zosh.zosh.pos.system.Model;

import com.zosh.zosh.pos.system.Payload.dto.BranchDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {

@Id
    private Long id ;

    @ManyToOne()
    private Branch branch;

    @ManyToOne()
    private Product product;

@Column(nullable=false)
    private Integer quantity;


    @Column(columnDefinition = "datetime")
private LocalTime lastUpdate;


    @PrePersist
    @PreUpdate
    protected  void onUpdate(){
        this.lastUpdate = LocalTime.now();
    }





}
