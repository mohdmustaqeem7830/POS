package com.zosh.zosh.pos.system.controller;


import com.zosh.zosh.pos.system.Model.Customer;
import com.zosh.zosh.pos.system.Payload.Response.ApiResponse;
import com.zosh.zosh.pos.system.Repository.CustomerRepository;
import com.zosh.zosh.pos.system.Services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer)  {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id ,@RequestBody Customer customer) throws Exception {
        return ResponseEntity.ok(customerService.updateCustomer(id,customer));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable Long id ) throws Exception {
        customerService.deleteCustomer(id);
        ApiResponse response = new ApiResponse();
        response.setMessage("successfully deleted");
        return ResponseEntity.ok(response);
    }


    @GetMapping()
    public ResponseEntity<List<Customer>> allCustomer( ) throws Exception {


        return ResponseEntity.ok(customerService.getAllCustomers());
    }



    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomer(@RequestParam String q ) throws Exception {


        return ResponseEntity.ok(customerService.searchCustomer(q));
    }

}
