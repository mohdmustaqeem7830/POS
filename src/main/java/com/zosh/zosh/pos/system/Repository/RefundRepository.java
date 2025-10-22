package com.zosh.zosh.pos.system.Repository;

import com.zosh.zosh.pos.system.Model.Refund;
import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Payload.dto.RefundDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RefundRepository extends JpaRepository<Refund,Long> {

    List<Refund> findByCashierIdAndCreatedAtBetween(
            Long cashierId,
            LocalDateTime from,
            LocalDateTime to
    );

    List<Refund> findByCashierId(Long id);
   List<Refund> findByShiftReportId(Long id);
   List<Refund> findByBranchId(Long id);




}
