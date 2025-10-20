package com.zosh.zosh.pos.system.Services;

import com.zosh.zosh.pos.system.Domain.UserRole;
import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Payload.dto.UserDto;

import java.util.List;

public interface EmployeeServices {

    UserDto createStoreEmployee(UserDto employee,Long storeId) throws Exception;
    UserDto createBranchEmployee(UserDto employee,Long branchId) throws Exception;
    User updateEmployee(Long employeeId,UserDto employeeDetails) throws Exception;
    void deleteEmployee(Long employeeId) throws Exception;
    List<UserDto> findStoreEmployee(Long storeId, UserRole role) throws Exception;
    List<UserDto> findBranchEmployee(Long branchId, UserRole role) throws Exception;
}
