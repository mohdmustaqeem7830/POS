package com.zosh.zosh.pos.system.Services;

import com.zosh.zosh.pos.system.Model.Category;
import com.zosh.zosh.pos.system.Payload.dto.CategoryDTO;

import java.util.List;

public interface CategoryServices {
  CategoryDTO createCategory(CategoryDTO categoryDTO) throws Exception;
  List<CategoryDTO> getCategoriesByStore(Long storeId);
  CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) throws Exception;
  void deeleteCategory(Long id) throws Exception;

}
