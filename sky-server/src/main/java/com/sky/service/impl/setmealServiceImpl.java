package com.sky.service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageDTO;
import com.sky.entity.Employee;
import com.sky.mapper.setmealMapper;
import com.sky.result.PageResult;
import com.sky.service.setmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 套餐接口实现
 */
@Service
public class setmealServiceImpl implements setmealService{

    @Autowired
    private  setmealMapper setmealMapper;
    /**
     * 套餐分页查询
     * @param setmealPageDTO
     * @return
     */
    @Override
    public PageResult page(SetmealPageDTO setmealPageDTO) {

        SetmealDTO setmealDTO=new SetmealDTO();
        PageHelper.startPage(setmealPageDTO.getPage(),setmealPageDTO.getPageSize());
        Page<SetmealDTO> page=setmealMapper.pageQuery(setmealPageDTO);
        long total = page.getTotal();
        List<SetmealDTO> records = page.getResult();
        return new PageResult(total,records);
    }
}
