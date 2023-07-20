package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageDTO;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface setmealMapper {


    Page<SetmealDTO> pageQuery(SetmealPageDTO setmealPageDTO);
}
