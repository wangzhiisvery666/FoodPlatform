package com.ccut.dachuang.service;


import com.ccut.dachuang.common.CommonResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Service
public interface FileService {

    CommonResponse<String> sendAvatar(MultipartFile file, HttpServletRequest request);
}
