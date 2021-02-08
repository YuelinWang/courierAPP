package com.my.kuaidi.service.dict.service.impl;

import com.my.kuaidi.service.dict.mapper.DictMapper;
import com.my.kuaidi.service.dict.service.DictService;
import com.my.kuaidi.model.Dict;
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
public class DictServiceImpl extends CommonServiceImpl<Dict,Long> implements DictService,InitializingBean{
    @Resource
    private DictMapper dictMapper;

    @Override
    public void afterPropertiesSet() {
        super.commonMapper = dictMapper;
    }
}
