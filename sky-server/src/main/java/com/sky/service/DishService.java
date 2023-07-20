package com.sky.service;

import com.sky.dto.DishPageDTO;
import com.sky.result.PageResult;

public interface DishService {
    /**
     * 分页查询菜品数据
     * @param dishPageDTO
     * @return
     */
    PageResult page(DishPageDTO dishPageDTO);
}
