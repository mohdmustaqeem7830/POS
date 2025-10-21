package com.zosh.zosh.pos.system.controller;

import com.zosh.zosh.pos.system.Domain.OrderStatus;
import com.zosh.zosh.pos.system.Domain.PaymentType;
import com.zosh.zosh.pos.system.Payload.dto.OrderDTO;
import com.zosh.zosh.pos.system.Repository.OrderRepository;
import com.zosh.zosh.pos.system.Services.OrderService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO  orderDTO) throws Exception {

            return ResponseEntity.ok(orderService.createOrder(orderDTO));

    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) throws Exception {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }



    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<OrderDTO>> getOrderByBranch(@PathVariable Long branchId,@RequestParam (required=false) Long customerId,
                                                           @RequestParam (required=false) Long cashierId,
                                                           @RequestParam (required=false) PaymentType paymentType,
                                                           @RequestParam (required = false) OrderStatus orderStatus


    ) throws Exception {
        return ResponseEntity.ok(orderService.getOrdersByBranchId(branchId,customerId,cashierId,paymentType,orderStatus));
    }

    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<OrderDTO>> getOrderByCashierId(@PathVariable Long cashierId) throws Exception {
        return ResponseEntity.ok(orderService.getOrderByCashier(cashierId));
    }
    @GetMapping("/today/branch/{branchId}")
    public ResponseEntity<List<OrderDTO>> getTodayOrder  (@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(orderService.getTodayOrdersByBranch(branchId));
    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderDTO>> getCustomerOrder  (@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(id));
    }

    @GetMapping("/recent/{branchId}")
    public ResponseEntity<List<OrderDTO>> getRecentOrder  (@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(orderService.getTop5RecentOrdersByBranchId(branchId));
    }





}
