package com.zosh.zosh.pos.system.Services.Impl;

import com.zosh.zosh.pos.system.Domain.UserRole;
import com.zosh.zosh.pos.system.Exceptions.UserException;
import com.zosh.zosh.pos.system.Model.Category;
import com.zosh.zosh.pos.system.Model.Store;
import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Payload.dto.CategoryDTO;
import com.zosh.zosh.pos.system.Repository.CategoryRepository;
import com.zosh.zosh.pos.system.Repository.StoreRepository;
import com.zosh.zosh.pos.system.Services.CategoryServices;
import com.zosh.zosh.pos.system.Services.UserService;
import com.zosh.zosh.pos.system.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryServices {

    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private  final StoreRepository storeRepository;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) throws Exception {

        User user = userService.getCurrentUser();
        Store store = storeRepository.findById(categoryDTO.getStoreId()).orElseThrow(
                ()->new Exception("Store Not Found")
        );

        Category category = Category.builder()
                .store(store)
                .name(categoryDTO.getName())
                .build();


        checkAuthority(user,category.getStore());

        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDTO> getCategoriesByStore(Long storeId) {

        List<Category> categories = categoryRepository.findByStoreId(storeId);

        return categories.stream().map(CategoryMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new Exception("Category not found")
        );

        User user = userService.getCurrentUser();
        category.setName(categoryDTO.getName());

        checkAuthority(user,category.getStore());
        return CategoryMapper.toDTO(categoryRepository.save(category));

    }

    @Override
    public void deeleteCategory(Long id) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new Exception("Category not found")
        );

        User user = userService.getCurrentUser();

        checkAuthority(user,category.getStore());
        categoryRepository.delete(category);
    }


    private void checkAuthority(User user,Store store) throws UserException {
        boolean  isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore = user.equals(store.getStoreAdmin());

        if(!(isAdmin && isSameStore) && !isManager){
            throw new UserException("You have not permission to manage  Category");
        }
    }
}
