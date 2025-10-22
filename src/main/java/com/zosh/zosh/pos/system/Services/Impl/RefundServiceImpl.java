package com.zosh.zosh.pos.system.Services.Impl;

import com.zosh.zosh.pos.system.Model.Branch;
import com.zosh.zosh.pos.system.Model.Order;
import com.zosh.zosh.pos.system.Model.Refund;
import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Payload.dto.RefundDTO;
import com.zosh.zosh.pos.system.Repository.OrderRepository;
import com.zosh.zosh.pos.system.Repository.RefundRepository;
import com.zosh.zosh.pos.system.Services.RefundServices;
import com.zosh.zosh.pos.system.Services.UserService;
import com.zosh.zosh.pos.system.mapper.RefundMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundServices {


    private final UserService userService;
    private final OrderRepository orderRepository;
    private final RefundRepository refundRepository;
    @Override
    public RefundDTO createRefund(RefundDTO refundDTO) throws Exception {

        User cashier = userService.getCurrentUser();
        Order order = orderRepository.findById(refundDTO.getOrderId()).orElseThrow(
                ()-> new Exception("Order not found")
        );

        Branch branch = order.getBranch();

        Refund createdRefund = Refund.builder()
                .order(order)
                .cashier(cashier)
                .branch(branch)
                .reason(refundDTO.getReason())
                .amount(refundDTO.getAmount())
                .createdAt(refundDTO.getCreatedAt())
                .build();
        Refund savedRefund = refundRepository.save(createdRefund);
        return RefundMapper.toRefundDTO(savedRefund);
    }

    @Override
    public List<RefundDTO> getAllRefunds() throws Exception {
        return refundRepository.findAll().stream().map(RefundMapper::toRefundDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByCashier(Long cashierId) throws Exception {
        return refundRepository.findByCashierId(cashierId).stream().map(RefundMapper::toRefundDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByShiftReport(Long shiftReportId) throws Exception {

        return refundRepository.findByShiftReportId(shiftReportId).stream().map(RefundMapper::toRefundDTO).collect(Collectors.toList());

    }

    @Override
    public List<RefundDTO> getRefundsByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) throws Exception {
        return refundRepository.findByCashierIdAndCreatedAtBetween(cashierId,startDate,endDate).stream().map(RefundMapper::toRefundDTO).collect(Collectors.toList());

    }

    @Override
    public List<RefundDTO> getRefundByBranch(Long branchId) throws Exception {
        return refundRepository.findByBranchId(branchId).stream().map(RefundMapper::toRefundDTO).collect(Collectors.toList());

    }

    @Override
    public RefundDTO getRefundById(Long refundId) throws Exception {
        return RefundMapper.toRefundDTO(refundRepository.findById(refundId).orElseThrow(
                ()-> new Exception("Refund not found")
        ));
    }

    @Override
    public void deleteRefund(Long refundId) throws Exception {
           this.getRefundById(refundId);
           refundRepository.deleteById(refundId);
    }
}
