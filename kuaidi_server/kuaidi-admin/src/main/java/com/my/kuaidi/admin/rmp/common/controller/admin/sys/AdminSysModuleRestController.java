package com.my.kuaidi.admin.rmp.common.controller.admin.sys;

import com.my.kuaidi.admin.rmp.model.SysModule;
import com.my.kuaidi.admin.rmp.service.SysModuleService;
import com.my.kuaidi.core.common.constant.PageConstant;
import com.my.kuaidi.core.common.exception.BusinessException;
import com.my.kuaidi.core.serialize.ResponseMsg;
import com.my.kuaidi.core.service.CommonRestController;
import com.my.kuaidi.core.utils.ListUtil;
import com.my.kuaidi.core.utils.MapUtil;
import com.my.kuaidi.core.utils.StringUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;




@RequestMapping("/admin/sys_module_rest/")
@RestController
public class AdminSysModuleRestController extends CommonRestController<SysModule,Long> implements InitializingBean
{

    @Resource
    private SysModuleService sysModuleService;
    @RequestMapping(value = "search")
    public ResponseMsg search(
        @RequestParam(required = false) String uniqueField,
        @RequestParam(required = false) Long uniqueValue,
        @RequestParam(required = false,defaultValue = "20") Integer limit,
        @RequestParam(required = false) String keyword
    ){
        limit = Math.min(PageConstant.MAX_LIMIT,limit);
        List<SysModule> list = null;
        Map<String,Object> query = new HashedMap();
        query.put("limit",limit);
        query.put("notSafeOrderBy","sort_num asc");
        if(uniqueValue!=null){//说明是来初始化的
            query.put(uniqueField,uniqueValue);
            list = sysModuleService.getModelList(query);
        }else {//正常搜索
            if(ListUtil.isBlank(list)){
                query.put("moduleTitleFirst",keyword);
                list = sysModuleService.getModelList(query);
                query.remove("moduleTitleFirst");
            }
        }
        return new ResponseMsg(list);
    }
    //分页查询
    @RequestMapping(value={"page"}, method={RequestMethod.GET})
    public ResponseMsg page(
        @RequestParam(required = false,value ="moduleNameFirst")                            String moduleNameFirst ,
        @RequestParam(required = false,value ="moduleCategoryIdFirst")                            Long moduleCategoryIdFirst ,
        @RequestParam(required = false,value ="moduleTitleFirst")                            String moduleTitleFirst ,
        @RequestParam int page,@RequestParam int limit,@RequestParam(required = false) String safeOrderBy)
    {
        limit = Math.min(limit, PageConstant.MAX_LIMIT);
        int start = (page - 1) * limit;
        Map<String,Object> query = new HashedMap();
        query.put("moduleNameFirst",coverBlankToNull(moduleNameFirst));
        query.put("moduleCategoryIdFirst",moduleCategoryIdFirst);
        query.put("moduleTitleFirst",coverBlankToNull(moduleTitleFirst));
        Integer count = sysModuleService.getModelListCount(query);
        query.put("start",start);
        query.put("limit",limit);
        if(StringUtil.isBlank(safeOrderBy)){
            query.put("notSafeOrderBy","sort_num asc");
        }else{
            query.put("safeOrderBy",safeOrderBy);
        }
        return new ResponseMsg(count,sysModuleService.getModelList(query));
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        super.commonService = sysModuleService;
        super.primaryKey = "id";//硬编码此实体的主键名称
    }
}
