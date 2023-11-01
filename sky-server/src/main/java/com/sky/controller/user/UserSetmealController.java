package com.sky.controller.user;

import com.sky.dto.SetmealDTO;
import com.sky.result.Result;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/user/setmeal")
public class UserSetmealController {
    public Result<SetmealVO> getSetmeal(SetmealDTO setmealDTO){
        log.info("根据分类id查询套餐:{}",setmealDTO);


        return Result.success();
    }
}
