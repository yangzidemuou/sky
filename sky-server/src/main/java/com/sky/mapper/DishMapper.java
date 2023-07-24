package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.DishPageDTO;
import com.sky.entity.Dish;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {
    /**
     * 分页查询菜品数据
     * @param dishPageDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageDTO dishPageDTO);

    /**
     * 跟新菜品状态
     * @param dish
     */
    void update(Dish dish);
    @Select("select * from dish where category_id=#{categoryId}")
    List<Dish> selectByType(Integer categoryId);

    /**
     * 通过菜品id获取菜品信息
     * @param id
     * @return
     */
    @Select("select * from dish where id=#{id}")
    List<DishVO> selectById(Long id);


}

