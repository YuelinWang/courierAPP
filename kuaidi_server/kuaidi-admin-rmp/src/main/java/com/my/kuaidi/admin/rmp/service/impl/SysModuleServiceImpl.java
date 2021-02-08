
package com.my.kuaidi.admin.rmp.service.impl;

import com.my.kuaidi.admin.rmp.mapper.SysModuleMapper;
import com.my.kuaidi.admin.rmp.model.SysModule;
import com.my.kuaidi.admin.rmp.service.SysModuleService;
import com.my.kuaidi.core.service.CommonServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;



@Service
public class SysModuleServiceImpl extends CommonServiceImpl<SysModule,Long> implements SysModuleService,InitializingBean{
    @Resource
    private SysModuleMapper sysModuleMapper;



    @Override
    public void afterPropertiesSet() {
        super.commonMapper = sysModuleMapper;
    }
}
