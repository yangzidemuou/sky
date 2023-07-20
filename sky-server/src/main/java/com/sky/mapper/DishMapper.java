package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.DishPageDTO;
import com.sky.entity.Dish;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper {
    /**
     * 分页查询菜品数据
     * @param dishPageDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageDTO dishPageDTO);

    void update(Dish dish);

}

