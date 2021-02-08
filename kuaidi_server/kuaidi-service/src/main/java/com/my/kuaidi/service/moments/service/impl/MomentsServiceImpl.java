package com.my.kuaidi.service.moments.service.impl;

import com.my.kuaidi.service.moments.mapper.MomentsMapper;
import com.my.kuaidi.service.moments.service.MomentsService;
import com.my.kuaidi.model.Moments;
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


@Service
public class MomentsServiceImpl extends CommonServiceImpl<Moments,Integer> implements MomentsService,InitializingBean{
    @Resource
    private MomentsMapper momentsMapper;

    @Override
    public void afterPropertiesSet() {
        super.commonMapper = momentsMapper;
    }
}
