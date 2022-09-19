package com.ccut.dachuang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccut.dachuang.common.CommonResponse;
import com.ccut.dachuang.model.VO.Title2;
import com.ccut.dachuang.model.VO.Title3;
import com.ccut.dachuang.model.pojo.Menu;
import com.ccut.dachuang.service.MenuService;
import com.ccut.dachuang.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author o'k
* @description 针对表【menu】的数据库操作Service实现
* @createDate 2022-09-06 18:57:48
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService{

    @Autowired
    MenuMapper menuMapper;


    @Override
    public CommonResponse<Menu> getMenu() {

        return null;
    }


   public List<Title3> getTitle3(){
       List<Title2> allById = menuMapper.getAllById();
       return menuMapper.getTitle3ByParentId();
   }




}




