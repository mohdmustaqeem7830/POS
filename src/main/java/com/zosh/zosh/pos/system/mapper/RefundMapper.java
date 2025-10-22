package com.zosh.zosh.pos.system.mapper;

import com.zosh.zosh.pos.system.Model.Refund;
import com.zosh.zosh.pos.system.Payload.dto.RefundDTO;

public class RefundMapper {

    public static RefundDTO toRefundDTO(Refund refund) {
        return RefundDTO.builder()
                .id(refund.getId())
                .orderId(refund.getOrder().getId())
                .amount(refund.getAmount())
                .paymentType(refund.getPaymentType())
                .cashierName(refund.getCashier().getFullName())
                .shiftReportId(refund.getShiftReport()!=null? refund.getShiftReport().getId():null)
                .createdAt(refund.getCreatedAt())
                .build();
    }
}
