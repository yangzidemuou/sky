package com.sky.controller.comment;

import com.sky.exception.BusinessException;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import com.sky.utils.FileUploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 通用接口操作
 */
@Slf4j
@RestController
@RequestMapping("/admin/common/")
@Api("通用接口操作操作")
public class UploadController {
    @Autowired
    private FileUploadUtil fileUploadUtil;
    @Autowired
    private AliOssUtil aliOssUtil;
    @PostMapping("/upload")
    @ApiOperation("图片上传")
    private Result<String> upload(MultipartFile file){
        //1. 判断文件是否为空 文件大小是否>0
        if(file == null || file.getSize() <= 0){
            throw new BusinessException(400,"请选择正确的文件上传");
        }
        //2. 完成文件上传
        String url = null;
        try {
            url = fileUploadUtil.upload(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //3. 返回图片的访问url
        return Result.success(url);
    }
}
