
package com.my.kuaidi.admin.rmp.service.impl;

import com.my.kuaidi.admin.rmp.mapper.SysRoleMapper;
import com.my.kuaidi.admin.rmp.model.SysRole;
import com.my.kuaidi.admin.rmp.service.SysRoleService;
import com.my.kuaidi.core.service.CommonServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;



@Service
public class SysRoleServiceImpl extends CommonServiceImpl<SysRole,Long> implements SysRoleService,InitializingBean{
    @Resource
    private SysRoleMapper sysRoleMapper;



    @Override
    public void afterPropertiesSet() {
        super.commonMapper = sysRoleMapper;
    }
}
