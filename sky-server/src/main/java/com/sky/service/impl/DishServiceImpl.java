package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.ThreadLocalUtil;
import com.sky.dto.DishPageDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    /**
     * 分页查询菜品数据
     * @param dishPageDTO
     * @return
     */
    @Override
    public PageResult page(DishPageDTO dishPageDTO) {
        PageHelper.startPage(dishPageDTO.getPage(),dishPageDTO.getPageSize());
        Page<DishVO> page=dishMapper.pageQuery(dishPageDTO);
        long total = page.getTotal();
        List<DishVO> records = page.getResult();
        return new PageResult(total,records);
    }

    /**
     * 菜品起售停售
     * @param status
     * @param id
     */
    @Override
    public void startAndStopDish(Integer status, Long id) {
        Dish dish=new Dish();
        dish.setId(id);
        dish.setStatus(status);
        dish.setUpdateTime(LocalDateTime.now());
        dish.setUpdateUser(ThreadLocalUtil.getCurrentId());
        dishMapper.update(dish);
    }

    /**
     * 根据菜品分类id查询
     * @param categoryId
     * @return
     */
    @Override
    public List<Dish> selectByTypeDish(Integer categoryId) {
        return dishMapper.selectByType(categoryId);
    }

    /**
     * 通过菜品id获取菜品信息
     * @param id
     * @return
     */
    @Override
    public List<DishVO> selectByIdDish(Long id) {
        return dishMapper.selectById(id);
    }

    /**
     * 新增菜品
     * @param dishVO
     */
    @Override
    public void addDish(DishVO dishVO) {
        System.out.println(dishVO);

        Dish dish=new Dish();
        BeanUtils.copyProperties(dishVO,dish);

        dish.setCreateTime(LocalDateTime.now());
        dish.setCreateUser(ThreadLocalUtil.getCurrentId());

        dishMapper.addDish(dish);



        List<DishFlavor> dishFlavors=dishVO.getFlavors();

        dishFlavors.forEach(dishFlavor -> {
            dishFlavor.setDishId(dish.getId());
        });

        dishMapper.addDishFlavors(dishFlavors);

    }

    @Override
    public void deleteBatch(Long[] ids) {
        if (ids.length>0){
            dishMapper.deleteBatch(ids);

            dishMapper.deleteDishFlavorBatch(ids);
        }
        else {
            System.out.println("ids异常");
        }


    }
}
