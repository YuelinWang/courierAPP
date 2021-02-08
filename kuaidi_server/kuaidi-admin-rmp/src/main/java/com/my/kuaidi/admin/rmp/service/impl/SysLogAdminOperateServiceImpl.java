
package com.my.kuaidi.admin.rmp.service.impl;

import com.my.kuaidi.admin.rmp.mapper.SysLogAdminOperateMapper;
import com.my.kuaidi.admin.rmp.model.SysLogAdminOperate;
import com.my.kuaidi.admin.rmp.service.SysLogAdminOperateService;
import com.my.kuaidi.core.service.CommonServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;



@Service
public class SysLogAdminOperateServiceImpl extends CommonServiceImpl<SysLogAdminOperate,Long> implements SysLogAdminOperateService,InitializingBean{
    @Resource
    private SysLogAdminOperateMapper sysLogAdminOperateMapper;



    @Override
    public void afterPropertiesSet() {
        super.commonMapper = sysLogAdminOperateMapper;
    }
}
