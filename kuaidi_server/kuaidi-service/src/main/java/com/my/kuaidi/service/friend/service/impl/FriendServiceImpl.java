package com.my.kuaidi.service.friend.service.impl;

import com.my.kuaidi.service.friend.mapper.FriendMapper;
import com.my.kuaidi.service.friend.service.FriendService;
import com.my.kuaidi.model.Friend;
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
public class FriendServiceImpl extends CommonServiceImpl<Friend,Integer> implements FriendService,InitializingBean{
    @Resource
    private FriendMapper friendMapper;

    @Override
    public void afterPropertiesSet() {
        super.commonMapper = friendMapper;
    }
}
