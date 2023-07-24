package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.ThreadLocalUtil;
import com.sky.dto.CategoryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 分页查询分类
     * @param categoryDTO
     * @return
     */
    @Override
    public PageResult page(CategoryDTO categoryDTO) {
        PageHelper.startPage(categoryDTO.getPage(),categoryDTO.getPageSize());
        Page<Category> page=categoryMapper.pageQuery(categoryDTO);
        long total = page.getTotal();
        List<Category> records = page.getResult();
        return new PageResult(total,records);
    }

    /**
     * 添加分类
     * @param categoryDTO
     */
    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        category.setCreateTime(LocalDateTime.now());
        category.setCreateUser(ThreadLocalUtil.getCurrentId());
        categoryMapper.insert(category);
    }

    /**
     * 修改分类
     * @param categoryDTO
     */
    @Override
    public void changeCategory(CategoryDTO categoryDTO) {
        Category category=new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(ThreadLocalUtil.getCurrentId());
        categoryMapper.update(category);
    }

    /**
     * 修改分类状态
     * @param status
     * @param id
     */
    @Override
    public void startAndStopCategory(Integer status, Long id) {
        Category category=Category.builder()
                .status(status)
                .id(id)
                .updateTime(LocalDateTime.now())
                .updateUser(ThreadLocalUtil.getCurrentId())
                .build();
        categoryMapper.update(category);
    }

    /**
     * 根据id删除分类
     * @param id
     */
    @Override
    public void deleteCategory(Long id) {
        Category category=Category.builder()
                .id(id)
                .updateTime(LocalDateTime.now())
                .updateUser(ThreadLocalUtil.getCurrentId())
                .build();
        categoryMapper.delete(category);
    }

    /**
     * 根据type查询分类信息
     * @param type
     * @return
     */
    @Override
    public List<Category> selectCategoryByType(Integer type) {


        List<Category> list=categoryMapper.selectCategoryByType(type);

        return list;
    }
}
