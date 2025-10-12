package com.zosh.zosh.pos.system.controller;


import com.zosh.zosh.pos.system.Domain.StoreStatus;
import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Payload.Response.ApiResponse;
import com.zosh.zosh.pos.system.Payload.dto.StoreDTO;
import com.zosh.zosh.pos.system.Services.StoreService;
import com.zosh.zosh.pos.system.Services.UserService;
import com.zosh.zosh.pos.system.mapper.StoreMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;
    private  final UserService userService;


    @PostMapping()
    public ResponseEntity<StoreDTO> createStore(@RequestBody StoreDTO storeDTO, @RequestHeader ("Authorization") String jwt) throws Exception{

        User user = userService.getUserFromJwtToken(jwt);

        return ResponseEntity.ok(storeService.createStore(storeDTO,user));
    }



    @GetMapping()
    public ResponseEntity<List<StoreDTO>> getAllStore(@RequestHeader ("Authorization") String jwt) throws Exception{
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/admin")
    public ResponseEntity<StoreDTO> getStoreByAdmin(@RequestHeader ("Authorization") String jwt) throws Exception{
        return ResponseEntity.ok(StoreMapper.toDto(storeService.getStoreByAdmin()));
    }
   @GetMapping("/employee")
    public ResponseEntity<StoreDTO> getStoreByEmployee(@RequestHeader ("Authorization") String jwt) throws Exception{
        return ResponseEntity.ok(storeService.getStoreByEmployee());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> updateStore(@PathVariable Long id , @RequestBody StoreDTO storeDTO)throws Exception{
        return  ResponseEntity.ok(storeService.updateStore(id,storeDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getStoreById(@PathVariable Long id, @RequestHeader ("Authorization") String jwt) throws Exception{
        return ResponseEntity.ok(storeService.getStoreById(id));
    }
  @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id )throws Exception{
        storeService.deleteStore(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Store Deleted Successfully");
        return  ResponseEntity.ok(apiResponse );
    }


    @PutMapping("/{id}/moderate")
    public ResponseEntity<StoreDTO> moderateStore(@PathVariable Long id , @RequestParam StoreStatus storeStatus)throws Exception{
        return  ResponseEntity.ok(storeService.moderateStore(id,storeStatus));
    }




}
