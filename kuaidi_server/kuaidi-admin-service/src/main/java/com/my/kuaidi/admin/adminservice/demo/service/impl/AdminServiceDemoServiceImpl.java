package com.my.kuaidi.admin.adminservice.demo.service.impl;

import com.my.kuaidi.admin.adminservice.demo.mapper.AdminServiceDemoMapper;
import com.my.kuaidi.admin.adminservice.demo.service.AdminServiceDemoService;
import com.my.kuaidi.admin.model.MyDemo;
import com.my.kuaidi.core.utils.MapUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceDemoServiceImpl  implements AdminServiceDemoService {
    @Resource
    private AdminServiceDemoMapper adminServiceDemoMapper;

    @Override
    public MyDemo demoGoods(Long id) {
        return adminServiceDemoMapper.daoDemoGoods(MapUtil.buildMap("id",id));
    }
}
