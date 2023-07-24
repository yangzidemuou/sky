package com.sky.utils;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.model.ObjectMetadata;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "sky.huaweiobs")
public class FileUploadUtil {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    public  String upload(MultipartFile file) throws IOException {

        ObsClient obsClient = new ObsClient(accessKeyId, accessKeySecret, endpoint);

        String fileName = file.getOriginalFilename();
        InputStream inputStream=file.getInputStream();

        String objectName = "images/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date())
                + "/" + System.currentTimeMillis() +"-"+fileName;
        try{
            ObjectMetadata objectMetadata=new ObjectMetadata();
            objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
            obsClient.putObject(bucketName, objectName, inputStream,objectMetadata);
        }catch (ObsException e){
            System.out.println("Error Message:" + e.getErrorMessage());
        }finally {
            obsClient.close();
        }
        //文件访问路径规则 https://BucketName.Endpoint/ObjectName
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(bucketName)
                .append(".")
                .append(endpoint)
                .append("/")
                .append(objectName);

        log.info("文件上传到:{}", stringBuilder.toString());

        return stringBuilder.toString();

    }


    private String getContentType(String filenameExtension) {

        if (filenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase(".jpeg") ||
                filenameExtension.equalsIgnoreCase(".jpg") ||
                filenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        return "image/jpg";
    }
}
