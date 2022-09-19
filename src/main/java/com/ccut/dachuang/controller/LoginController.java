package com.ccut.dachuang.controller;

import com.ccut.dachuang.common.CommonResponse;
import com.ccut.dachuang.model.VO.CompleteLoginVo;
import com.ccut.dachuang.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/login")
@Api(tags = "登陆模块")
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/verify")
    @ApiResponses({

            @ApiResponse(code = 108, message = "用户名为空"),
            @ApiResponse(code = 109, message = "密码为空"),
            @ApiResponse(code = 110, message = "用户名或密码错误"),
    })
    public CommonResponse<CompleteLoginVo>  doLogin(@Param("username") String username,@Param("password") String password){

        return  userService.login(username, password);
    }

}