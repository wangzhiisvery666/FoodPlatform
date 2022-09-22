package com.ccut.dachuang.utils;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Random;

import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;


import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 阿里云 OSS工具类
 *
 * @author Monkey
 * @date 2017年9月30日下午3:38:09
 * @version 1.0
 */
@Controller
public class OSSClientUtil {


	public static String getcontentType(String filenameExtension) {
		if (filenameExtension.equalsIgnoreCase("bmp")) {
			return "image/bmp";
		}
		if (filenameExtension.equalsIgnoreCase("gif")) {
			return "image/gif";
		}
		if (filenameExtension.equalsIgnoreCase("jpeg") || filenameExtension.equalsIgnoreCase("jpg")
				|| filenameExtension.equalsIgnoreCase("png")) {
			return "image/jpeg";
		}
		if (filenameExtension.equalsIgnoreCase("html")) {
			return "text/html";
		}
		if (filenameExtension.equalsIgnoreCase("txt")) {
			return "text/plain";
		}
		if (filenameExtension.equalsIgnoreCase("vsd")) {
			return "application/vnd.visio";
		}
		if (filenameExtension.equalsIgnoreCase("pptx") || filenameExtension.equalsIgnoreCase("ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (filenameExtension.equalsIgnoreCase("docx") || filenameExtension.equalsIgnoreCase("doc")) {
			return "application/msword";
		}
		if (filenameExtension.equalsIgnoreCase("xml")) {
			return "text/xml";
		}
		return "image/jpeg";
	}
}