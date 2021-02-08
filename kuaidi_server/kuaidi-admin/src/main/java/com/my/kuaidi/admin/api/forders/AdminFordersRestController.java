package com.my.kuaidi.admin.api.forders;

import com.my.kuaidi.core.service.CommonRestController;
import org.springframework.beans.factory.InitializingBean;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.math.*;
import java.util.List;
import java.util.Map;
import com.my.kuaidi.core.common.constant.PageConstant;
import com.my.kuaidi.core.common.exception.BusinessException;
import com.my.kuaidi.core.serialize.ResponseMsg;
import com.my.kuaidi.model.Forders;
import com.my.kuaidi.service.forders.service.FordersService;

import com.my.kuaidi.core.utils.ListUtil;
import com.my.kuaidi.core.utils.MapUtil;
import com.my.kuaidi.core.utils.StringUtil;




@RequestMapping("/admin/forders_rest/")
@RestController
public class AdminFordersRestController extends CommonRestController<Forders,Integer> implements InitializingBean
{

    @Resource
    private FordersService fordersService;

    //分页查询
    @RequestMapping(value={"page"}, method={RequestMethod.GET})
    public ResponseMsg page(
        @RequestParam(required = false,value ="idFirst")                            Integer idFirst ,
        @RequestParam(required = false,value ="uidFirst")                            Integer uidFirst ,
        @RequestParam(required = false,value ="oidFirst")                            Integer oidFirst ,
        @RequestParam int page,@RequestParam int limit,@RequestParam(required = false) String safeOrderBy)
    {
        limit = Math.min(limit, PageConstant.MAX_LIMIT);
        int start = (page - 1) * limit;
        Map<String,Object> query = new HashedMap();
        query.put("idFirst",idFirst);
        query.put("uidFirst",uidFirst);
        query.put("oidFirst",oidFirst);
        Integer count = fordersService.getModelListCount(query);
        query.put("start",start);
        query.put("limit",limit);
        if(StringUtil.isBlank(safeOrderBy)){
            query.put("notSafeOrderBy","id desc");
        }else{
            query.put("safeOrderBy",safeOrderBy);
        }
        return new ResponseMsg(count,fordersService.getModelList(query));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.commonService = fordersService;
        super.primaryKey = "id";//硬编码此实体的主键名称
    }
}
