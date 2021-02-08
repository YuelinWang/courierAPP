package com.my.kuaidi.admin.rmp.common.controller.admin.sys;

import com.my.kuaidi.admin.rmp.model.SysLogAdminOperate;
import com.my.kuaidi.admin.rmp.service.SysLogAdminOperateService;
import com.my.kuaidi.core.common.constant.PageConstant;
import com.my.kuaidi.core.common.exception.BusinessException;
import com.my.kuaidi.core.serialize.ResponseMsg;
import com.my.kuaidi.core.service.CommonRestController;
import com.my.kuaidi.core.utils.StringUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Set;




@RequestMapping("/admin/sys_log_admin_operate_rest/")
@RestController
public class AdminSysLogAdminOperateRestController extends CommonRestController<SysLogAdminOperate,Long> implements InitializingBean
{

    @Resource
    private SysLogAdminOperateService sysLogAdminOperateService;
    //分页查询
    @RequestMapping(value={"page"}, method={RequestMethod.GET})
    public ResponseMsg page(
        @RequestParam(required = false,value ="adminUserIdFirst")                            Long adminUserIdFirst ,
        @RequestParam(required = false,value ="userNameFirst")                            String userNameFirst ,
        @RequestParam(required = false,value ="tableNameFirst")                            String tableNameFirst ,
        @RequestParam(required = false,value ="formBodyFirst")                            String formBodyFirst ,
        @RequestParam(required = false,value ="createTimeFirst")                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date createTimeFirst ,
        @RequestParam(required = false,value ="createTimeSecond")                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date createTimeSecond ,
        @RequestParam int page,@RequestParam int limit,@RequestParam(required = false) String safeOrderBy)
    {
        limit = Math.min(limit, PageConstant.MAX_LIMIT);
        int start = (page - 1) * limit;
        Map<String,Object> query = new HashedMap();
        query.put("adminUserIdFirst",adminUserIdFirst);
        query.put("userNameFirst",coverBlankToNull(userNameFirst));
        query.put("tableNameFirst",coverBlankToNull(tableNameFirst));
        query.put("formBodyFirst",coverBlankToNull(formBodyFirst));
        query.put("createTimeFirst",createTimeFirst);
        query.put("createTimeSecond",createTimeSecond);
        Integer count = sysLogAdminOperateService.getModelListCount(query);
        query.put("start",start);
        query.put("limit",limit);
        if(StringUtil.isBlank(safeOrderBy)){
            query.put("notSafeOrderBy","create_time desc");
        }else{
            query.put("safeOrderBy",safeOrderBy);
        }
        return new ResponseMsg(count,sysLogAdminOperateService.getModelList(query));
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        super.commonService = sysLogAdminOperateService;
        super.primaryKey = "id";//硬编码此实体的主键名称
    }
}
