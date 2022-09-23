package com.ccut.dachuang.controller;

import com.ccut.dachuang.Exception.CustomizeException;
import com.ccut.dachuang.common.CommonResponse;
import com.ccut.dachuang.common.ErrorEnum;
import com.ccut.dachuang.model.VO.CurrentUser;
import com.ccut.dachuang.model.pojo.Address;
import com.ccut.dachuang.model.request.RequestAddress;
import com.ccut.dachuang.service.AddressService;
import com.ccut.dachuang.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/userInfo")
@Api(tags = "修改用户信息模块")
public class UserInfoController {
    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;

    @PutMapping("/updateUsername")
    @ApiOperation("修改昵称接口")
    @ApiImplicitParam(name = "newUsername",value = "传入新昵称",required = true,paramType = "path",dataType = "String",defaultValue = "360700")

    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 100, message = "参数为空"),
            @ApiResponse(code = 101, message = "用户名长度小于4"),
            @ApiResponse(code = 102, message = "用户名已存在"),
            @ApiResponse(code = 104, message = "密码长度小于6"),
            @ApiResponse(code = 105, message = "用户名包含敏感词"),
            @ApiResponse(code = 117, message = "请输入新昵称"),
    })
    public CommonResponse<Boolean> updateUsername(HttpServletRequest request, @RequestParam("newUsername")  String  newUsername  ){

        return   userService.updateUsername(request,newUsername);
    }

    @PostMapping("/addAddress")
    @ApiOperation("添加地址接口")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 119, message = "地址添加失败")
    })
    public CommonResponse<Boolean> addAddress(@RequestBody RequestAddress requestAddress,HttpServletRequest httpServletRequest){
        return   addressService.addAdress(requestAddress,httpServletRequest);
    }


    @DeleteMapping("/deleteAddress")
    @ApiOperation("删除地址接口")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 120, message = "地址删除失败")
    })
    public CommonResponse<Boolean> deleteAddress(@RequestBody  Address deleteAddress){
        return   addressService.deleteAdress(deleteAddress);
    }

    @PutMapping("/updateAddress")
    @ApiOperation("修改地址接口")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 121, message = "地址修改失败")
    })
    public CommonResponse<Boolean> updateAddress(@RequestBody Address updateAddress){
        return  addressService.updateAdress(updateAddress);
    }

    @GetMapping("/getAddress")
    @ApiOperation("获取地址接口")
    @ApiResponses({
            @ApiResponse(code = 200, message = "获取地址成功"),
            @ApiResponse(code = 122, message = "获取地址失败"),
    })
    public CommonResponse<List<Address>> getAddress(){
        return  addressService.getAdress();
    }

    @GetMapping("/getCurrentUser")
    @ApiOperation("获取当前的用户信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "获取当前用户成功"),
    })
    public  CommonResponse<CurrentUser>  getCurrentUser(HttpServletRequest request){
        return userService.getCurrentUser(request);
    }
}
