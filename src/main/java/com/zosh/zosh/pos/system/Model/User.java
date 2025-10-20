package com.zosh.zosh.pos.system.Model;


import com.zosh.zosh.pos.system.Domain.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

   @Column(nullable = false)
   private String fullName ;

   @Column(nullable = false,unique = true)
   @Email(message = "Email Should be valid")
   private String email;

   @Column(nullable = false)
   private String password;


   @Column(nullable = false)
   @Enumerated(EnumType.STRING)
   private UserRole role;

   @ManyToOne
   private Store store;

   @ManyToOne
   private Branch branch;

   private String phone ;

   @Column(columnDefinition = "datetime")
   private LocalDateTime createdAt;
   @Column(columnDefinition = "datetime")
   private LocalDateTime updatedAt;
   @Column(columnDefinition = "datetime")
   private LocalDateTime lastlogin;





}
