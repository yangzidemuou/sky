package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    /**
     * 分页查询分类
     * @param categoryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryDTO categoryDTO);
}
