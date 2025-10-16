package com.zosh.zosh.pos.system.Services;

import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Payload.dto.ProductDTO;

import java.util.List;

public interface ProductServices {


    ProductDTO createProduct (ProductDTO productDTO , User user) throws Exception;
    ProductDTO updateProduct (Long id , ProductDTO productDTO ,User user) throws Exception;
    void deleteProduct (Long id , User user) throws Exception;
    List<ProductDTO> getProductByStoreId(Long storeId);
    List<ProductDTO> searchByKeyword(Long storeId,String keyword);



}
