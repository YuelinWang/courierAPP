
package com.my.kuaidi.admin.rmp.service.impl;

import com.my.kuaidi.admin.rmp.mapper.SysGlobalPermitUrlMapper;
import com.my.kuaidi.admin.rmp.model.SysGlobalPermitUrl;
import com.my.kuaidi.admin.rmp.service.SysGlobalPermitUrlService;
import com.my.kuaidi.core.service.CommonServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;



@Service
public class SysGlobalPermitUrlServiceImpl extends CommonServiceImpl<SysGlobalPermitUrl,Long> implements SysGlobalPermitUrlService,InitializingBean{
    @Resource
    private SysGlobalPermitUrlMapper sysGlobalPermitUrlMapper;



    @Override
    public void afterPropertiesSet() {
        super.commonMapper = sysGlobalPermitUrlMapper;
    }
}
