
package com.my.kuaidi.admin.rmp.service.impl;

import com.my.kuaidi.admin.rmp.mapper.SysAdminUserMapper;
import com.my.kuaidi.admin.rmp.model.SysAdminUser;
import com.my.kuaidi.admin.rmp.service.SysAdminUserService;
import com.my.kuaidi.core.service.CommonServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;



@Service
public class SysAdminUserServiceImpl extends CommonServiceImpl<SysAdminUser,Long> implements SysAdminUserService,InitializingBean{
    @Resource
    private SysAdminUserMapper sysAdminUserMapper;



    @Override
    public void afterPropertiesSet() {
        super.commonMapper = sysAdminUserMapper;
    }
}
