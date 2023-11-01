package com.sky.controller.user;

import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user/shop")
@Slf4j

public class UserShopController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @GetMapping("/status")
    public Result<Integer> getStatus(){

        Integer status = (Integer)redisTemplate.opsForValue().get("SHOP_STATUS");
        Optional<Integer> optionalI=Optional.ofNullable(status);
        int value = optionalI.orElse(0);
        log.info("获取到的状态为：{}",value == 1?"营业中":"打样中");
        return Result.success(status);
    }

}
