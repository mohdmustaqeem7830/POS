package com.zosh.zosh.pos.system.controller;

import com.zosh.zosh.pos.system.Model.Store;
import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Payload.Response.ApiResponse;
import com.zosh.zosh.pos.system.Payload.dto.ProductDTO;
import com.zosh.zosh.pos.system.Services.ProductServices;
import com.zosh.zosh.pos.system.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductServices productServices;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO,@RequestHeader("Authorization") String jwt) throws Exception {

        User user    = userService.getUserFromJwtToken(jwt);
        return  ResponseEntity.ok(
                productServices.createProduct(
                        productDTO,user
                )
        );
    }
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<ProductDTO>> getProductByStoreId(@PathVariable Long storeId ,@RequestHeader("Authorization") String jwt) throws Exception {


        return  ResponseEntity.ok(
                productServices.getProductByStoreId(
                       storeId
                )
        );
    }



    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO,@RequestHeader("Authorization") String jwt) throws Exception {


        User user    = userService.getUserFromJwtToken(jwt);
        return  ResponseEntity.ok(
                productServices.updateProduct(
                        id,productDTO,user
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws Exception {


        User user    = userService.getUserFromJwtToken(jwt);

        productServices.deleteProduct(
                id,user
        );

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Product Deleted Successfully");
        return  ResponseEntity.ok(
           apiResponse
        );
    }


    @GetMapping("/store/{storeId}/search")
    public ResponseEntity<List<ProductDTO>> getProductBySearchKeyword(@PathVariable Long storeId,@RequestParam String keyword ,@RequestHeader("Authorization") String jwt) throws Exception {


        return  ResponseEntity.ok(
                productServices.searchByKeyword(
                        storeId,keyword
                )
        );
    }

}
