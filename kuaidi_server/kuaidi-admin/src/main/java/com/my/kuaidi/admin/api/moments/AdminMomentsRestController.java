package com.my.kuaidi.admin.api.moments;

import com.my.kuaidi.core.service.CommonRestController;
import org.springframework.beans.factory.InitializingBean;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.math.*;
import java.util.List;
import java.util.Map;
import com.my.kuaidi.core.common.constant.PageConstant;
import com.my.kuaidi.core.common.exception.BusinessException;
import com.my.kuaidi.core.serialize.ResponseMsg;
import com.my.kuaidi.model.Moments;
import com.my.kuaidi.service.moments.service.MomentsService;

import com.my.kuaidi.core.utils.ListUtil;
import com.my.kuaidi.core.utils.MapUtil;
import com.my.kuaidi.core.utils.StringUtil;




@RequestMapping("/admin/moments_rest/")
@RestController
public class AdminMomentsRestController extends CommonRestController<Moments,Integer> implements InitializingBean
{

    @Resource
    private MomentsService momentsService;

    //分页查询
    @RequestMapping(value={"page"}, method={RequestMethod.GET})
    public ResponseMsg page(
        @RequestParam(required = false,value ="idFirst")                            Integer idFirst ,
        @RequestParam(required = false,value ="contentFirst")                            String contentFirst ,
        @RequestParam(required = false,value ="uidFirst")                            Integer uidFirst ,
        @RequestParam(required = false,value ="timeFirst")                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date timeFirst ,
        @RequestParam(required = false,value ="timeSecond")                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date timeSecond ,
        @RequestParam int page,@RequestParam int limit,@RequestParam(required = false) String safeOrderBy)
    {
        limit = Math.min(limit, PageConstant.MAX_LIMIT);
        int start = (page - 1) * limit;
        Map<String,Object> query = new HashedMap();
        query.put("idFirst",idFirst);
        query.put("contentFirst",coverBlankToNull(contentFirst));
        query.put("uidFirst",uidFirst);
        query.put("timeFirst",timeFirst);
        query.put("timeSecond",timeSecond);
        Integer count = momentsService.getModelListCount(query);
        query.put("start",start);
        query.put("limit",limit);
        if(StringUtil.isBlank(safeOrderBy)){
            query.put("notSafeOrderBy","id desc");
        }else{
            query.put("safeOrderBy",safeOrderBy);
        }
        return new ResponseMsg(count,momentsService.getModelList(query));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.commonService = momentsService;
        super.primaryKey = "id";//硬编码此实体的主键名称
    }
}
