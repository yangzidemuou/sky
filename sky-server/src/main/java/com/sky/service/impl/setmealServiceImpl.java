package com.sky.service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.ThreadLocalUtil;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.BusinessException;
import com.sky.mapper.setmealMapper;
import com.sky.result.PageResult;
import com.sky.service.setmealService;
import com.sky.vo.SetmealVO;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
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
        PageHelper.startPage(setmealPageDTO.getPage(),setmealPageDTO.getPageSize());
        Page<SetmealVO> page=setmealMapper.pageQuery(setmealPageDTO);
        long total = page.getTotal();
        List<SetmealVO> records = page.getResult();
        return new PageResult(total,records);
    }

    /**
     * 新增套餐
     * @param setmealDTO
     */
    @Override
    public void add(SetmealDTO setmealDTO) {
        Setmeal setmeal=new Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);

        setmeal.setCreateTime(LocalDateTime.now());
        setmeal.setCreateUser(ThreadLocalUtil.getCurrentId());

        List<SetmealDish> setmealDishes=setmealDTO.getSetmealDishes();

        setmealMapper.addSetmeal(setmeal);

        Long setmealId=setmeal.getId();

        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmealId);
        });

        setmealMapper.addSetmealDish(setmealDishes);
    }

    /**
     * 修改套餐状态
     * @param status
     * @param id
     */
    @Override
    public void changeStatus(Integer status, Long id) {
        Setmeal setmeal=new Setmeal();
        setmeal.setStatus(status);
        setmeal.setId(id);
        setmeal.setUpdateTime(LocalDateTime.now());
        setmeal.setUpdateUser(ThreadLocalUtil.getCurrentId());
        setmealMapper.update(setmeal);
    }

    @Override
    public void update(SetmealDTO setmealDTO) {

        Setmeal setmeal=new Setmeal();

        BeanUtils.copyProperties(setmealDTO,setmeal);

        setmeal.setUpdateUser(ThreadLocalUtil.getCurrentId());
        setmeal.setUpdateTime(LocalDateTime.now());

        System.out.println(setmeal);

        setmealMapper.update(setmeal);
        List<SetmealDish> setmealDishes=setmealDTO.getSetmealDishes();

        if (!setmealDishes.isEmpty()){
            setmealMapper.updateSetmealDish(setmealDishes);
        }
    }

    @Override
    public SetmealVO selectById(Long id) {

        Setmeal setmeal= setmealMapper.selectSetmealById(id);

        List<SetmealDish> setmealDishes = setmealMapper.selectBySetmealId(id);

        SetmealVO setmealVO=new SetmealVO();

        BeanUtils.copyProperties(setmeal,setmealVO);

        setmealVO.setSetmealDishes(setmealDishes);

        return setmealVO;
    }

    @Override
    public void delete(Long[] ids) {
        if(ids == null || ids.length == 0){
            throw new BusinessException(400,"未选择要删除的套餐");
        }

        setmealMapper.delete(ids);

        setmealMapper.deleteSetmealDish(ids);
        //遍历套餐id,删除套餐、套餐菜品
//        Arrays.stream(ids).forEach(setmealId ->{
//            //删除套餐
//            setmealMapper.delete(setmealId);
//            //删除套餐菜品
//            setmealDishMapper.deleteBySid(setmealId);
//        });
    }
}
