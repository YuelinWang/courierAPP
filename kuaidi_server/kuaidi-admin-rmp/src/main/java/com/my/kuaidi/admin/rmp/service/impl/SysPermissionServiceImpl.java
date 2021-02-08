
package com.my.kuaidi.admin.rmp.service.impl;

import com.my.kuaidi.admin.rmp.mapper.SysPermissionMapper;
import com.my.kuaidi.admin.rmp.model.SysPermission;
import com.my.kuaidi.admin.rmp.service.SysPermissionService;
import com.my.kuaidi.core.service.CommonServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;



@Service
public class SysPermissionServiceImpl extends CommonServiceImpl<SysPermission,Long> implements SysPermissionService,InitializingBean{
    @Resource
    private SysPermissionMapper sysPermissionMapper;



    @Override
    public void afterPropertiesSet() {
        super.commonMapper = sysPermissionMapper;
    }
}
