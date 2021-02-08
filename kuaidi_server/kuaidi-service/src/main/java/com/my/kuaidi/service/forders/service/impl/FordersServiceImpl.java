package com.my.kuaidi.service.forders.service.impl;

import com.my.kuaidi.service.forders.mapper.FordersMapper;
import com.my.kuaidi.service.forders.service.FordersService;
import com.my.kuaidi.model.Forders;
import com.my.kuaidi.core.service.CommonServiceImpl;
import com.my.kuaidi.core.utils.StringUtil;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.my.kuaidi.core.utils.CopyUtil;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.*;


@Service
public class FordersServiceImpl extends CommonServiceImpl<Forders,Integer> implements FordersService,InitializingBean{
    @Resource
    private FordersMapper fordersMapper;

    @Override
    public void afterPropertiesSet() {
        super.commonMapper = fordersMapper;
    }
}
