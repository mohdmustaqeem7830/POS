package com.zosh.zosh.pos.system.Services.Impl;

import com.zosh.zosh.pos.system.Model.Category;
import com.zosh.zosh.pos.system.Model.Product;
import com.zosh.zosh.pos.system.Model.Store;
import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Payload.dto.ProductDTO;
import com.zosh.zosh.pos.system.Repository.CategoryRepository;
import com.zosh.zosh.pos.system.Repository.ProductRepository;
import com.zosh.zosh.pos.system.Repository.StoreRepository;
import com.zosh.zosh.pos.system.Services.ProductServices;
import com.zosh.zosh.pos.system.mapper.CategoryMapper;
import com.zosh.zosh.pos.system.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductServices {

    private  final ProductRepository productRepository;
    private  final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public ProductDTO createProduct(ProductDTO productDTO, User user) throws Exception {

        Store store = storeRepository.findById(productDTO.getStoreId()).orElseThrow(
                ()-> new Exception("Store Not Found")
        );


        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(
                ()-> new Exception("Category Not Found")
        );

        Product product = ProductMapper.toEntity(productDTO,store,category);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception("Product Not Found")
        );



        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setSku(productDTO.getSku());
        product.setImage(productDTO.getImage());
        product.setMrp(productDTO.getMrp());
        product.setSellingPrice(productDTO.getSellingPrice());
        product.setBrand(productDTO.getBrand());
        product.setUpdatedAt(LocalDateTime.now());

        if (productDTO.getCategoryId()!=null) {
            Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(
                    ()-> new Exception("Category Not Found")
            );

            product.setCategory(category);

        }
       Product saveProduct = productRepository.save(product);
        return ProductMapper.toDto(saveProduct);
    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {

        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception("Product not found")
        );

        productRepository.delete(product);
    }

    @Override
    public List<ProductDTO> getProductByStoreId(Long storeId) {

        List<Product> products = productRepository.findByStoreId(storeId);

        return products.stream().map(ProductMapper::toDto).collect(Collectors.toList());

    }

    @Override
    public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {
        List<Product> products = productRepository.searchByKeyword(storeId,keyword);

        return products.stream().map(ProductMapper::toDto).collect(Collectors.toList());

    }
}
