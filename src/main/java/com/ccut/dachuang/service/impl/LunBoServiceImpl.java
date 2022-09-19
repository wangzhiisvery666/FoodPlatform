package com.ccut.dachuang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccut.dachuang.model.pojo.lunBo;
import com.ccut.dachuang.service.LunBoService;
import com.ccut.dachuang.mapper.LunboMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
* @author o'k
* @description 针对表【lunBo】的数据库操作Service实现
* @createDate 2022-09-06 20:40:01
*/
@Service
@Slf4j
public class LunBoServiceImpl extends ServiceImpl<LunboMapper, lunBo>
    implements LunBoService {

    @Autowired
    LunboMapper lunboMapper;

    @Override
    public List<String> getUrl() {
        ArrayList<String> strings = new ArrayList<>();
        List<lunBo> list = lunboMapper.selectList(null);

        Iterator<lunBo> iterator = list.iterator();
        while (iterator.hasNext()) {
            String url = iterator.next().getUrl();
            strings.add(url);
        }
        log.info("====================获取轮播图URL成功==================");
        return strings;
    }
}




