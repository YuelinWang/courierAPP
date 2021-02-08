package com.my.kuaidi.admin.adminservice.demo.mapper;

import com.my.kuaidi.admin.model.MyDemo;

import java.util.Map;

public interface AdminServiceDemoMapper {
    MyDemo daoDemoGoods(Map<String,Object> query);

}
