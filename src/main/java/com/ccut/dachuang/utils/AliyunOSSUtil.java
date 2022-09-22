//package com.ccut.dachuang.utils;
//
//import com.aliyun.oss.ClientException;
//import com.aliyun.oss.OSSClient;
//import com.aliyun.oss.OSSException;
//import com.aliyun.oss.model.CannedAccessControlList;
//import com.aliyun.oss.model.CreateBucketRequest;
//import com.aliyun.oss.model.PutObjectRequest;
//import com.aliyun.oss.model.PutObjectResult;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.io.File;
//
///**
// * 阿里OSS工具类
// *
// * @author CYY
// * @date 2022/4/15 16:23
// */
//@Component
//@Log4j2
//public class AliyunOSSUtil {
//    @Value("${oss.bucketname}")
//    String bucketName;
//    @Resource
//    private OSSClient client;
//
//    /**
//     * 上传文件
//     */
//    public String upLoad(File file, String fileUrl) {
//        log.info("------OSS文件上传开始--------" + file.getName());
//        // 判断文件是否为空
//        if (file == null) {
//            return null;
//        }
//
//        try {
//            // 判断容器是否存在,不存在就创建
//            if (!client.doesBucketExist(bucketName)) {
//                client.createBucket(bucketName);
//                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
//                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
//                client.createBucket(createBucketRequest);
//                // 设置权限(公开读)
//                client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
//            }
//            // 上传文件
//            PutObjectResult result = client.putObject(new PutObjectRequest(bucketName, fileUrl, file));
//            if (result != null) {
//                log.info("------OSS文件上传成功------" + fileUrl);
//                return fileUrl;
//            }
//        } catch (OSSException oe) {
//            log.error(oe.getMessage());
//        } catch (ClientException ce) {
//            log.error(ce.getErrorMessage());
//        } finally {
//        }
//        return null;
//    }
//}
