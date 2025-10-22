package com.zosh.zosh.pos.system.controller;

import com.stripe.service.RefundService;
import com.zosh.zosh.pos.system.Payload.dto.RefundDTO;
import com.zosh.zosh.pos.system.Services.RefundServices;
import com.zosh.zosh.pos.system.mapper.RefundMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/refunds")
public class RefundController {

   private final RefundServices refundServices;


    @PostMapping
    public ResponseEntity<RefundDTO> createRefunds(@RequestBody RefundDTO refundDTO) throws Exception {

        RefundDTO refund = refundServices.createRefund(refundDTO);
        return ResponseEntity.ok(refund);
    }


    @GetMapping
    public ResponseEntity<List<RefundDTO>> getAllRefunds() throws Exception {

        List<RefundDTO> refund = refundServices.getAllRefunds();
        return ResponseEntity.ok(refund);
    }



    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<RefundDTO>> getRefundsByCashier(@PathVariable Long cashierId) throws Exception {

        List<RefundDTO> refund = refundServices.getRefundByCashier(cashierId);
        return ResponseEntity.ok(refund);
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<RefundDTO>> getRefundsByBranch(@PathVariable Long branchId) throws Exception {

        List<RefundDTO> refund = refundServices.getRefundByBranch(branchId);
        return ResponseEntity.ok(refund);
    }


    @GetMapping("/shift/{shiftId}")
    public ResponseEntity<List<RefundDTO>> getRefundsByShiftId(@PathVariable Long shiftId) throws Exception {

        List<RefundDTO> refund = refundServices.getRefundByShiftReport(shiftId);
        return ResponseEntity.ok(refund);
    }


    @GetMapping("/cashier/{cashierId}/range")
    public ResponseEntity<List<RefundDTO>> getRefundsByCashierIdAndDataRange(@PathVariable Long cashierId
            ,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate
            ,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate

    ) throws Exception {

        List<RefundDTO> refund = refundServices.getRefundsByCashierAndDateRange(cashierId,startDate,endDate);
        return ResponseEntity.ok(refund);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<RefundDTO> getRefundById(@PathVariable Long Id) throws Exception {

        return ResponseEntity.ok(refundServices.getRefundById(Id));

    }

}
