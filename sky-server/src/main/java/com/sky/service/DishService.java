package com.sky.service;

import com.sky.dto.DishPageDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    /**
     * 分页查询菜品数据
     * @param dishPageDTO
     * @return
     */
    PageResult page(DishPageDTO dishPageDTO);

    /**
     * 菜品起售停售
     * @param status
     * @param id
     */
    void startAndStopDish(Integer status, Long id);

    /**
     * 通过类型获取菜品信息
     * @param categoryId
     * @return
     */
    List<Dish> selectByTypeDish(Integer categoryId);

    /**
     * 通过id获得菜品信息
     * @param id
     * @return
     */
    List<DishVO> selectByIdDish(Long id);

    /**
     * 新增菜品
     * @param dishVO
     */
    void addDish(DishVO dishVO);

    void deleteBatch(Long[] ids);

    void changeDish(DishVO dishVO);

}
