package com.zosh.zosh.pos.system.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String name;
    private String email;
    private String address;
    private String phone;
    @ElementCollection()
    private List<String> workingDays;

    @Column(columnDefinition = "TIME")
    private LocalTime openingTime;

    @Column(columnDefinition = "TIME")
    private LocalTime closingTime;

    @Column(columnDefinition = "datetime")
    private LocalDateTime   createdAt;


    @Column(columnDefinition = "datetime")
    private LocalDateTime updatedAt;

    @ManyToOne()
    private Store store;

    @OneToOne(cascade = CascadeType.REMOVE)
    private User manager;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();

    }

    @PreUpdate
    protected  void onUpdate(){
        updatedAt = LocalDateTime.now();
    }

}
