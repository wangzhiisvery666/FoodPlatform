package com.ccut.dachuang.mapper;

import com.ccut.dachuang.model.VO.Title2;
import com.ccut.dachuang.model.VO.Title3;
import com.ccut.dachuang.model.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author o'k
* @description 针对表【menu】的数据库操作Mapper
* @createDate 2022-09-06 18:57:48
* @Entity com.ccut.dachuang.model.pojo.Menu
*/
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    @Select("select menu.name from menu where parent_id=25")
    List<Title3> getTitle3ByParentId();

    @Select("select menu.name from menu where Numbering=25")
    List<Title2> getAllById();

//    @Select("select menu.name from menu")
//    List<Title1> getTitle3ByParentId();
}




