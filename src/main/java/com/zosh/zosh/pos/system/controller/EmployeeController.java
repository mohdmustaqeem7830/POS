package com.zosh.zosh.pos.system.controller;

import com.zosh.zosh.pos.system.Domain.UserRole;
import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Payload.Response.ApiResponse;
import com.zosh.zosh.pos.system.Payload.dto.UserDto;
import com.zosh.zosh.pos.system.Services.EmployeeServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController  {

    private final EmployeeServices employeeServices;


    @PostMapping("/store/{storeId}")
    public ResponseEntity<UserDto> createStoreEmployee(@PathVariable Long storeId, @RequestBody UserDto userDto) throws Exception {

        UserDto employee = employeeServices.createStoreEmployee(userDto,storeId);

        return ResponseEntity.ok(employee);
    }


    @PostMapping("/branch/{branchId}")
    public ResponseEntity<UserDto> createBranchEmployee(@PathVariable Long branchId, @RequestBody UserDto userDto) throws Exception {

        UserDto employee = employeeServices.createBranchEmployee(userDto,branchId);

        return ResponseEntity.ok(employee);
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateEmployee(@PathVariable Long id, @RequestBody UserDto userDto) throws Exception {

        User employee = employeeServices.updateEmployee(id,userDto);

        return ResponseEntity.ok(employee);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Long id) throws Exception {

       employeeServices.deleteEmployee(id);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Delete employee successfully");

        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/store/{id}")
    public ResponseEntity<List<UserDto>> storeEmployee(@PathVariable Long id, @RequestParam(required = false)UserRole userRole) throws Exception {

        List<UserDto> employee = employeeServices.findStoreEmployee(id,userRole);
        return ResponseEntity.ok(employee);

    }



    @GetMapping("/branch/{id}")
    public ResponseEntity<List<UserDto>> branchEmployee(@PathVariable Long id, @RequestParam(required = false)UserRole userRole) throws Exception {

        List<UserDto> employee = employeeServices.findBranchEmployee(id,userRole);
        return ResponseEntity.ok(employee);

    }

}
