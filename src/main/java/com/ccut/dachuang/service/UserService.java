package com.ccut.dachuang.service;

import com.ccut.dachuang.common.CommonResponse;
import com.ccut.dachuang.model.VO.CompleteLoginVo;
import com.ccut.dachuang.model.VO.RegisterVo;
import com.ccut.dachuang.model.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author o'k
* @description 针对表【user】的数据库操作Service
* @createDate 2022-09-04 17:10:00
*/
public interface UserService extends IService<User> {

    CommonResponse<Boolean> addUser(RegisterVo registerVo);

    CommonResponse<String>  getVerificationCode(String username,String password, String phoneNumber);

    CommonResponse<CompleteLoginVo>  login(String username,String password);
}
