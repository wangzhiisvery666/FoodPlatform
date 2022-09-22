package com.ccut.dachuang.controller;

import com.ccut.dachuang.common.CommonResponse;
import com.ccut.dachuang.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Api(tags = "上传文件模块")
@RequestMapping("/upload")
@RestController
@Slf4j
public class FileController {

    @Autowired
    FileService fileService;

    //文件上传
    @PostMapping("/Avatar")
    @ApiOperation("上传头像接口")
    public CommonResponse<String> transAvatar(@RequestPart("img") MultipartFile file, HttpServletRequest request) throws IOException {

        return fileService.sendAvatar(file, request);
    }

}
