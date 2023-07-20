package com.sky.service;


import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageDTO;
import com.sky.result.PageResult;

public interface setmealService {
    /**
     * 套餐分页查询
     * @param setmealPageDTO
     * @return
     */
    PageResult page(SetmealPageDTO setmealPageDTO);

    /**
     * 新增套餐
     * @param setmealDTO
     */
    void add(SetmealDTO setmealDTO);
}
