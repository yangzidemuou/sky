package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface setmealMapper {


    Page<SetmealVO> pageQuery(SetmealPageDTO setmealPageDTO);

    void addSetmeal(Setmeal setmeal);

    void addSetmealDish(List<SetmealDish> setmealDishes);

    void update(Setmeal setmeal);

    void updateSetmealDish(List<SetmealDish> setmealDishes);
    @Select("select * from setmeal where id=#{id}")
    Setmeal selectSetmealById(Long id);
    @Select("select * from setmeal_dish where setmeal_id=#{id}")
    List<SetmealDish> selectBySetmealId(Long id);

    void delete(Long[] ids);

    void deleteSetmealDish(Long[] ids);
}
