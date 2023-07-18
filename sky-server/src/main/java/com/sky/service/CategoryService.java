package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.result.PageResult;

public interface CategoryService {
    PageResult page(CategoryDTO categoryDTO);
}
