package com.my.kuaidi.service.user.service.impl;

import com.my.kuaidi.service.user.mapper.UserMapper;
import com.my.kuaidi.service.user.service.UserService;
import com.my.kuaidi.model.User;
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
/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

@Service
public class UserServiceImpl extends CommonServiceImpl<User,Integer> implements UserService,InitializingBean{
    @Resource
    private UserMapper userMapper;

    @Override
    public void afterPropertiesSet() {
        super.commonMapper = userMapper;
    }
}
