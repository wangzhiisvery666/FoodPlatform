package com.ccut.dachuang.controller;

import com.ccut.dachuang.model.VO.MenuVO;
import com.ccut.dachuang.model.VO.Title1;
import com.ccut.dachuang.model.VO.Title2;
import com.ccut.dachuang.model.VO.Title3;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;

@RestController
@ApiIgnore
@Api(tags = "菜单控制层")
@RequestMapping("/menu")
public class MenuController {
//
    @ApiOperation("获取菜单接口")
    @GetMapping("/menu")
    public MenuVO getMenu(){

        Title3 title31 = new Title3("鲁南糁汤");
        Title3 title32 = new Title3("云吞");
        Title3 title33 = new Title3("手抓饼");

        ArrayList<Title3> title2s = new ArrayList<>();

        title2s.add(title31);
        title2s.add(title32);
        title2s.add(title33);

        Title2 title2 = new Title2(title2s, "本店特色");

        ArrayList<Title2> title2s1 = new ArrayList<>();
        title2s1.add(title2);

        Title1 title1 = new Title1(title2s1,"西餐");

        ArrayList<Title1> title1s = new ArrayList<>();
        title1s.add(title1);

        ArrayList<Title1>  title1s1 = new ArrayList<>(title1s);

        MenuVO menuVO = new MenuVO(title1s1);

        return menuVO;
    }
}
