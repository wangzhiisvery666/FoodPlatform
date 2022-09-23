package com.ccut.dachuang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccut.dachuang.Exception.CustomizeException;
import com.ccut.dachuang.common.CommonResponse;
import com.ccut.dachuang.common.ErrorEnum;
import com.ccut.dachuang.model.pojo.Address;
import com.ccut.dachuang.model.request.RequestAddress;
import com.ccut.dachuang.service.AddressService;
import com.ccut.dachuang.mapper.AddressMapper;
import com.ccut.dachuang.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
* @author o'k
* @description 针对表【address】的数据库操作Service实现
* @createDate 2022-09-23 17:45:16
*/
@Service
@Slf4j
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
    implements AddressService{

    @Autowired
    AddressMapper addressMapper;
    @Autowired
    AddressService addressService;
    @Autowired
    AddressServiceImpl addressService1;

    /**
     * 添加地址
     * @param requestAddress ：地址信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResponse<Boolean> addAdress(RequestAddress requestAddress, HttpServletRequest httpServletRequest) {
        //对象复制
        Address address1 = new Address();
        BeanUtils.copyProperties(requestAddress,address1);
        HashMap<String, String> decodeTokenToUserInfo = JwtUtil.getDecodeTokenToUserInfo(httpServletRequest);
        String s = decodeTokenToUserInfo.get(JwtUtil.UID);

        Integer userId =Integer.parseInt(s);
        String permission = requestAddress.getPermission();
        address1.setUserId(userId);

        //设置对象准备查询
        QueryWrapper<Address> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("user_id",userId);

        long count = addressService.count(objectQueryWrapper);
        //如果只有这一个地址则把该地址设为默认地址
        if (count==0){
            address1.setPermission("1");
        }
        //如果不是第一个地址 并且 把这该地址设为默认地址
        if (count!=0&&permission.equals("1")){
            //把原先的默认地址改为普通地址
            addressMapper.updatePermissionByUserId(userId);
            address1.setPermission("1");
        }
        //如果不是第一个地址 并且 不把该地址 设为默认地址
        if (count!=0&&permission.equals("0")){
            address1.setPermission("0");
        }
        int  row=addressMapper.insert(address1);
        if (row>0){
            throw  new CustomizeException(ErrorEnum.ADDRESS_ADD_FAILED);
        }
        log.info("addAdress添加地址成功");
        return new CommonResponse<>("地址添加成功", "200", true);
    }
    /**
     * 删除地址
     * @param address 要删除的地址
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResponse<Boolean> deleteAdress(Address address) {
        try{
        Long id = address.getId();
        Integer userId = address.getUserId();
        String permission = address.getPermission();
        //如果要删除的地址是默认地址 则把其他的地址设为默认地址
        if (permission!=null&&permission.equals("1")){
           //先判断是否有其他地址
            QueryWrapper<Address> objectQueryWrapper = new QueryWrapper<>();
            objectQueryWrapper.eq("user_id",userId);
            List<Address> addresses = addressMapper.selectList(objectQueryWrapper);
            //如果还有其他地址则把第一个设为默认地址
            if (addresses.size()>0){
                Address address1 = addresses.get(0);
                address1.setPermission("1");
                addressMapper.updateById(address1);
            }
        }
        //则把该地址删除
        if (permission==null||permission.equals("0")){
                addressMapper.deleteById(id);
        }
        }catch (Exception e){
            throw  new CustomizeException(ErrorEnum.ADDRESS_DELETE_FAILED);
        }

        log.info("deleteAdress地址删除地址成功");
        return new CommonResponse<>("地址删除成功", "200", true);
    }

    @Override
    public CommonResponse<List<Address>> getAdress() {
        List<Address> list =null;
        try {
            list = addressService.list();
        }catch (Exception e){
            throw new CustomizeException(ErrorEnum.FAILED_TO_GET_ADDRESS);
        }
        return  new CommonResponse<>("地址列表", "200", list);
    }

    /**
     * 修改地址
     * @param requestAddress :地址信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResponse<Boolean> updateAdress(Address requestAddress) {

        Integer uid = requestAddress.getUserId();
        String permission = requestAddress.getPermission();
        //如果用户修改为默认地址 则把原来的默认改为普通
        if (permission.equals("1")){
            addressMapper.updatePermissionByUserId(uid);
        }
        int row = addressMapper.updateById(requestAddress);
        if (row<0){
            throw new CustomizeException(ErrorEnum.FAIL_TO_EDIT);
        }
        log.info("修改地址成功");
        return new CommonResponse<>("地址修改成功", "200", true);
    }

    @Override
    public Address getDefaultAddress(int id) {
        QueryWrapper<Address> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq("user_id",id);
        QueryWrapper.eq("permission",'1');

        return  addressMapper.selectOne(QueryWrapper);
    }
}




