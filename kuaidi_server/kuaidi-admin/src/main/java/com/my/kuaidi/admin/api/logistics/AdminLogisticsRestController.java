package com.my.kuaidi.admin.api.logistics;

import com.my.kuaidi.core.service.CommonRestController;
import com.my.kuaidi.model.Orders;
import com.my.kuaidi.service.orders.service.OrdersService;
import com.my.kuaidi.service.user.service.impl.JiguangPushServiceImpl;
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
import com.my.kuaidi.model.Logistics;
import com.my.kuaidi.service.logistics.service.LogisticsService;

import com.my.kuaidi.core.utils.ListUtil;
import com.my.kuaidi.core.utils.MapUtil;
import com.my.kuaidi.core.utils.StringUtil;


/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

@RequestMapping("/admin/logistics_rest/")
@RestController
public class AdminLogisticsRestController extends CommonRestController<Logistics,Integer> implements InitializingBean
{

    @Resource
    private LogisticsService logisticsService;
    @Resource
    private JiguangPushServiceImpl jiguangPushService;
    @Resource
    private OrdersService ordersService;

    //分页查询
    @RequestMapping(value={"page"}, method={RequestMethod.GET})
    public ResponseMsg page(
        @RequestParam(required = false,value ="idFirst")                            Integer idFirst ,
        @RequestParam(required = false,value ="oidFirst")                            Integer oidFirst ,
        @RequestParam int page,@RequestParam int limit,@RequestParam(required = false) String safeOrderBy)
    {
        limit = Math.min(limit, PageConstant.MAX_LIMIT);
        int start = (page - 1) * limit;
        Map<String,Object> query = new HashedMap();
        query.put("idFirst",idFirst);
        query.put("oidFirst",oidFirst);
        Integer count = logisticsService.getModelListCount(query);
        query.put("start",start);
        query.put("limit",limit);
        query.put("safeOrderBy",safeOrderBy);
        return new ResponseMsg(count,logisticsService.getModelList(query));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.commonService = logisticsService;
        super.primaryKey = "id";//硬编码此实体的主键名称
    }

    @Override
    public ResponseMsg save(Logistics entity) {
        Integer oid = entity.getOid();
        Orders model = ordersService.getModel(oid);
        jiguangPushService.jiguangPush(model.getUid().toString(),"订单："+model.getNumber() + "有新的物流信息了");
        return super.save(entity);
    }
}
