
package com.my.kuaidi.admin.rmp.service.impl;

import com.my.kuaidi.admin.rmp.mapper.SysRoleModulePermissionMapper;
import com.my.kuaidi.admin.rmp.model.SysRoleModulePermission;
import com.my.kuaidi.admin.rmp.service.SysRoleModulePermissionService;
import com.my.kuaidi.core.service.CommonServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;



@Service
public class SysRoleModulePermissionServiceImpl extends CommonServiceImpl<SysRoleModulePermission,Long> implements SysRoleModulePermissionService,InitializingBean{
    @Resource
    private SysRoleModulePermissionMapper sysRoleModulePermissionMapper;



    @Override
    public void afterPropertiesSet() {
        super.commonMapper = sysRoleModulePermissionMapper;
    }
}
