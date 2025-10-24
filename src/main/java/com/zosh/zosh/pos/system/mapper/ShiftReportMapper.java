package com.zosh.zosh.pos.system.mapper;

import com.zosh.zosh.pos.system.Model.Order;
import com.zosh.zosh.pos.system.Model.Product;
import com.zosh.zosh.pos.system.Model.Refund;
import com.zosh.zosh.pos.system.Model.ShiftReport;
import com.zosh.zosh.pos.system.Payload.dto.OrderDTO;
import com.zosh.zosh.pos.system.Payload.dto.ProductDTO;
import com.zosh.zosh.pos.system.Payload.dto.RefundDTO;
import com.zosh.zosh.pos.system.Payload.dto.ShiftReportDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ShiftRepotMapper {

    private static ShiftReportDTO toDTO (ShiftReport shiftReport){
        return  ShiftReportDTO.builder()
                .id(shiftReport.getId())
                .shiftStart(shiftReport.getShiftStart())
                .shiftEnd(shiftReport.getShiftEnd())
                .totalSales(shiftReport.getTotalSales())
                .totalRefunds(shiftReport.getTotalRefunds())
                .netSales(shiftReport.getNetSales())
                .totalOrders(shiftReport.getTotalOrders())
                .cashier(shiftReport.getCashier())
                .cashierId(shiftReport.getCashier().getId())
                .branchId(shiftReport.getBranch().getId())
                .recentOrder(mapOrders(shiftReport.getRecentOrder()))
                .topSellingProducts(mapProducts(shiftReport.getTopSellingProducts()))
                .refunds(mapRefunds(shiftReport.getRefunds()))
                .paymentSummaryList(shiftReport.getPaymentSummaryList())
                .build();
    }

    private static List<RefundDTO> mapRefunds(List<Refund> refunds) {
        if (refunds == null || refunds.isEmpty()) {return null;}

        return refunds.stream().map(RefundMapper :: toRefundDTO).collect(Collectors.toList());
    }

    private static List<ProductDTO> mapProducts(List<Product> topSellingProducts) {
        if (topSellingProducts == null || topSellingProducts.isEmpty()) {return null;}
        return topSellingProducts.stream().map(ProductMapper :: toDto).collect(Collectors.toList());


    }

    private static List<OrderDTO> mapOrders(List<Order> recentOrder) {
        if (recentOrder == null || recentOrder.isEmpty()) {return null;}
        return recentOrder.stream().map(OrderMapper :: toDto).collect(Collectors.toList());
    }
}
