package com.zosh.zosh.pos.system.Repository;

import com.zosh.zosh.pos.system.Model.Customer;
import org.aspectj.apache.bcel.util.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    List<Customer> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String fullName,String email);
}
