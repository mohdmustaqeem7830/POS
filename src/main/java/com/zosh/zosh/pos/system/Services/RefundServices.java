package com.zosh.zosh.pos.system.Services;

import com.zosh.zosh.pos.system.Payload.dto.RefundDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundServices {

    RefundDTO createRefund(RefundDTO refundDTO) throws Exception;
    List<RefundDTO> getAllRefunds() throws Exception;
    List<RefundDTO> getRefundByCashier(Long cashierId) throws Exception;
    List<RefundDTO> getRefundByShiftReport (Long shiftReportId) throws Exception;
    List<RefundDTO> getRefundsByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) throws Exception;
    List<RefundDTO> getRefundByBranch(Long branchId) throws Exception;

    RefundDTO getRefundById(Long refundId) throws Exception;

    void deleteRefund(Long refundId) throws Exception;


}
