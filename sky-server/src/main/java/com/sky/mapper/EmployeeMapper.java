package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    /**
     * 按姓名查找员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{usernmae}")
    Employee getByUsername(String username);

    /**
     * 插入员工
     * @param employee
     */
    @Insert("insert into employee (name,username,password,phone,sex,id_number,create_time,update_time,create_user,update_user,status)"+
            "values" +
            "(#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{createTime},#{updateTime},#{createUser},#{updateUser},#{status})")
    void insert(Employee employee);

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 更新数据
     * @param employee
     */
    void update(Employee employee);

    /**
     *
     * @param id
     * @return
     */
    @Select("select * from employee where id = #{id}")
    Employee SelectEmployeeById(Long id);
}
