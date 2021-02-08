package com.my.kuaidi.service.logistics.service.impl;

import com.my.kuaidi.service.logistics.mapper.LogisticsMapper;
import com.my.kuaidi.service.logistics.service.LogisticsService;
import com.my.kuaidi.model.Logistics;
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
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.math.*;
/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

@Service
public class LogisticsServiceImpl extends CommonServiceImpl<Logistics,Integer> implements LogisticsService,InitializingBean{
    @Resource
    private LogisticsMapper logisticsMapper;

    @Override
    public void afterPropertiesSet() {
        super.commonMapper = logisticsMapper;
    }
}
