package com.sky.dto;

import lombok.Data;

@Data
public class EmployeePageQueryDTO {
    //员工姓名
    private String name;
    //页码
    private int page;
    //每页显示的记录数
    private int pageSize;
}
