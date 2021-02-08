
package com.my.kuaidi.admin.rmp.service.impl;

import com.my.kuaidi.admin.rmp.mapper.SysModuleCategoryMapper;
import com.my.kuaidi.admin.rmp.model.SysModuleCategory;
import com.my.kuaidi.admin.rmp.service.SysModuleCategoryService;
import com.my.kuaidi.core.service.CommonServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;



@Service
public class SysModuleCategoryServiceImpl extends CommonServiceImpl<SysModuleCategory,Long> implements SysModuleCategoryService,InitializingBean{
    @Resource
    private SysModuleCategoryMapper sysModuleCategoryMapper;



    @Override
    public void afterPropertiesSet() {
        super.commonMapper = sysModuleCategoryMapper;
    }
}
