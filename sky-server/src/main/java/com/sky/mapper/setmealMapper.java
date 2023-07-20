package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageDTO;
import com.sky.entity.Setmeal;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface setmealMapper {


    Page<SetmealVO> pageQuery(SetmealPageDTO setmealPageDTO);

    void add(SetmealDTO setmealDTO);
}
