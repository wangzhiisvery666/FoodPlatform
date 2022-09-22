package com.ccut.dachuang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccut.dachuang.Exception.CustomizeException;


import com.ccut.dachuang.common.CommonResponse;
import com.ccut.dachuang.common.ErrorEnum;
import com.ccut.dachuang.mapper.UserMapper;

import com.ccut.dachuang.model.VO.CompleteLoginVo;
import com.ccut.dachuang.model.VO.RegisterVo;
import com.ccut.dachuang.model.pojo.User;
import com.ccut.dachuang.service.UserService;
import com.ccut.dachuang.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.concurrent.TimeUnit;

/**
* @author o'k
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-09-04 17:10:00
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public CommonResponse<Boolean> addUser(RegisterVo registerVo) {

        //如果参数错误直接异常
        if (registerVo ==null){
            throw new CustomizeException(ErrorEnum.PARAMETER_EXCEPTION_NULL);
        }
        User user = new User();
        String username = registerVo.getUsername();
        String phoneNumber = registerVo.getPhoneNumber();
        //加密后放入数据库
        String password = CommonUtils.md5( registerVo.getPassword());
        user.setUsername(username);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);


        //检查用户名和手机号
        checkUsernameAndPhoneNumberAndPassword(username,password, phoneNumber);

         String key=username + ":" + phoneNumber;
         log.info("==============={}",key);
        //放入 redis
        String code = redisTemplate.opsForValue().get(key);
        log.info("==============={}",code);
        log.info("=============={}", registerVo.getCode());
        if (code!=null&&code.equals(registerVo.getCode())){
            log.info("验证码验证成功");
        }else {
            throw new CustomizeException(ErrorEnum.VERIFICATION_CODE_ERROR);
        }


        //插入数据库
        int insert = userMapper.insert(user);
        if (insert==0){
            throw new CustomizeException(ErrorEnum.SQL_ERROR);
        }
        log.info("==============成功注册一名用户=================");
        return new CommonResponse<>("success",true,"200");

    }

    @Override
    public CommonResponse<String> getVerificationCode(String username, String password,String phoneNumber) {
        checkUsernameAndPhoneNumberAndPassword(username, password, phoneNumber);


        String code = CommonUtils.geCode();
        String key=username+":"+phoneNumber;
//        redisTemplate.opsForValue().set(key,code,180);
        redisTemplate.opsForValue().set(key,code,180, TimeUnit.SECONDS);
        log.info("==================验证码获取成功:{}  过期时间为3分钟",code);
        return new CommonResponse<>("验证码获取成功请,在三分钟之内完成注册",code,"200");
    }

    /**
     * 登陆方法
     */
    @Override
    public CommonResponse<CompleteLoginVo> login(String username,String password, HttpServletRequest session) {


      if (StringUtils.isEmpty(username)){
          throw new CustomizeException(ErrorEnum.USERNAME_IS_EMPTY);
      }

      if (StringUtils.isEmpty(password)){
          throw new CustomizeException(ErrorEnum.PASSWORD_IS_EMPTY);
      }
      
          // TODO 此处注意 要加上md5
        String pw = CommonUtils.md5(password);

        User user =null;
      try {
            user = userMapper.userIsExist(username, pw);
      }catch (RuntimeException e){
          throw e;
      }

      if (user==null){
          throw new CustomizeException(ErrorEnum.WRONG_USER_NAME_OR_PASSWORD);
      }

        CompleteLoginVo completeLoginVo = new CompleteLoginVo();
        completeLoginVo.setName(user.getUsername());
        completeLoginVo.setAvatar(user.getAvatar());

        session.getSession().setAttribute(user.getUsername(),completeLoginVo);
        log.info("==============Session中{}",session.getSession().getAttribute(user.getUsername()));
        log.info("============欢迎: {} 的登陆==========",user.getUsername());
      return new CommonResponse<>("登陆成功",completeLoginVo,"200");
    }

    /**
     * 用户退出登录
     */
    @Override
    public CommonResponse<Boolean> logOut(HttpServletRequest session,String username) {
        session.getSession().removeAttribute(username);

        return new CommonResponse<>("退出成功",true,"200");
    }


    /**
     * 检查用户名和密码的方法
     */
    public void checkUsernameAndPhoneNumberAndPassword(String username,String password, String phoneNumber){

        //1、非空
        if (StringUtils.isAnyEmpty(username ,  password , phoneNumber)){
            throw new CustomizeException(ErrorEnum.PARAMETER_EXCEPTION_NULL);
        }
        //2、账户长度不得小于4
        if (username.length()<4){
            throw new CustomizeException(ErrorEnum.USER_REGISTRATION_ERROR);
        }
        //3、密码长度不得小于6
        if (password.length()<6){
            throw new  CustomizeException(ErrorEnum.SHORT_PASSWORD_LENGTH);
        }

        //4、检查账户特殊字符
        //账户由字母、数字、下划线 组成
        String regex="^[0-9a-zA-Z_]{1,}$";
        if (!username.matches(regex)){
            throw new CustomizeException(ErrorEnum.USERNAME_CONTAINS_SENSITIVE_WORDS);
        }


        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("USERNAME", username);

        boolean exists = userMapper.exists(userQueryWrapper);
        if (exists){
            log.info("用户名已经存在");
            throw new CustomizeException(ErrorEnum.USERNAME_EXIST);
        }

        QueryWrapper<User> userQueryWrapper1 = new QueryWrapper<>();
        userQueryWrapper1.eq("PHONE_NUMBER", phoneNumber);
        exists = userMapper.exists(userQueryWrapper1);
        if (exists){
            log.info("该手机号已绑定账号");
            throw new CustomizeException(ErrorEnum.PHONE_NUMBER_ALREADY_BOUND);
        }
    }
}




