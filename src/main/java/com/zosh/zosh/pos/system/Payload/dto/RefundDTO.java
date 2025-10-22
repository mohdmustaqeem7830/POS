package com.zosh.zosh.pos.system.Payload.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zosh.zosh.pos.system.Domain.PaymentType;
import com.zosh.zosh.pos.system.Model.Branch;
import com.zosh.zosh.pos.system.Model.Order;
import com.zosh.zosh.pos.system.Model.ShiftReport;
import com.zosh.zosh.pos.system.Model.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class RefundDTO {
    private Long id;
    private Order order;
    private Long orderId;
    private Double amount;
    private ShiftReport shiftReport;
    private Long shiftReportId;
    private UserDto cashier;
    private String cashierName;
    private String reason;
    private Long branchId;
    private PaymentType paymentType;
    private BranchDTO branch;
    private LocalDateTime createdAt;

}
