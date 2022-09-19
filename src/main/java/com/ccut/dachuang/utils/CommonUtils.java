package com.ccut.dachuang.utils;

import com.ccut.dachuang.model.pojo.User;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * 加密类
 */
public class CommonUtils {

    /**
     * md5 加密
     * @param password :密码
     * @return :返回加密后的密码
     */
    public static String md5(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 脱敏方法
     *
     * @param user ：用户名
     * @return ：返回脱敏后的对象
     */
    public static User getSafetyUser(User user) {
        //6、脱敏：就是把敏感的信息隐藏掉、返回不敏感的信息给前端展示
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setAvatar(user.getAvatar());
        user1.setSex(user.getSex());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setCreateDate(user.getCreateDate());
        user1.setUpdateDate(user.getUpdateDate());
        return user1;
    }

    /**
     * 获取随机数的方法
     */
    public static String geCode(){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<6;i++)
        {
            stringBuilder.append(random.nextInt(10));
        }
       return  stringBuilder.toString();
    }
}
