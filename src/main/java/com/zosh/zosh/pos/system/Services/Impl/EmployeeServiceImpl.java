package com.zosh.zosh.pos.system.Services.Impl;

import com.zosh.zosh.pos.system.Domain.UserRole;
import com.zosh.zosh.pos.system.Model.Branch;
import com.zosh.zosh.pos.system.Model.Store;
import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Payload.dto.UserDto;
import com.zosh.zosh.pos.system.Repository.BranchRepository;
import com.zosh.zosh.pos.system.Repository.StoreRepository;
import com.zosh.zosh.pos.system.Repository.UserRepository;
import com.zosh.zosh.pos.system.Services.EmployeeServices;
import com.zosh.zosh.pos.system.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeServices {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception {

        Store store = storeRepository.findById(storeId).orElseThrow(

                () -> new Exception("Store not found")
        );

        Branch branch = null;

        if (employee.getRole() == UserRole.ROLE_BRANCH_MANAGER) {
            if (employee.getBranchId() == null) {
                throw new Exception("Branch Id is required");
            }
            branch = branchRepository.findById(employee.getBranchId()).orElseThrow(
                    () -> new Exception("Branch not found")
            );


        }


        User user = new User();
        user.setEmail(employee.getEmail());
        user.setFullName(employee.getFullName());
        user.setRole(employee.getRole());
        user.setStore(store);
        user.setPhone(employee.getPhone());
        user.setPassword(passwordEncoder.encode(employee.getPassword()));
        user.setBranch(branch);


        User savedUser = userRepository.save(user);

        if(employee.getRole() == UserRole.ROLE_BRANCH_MANAGER && branch!=null) {
            branch.setManager(savedUser);
            branchRepository.save(branch);
        }

        return UserMapper.toDto(savedUser);
    }



    @Override
    public UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception {

        Branch branch=branchRepository.findById(branchId).orElseThrow(
                () -> new Exception("Branch not found")
        );


        if(employee.getRole() == UserRole.ROLE_BRANCH_CASHIER ||  employee.getRole() == UserRole.ROLE_BRANCH_MANAGER) {

            User user = UserMapper.toEntity(employee);
            user.setEmail(employee.getEmail());
            user.setFullName(employee.getFullName());
            user.setRole(employee.getRole());
            user.setPhone(employee.getPhone());
            user.setBranch(branch);
            user.setPassword(passwordEncoder.encode(employee.getPassword()));
            return UserMapper.toDto(userRepository.save(user));
        }

        throw new Exception("Branch role not supported");
    }

    @Override
    public User updateEmployee(Long employeeId, UserDto employeeDetails) throws Exception {

        User existingEmployee = userRepository.findById(employeeId).orElseThrow(
                ()-> new Exception("Employee  not found")
        );

        Branch branch = branchRepository.findById(employeeDetails.getBranchId()).orElseThrow(
                ()-> new Exception("Branch not found")
        );

        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setFullName(employeeDetails.getFullName());
        existingEmployee.setPassword(employeeDetails.getPassword());
        existingEmployee.setRole(employeeDetails.getRole());
        existingEmployee.setBranch(branch);
        return userRepository.save(existingEmployee);

    }

    @Override
    public void deleteEmployee(Long employeeId) throws Exception {
             User user = userRepository.findById(employeeId).orElseThrow(
                     ()-> new Exception("Employee not found")
             );

             userRepository.delete(user);


    }

    @Override
    public List<UserDto> findStoreEmployee(Long storeId, UserRole role) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow(
                ()->new Exception("Store not found")
        );



        return userRepository.findByStore(store).stream().filter(user ->  role ==null || user.getRole()==role).map(UserMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findBranchEmployee(Long branchId, UserRole role) throws Exception {

        Branch branch = branchRepository.findById(branchId).orElseThrow(
                ()-> new Exception("Branch not found")
        );
      return   userRepository.findByBranchId(branchId)
              .stream().filter(
                      user-> role ==null || user.getRole()==role
              ).map(UserMapper :: toDto).collect(Collectors.toList());

    }
}
