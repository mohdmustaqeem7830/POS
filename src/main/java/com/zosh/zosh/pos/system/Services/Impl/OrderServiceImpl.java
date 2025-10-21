package com.zosh.zosh.pos.system.Services.Impl;

import com.zosh.zosh.pos.system.Domain.OrderStatus;
import com.zosh.zosh.pos.system.Domain.PaymentType;
import com.zosh.zosh.pos.system.Model.*;
import com.zosh.zosh.pos.system.Payload.Response.ApiResponse;
import com.zosh.zosh.pos.system.Payload.dto.OrderDTO;
import com.zosh.zosh.pos.system.Payload.dto.OrderItemDTO;
import com.zosh.zosh.pos.system.Repository.OrderItemRepository;
import com.zosh.zosh.pos.system.Repository.OrderRepository;
import com.zosh.zosh.pos.system.Repository.ProductRepository;
import com.zosh.zosh.pos.system.Services.OrderService;
import com.zosh.zosh.pos.system.Services.UserService;
import com.zosh.zosh.pos.system.mapper.OrderMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;


    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) throws Exception {
        User cashier = userService.getCurrentUser();


        Branch branch = cashier.getBranch();

        if (branch == null) {
            throw new Exception("Cashier's Branch not found");
        }

        Order order = Order.builder()
                .branch(branch)
                .cashier(cashier)
                .customer(orderDTO.getCustomer())
                .paymentType(orderDTO.getPaymentType())

                .build();

        List<OrderItem> orderItems = orderDTO.getItems().stream().map(

                itemDto -> {
                    Product product = productRepository.findById(itemDto.getProductId()).orElseThrow(
                            () -> new EntityNotFoundException("Product Not Found")
                    );

                   return  OrderItem.builder()
                            .product(product)
                            .quantity(itemDto.getQuantity())
                            .price(product.getSellingPrice() * itemDto.getQuantity())
                            .order(order)
                            .build();

                }

        ).toList();

        double total = orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
        order.setTotalAmount(total);
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);

        return OrderMapper.toDto(savedOrder);
    }

    @Override
    public OrderDTO getOrderById(Long orderId) throws Exception {
        return orderRepository.findById(orderId)
                .map(OrderMapper::toDto)
                .orElseThrow(
                        () -> new Exception("Order Not Found " + orderId)
                );
    }

    @Override
    public List<OrderDTO> getOrdersByBranchId(Long branchId, Long customerId, Long cashierId, PaymentType paymentType, OrderStatus status) throws Exception {
        return orderRepository.findByBranchId(branchId).stream().filter(
                        order -> customerId == null || (order.getCustomer() != null && order.getCustomer().getId().equals(customerId)))
                .filter(order -> cashierId == null ||
                        order.getCashier() != null &&
                                order.getCashier().getId().equals(cashierId)
                )
                .filter(order -> paymentType == null ||
                        order.getPaymentType() == paymentType
                ).map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrderByCashier(Long cashierId) {


        return orderRepository.findByCashierId(cashierId).stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long id) throws Exception {
       Order order = orderRepository.findById(id).orElseThrow(
               () -> new EntityNotFoundException("Order Not Found " + id)
       );

       orderRepository.delete(order);

        ApiResponse apiResponse = new  ApiResponse();
        apiResponse.setMessage("Order Deleted");


    }

    @Override
    public List<OrderDTO> getTodayOrdersByBranch(Long branchId) throws Exception {

        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();

        return orderRepository.findByBranchIdAndCreatedAtBetween(branchId,start,end).stream().map(OrderMapper :: toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByCustomerId(Long customerId) throws Exception {
        return orderRepository.findByCustomerId(customerId).stream().map(OrderMapper::toDto).collect(Collectors.toList());

    }

    @Override
    public List<OrderDTO> getTop5RecentOrdersByBranchId(Long branchId) throws Exception {
        return orderRepository.findTop5ByBranchIdOrderByCreatedAtDesc(branchId).stream().map(OrderMapper::toDto).collect(Collectors.toList());

    }
}
