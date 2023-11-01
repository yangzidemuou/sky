package com.sky.controller.admin;

import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/admin/shop")
public class ShopController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @PutMapping("/{status}")
    public Result<String> setStatus(@PathVariable Integer status){
      log.info("设置店铺状态为：{}",status==1?"营业中":"打样中");
      redisTemplate.opsForValue().set("SHOP_STATUS",status);
      return Result.success();
    }


    @GetMapping("/status")
    public Result<Integer> getStatus(){

        Integer status = (Integer)redisTemplate.opsForValue().get("SHOP_STATUS");
        Optional<Integer> optionalI=Optional.ofNullable(status);
        int value = optionalI.orElse(0);
        log.info("获取到的状态为：{}",value == 1?"营业中":"打样中");
        return Result.success(status);
    }

}
