package com.zosh.zosh.pos.system.controller;


import com.zosh.zosh.pos.system.Payload.Response.ApiResponse;
import com.zosh.zosh.pos.system.Payload.dto.CategoryDTO;
import com.zosh.zosh.pos.system.Services.CategoryServices;
import com.zosh.zosh.pos.system.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryServices categoryServices;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody  CategoryDTO categoryDTO) throws Exception {


        return  ResponseEntity.ok(categoryServices.createCategory(categoryDTO));
    }


    @GetMapping("store/{storeId}")
    public ResponseEntity<List<CategoryDTO>> getCategoriesByStoreId(@PathVariable Long storeId) throws Exception {

        return  ResponseEntity.ok(categoryServices.getCategoriesByStore(storeId));
    }


    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id ,@RequestBody  CategoryDTO categoryDTO) throws Exception {


        return  ResponseEntity.ok(categoryServices.updateCategory(id,categoryDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id , @RequestBody  CategoryDTO categoryDTO) throws Exception {


        categoryServices.deeleteCategory(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Category deleted successfully");


        return  ResponseEntity.ok(apiResponse);
    }

}
