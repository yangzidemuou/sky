package com.sky.service;


import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageDTO;
import com.sky.result.PageResult;
import io.swagger.models.auth.In;

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

    void changeStatus(Integer status, Long id);
}
