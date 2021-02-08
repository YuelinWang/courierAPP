package com.my.kuaidi.admin.api.orders;

import com.my.kuaidi.core.service.CommonRestController;
import com.my.kuaidi.service.user.service.impl.JiguangPushServiceImpl;
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
import com.my.kuaidi.model.Orders;
import com.my.kuaidi.service.orders.service.OrdersService;

import com.my.kuaidi.core.utils.ListUtil;
import com.my.kuaidi.core.utils.MapUtil;
import com.my.kuaidi.core.utils.StringUtil;




@RequestMapping("/admin/orders_rest/")
@RestController
public class AdminOrdersRestController extends CommonRestController<Orders,Integer> implements InitializingBean
{

    @Resource
    private OrdersService ordersService;
    @Resource
    private JiguangPushServiceImpl jiguangPushService;
    @RequestMapping(value = "search")
    public ResponseMsg search(
        @RequestParam(required = false) String uniqueField,
        @RequestParam(required = false) Integer uniqueValue,
        @RequestParam(required = false,defaultValue = "20") Integer limit,
        @RequestParam(required = false) String keyword
    ){
        limit = Math.min(PageConstant.MAX_LIMIT,limit);
        List<Orders> list = null;
        Map<String,Object> query = new HashedMap();
        query.put("limit",limit);
        if(uniqueValue!=null){//说明是来初始化的
            query.put(uniqueField,uniqueValue);
            list = ordersService.getModelList(query);
        }else {//正常搜索
            if(ListUtil.isBlank(list)){
                query.put("uidFirst",keyword);
                list = ordersService.getModelList(query);
                query.remove("uidFirst");
            }
        }
        return new ResponseMsg(list);
    }
    //分页查询
    @RequestMapping(value={"page"}, method={RequestMethod.GET})
    public ResponseMsg page(
        @RequestParam(required = false,value ="numberFirst")                            String numberFirst ,
        @RequestParam(required = false,value ="uidFirst")                            Integer uidFirst ,
        @RequestParam(required = false,value ="stateFirst")                            Integer stateFirst ,
        @RequestParam int page,@RequestParam int limit,@RequestParam(required = false) String safeOrderBy)
    {
        limit = Math.min(limit, PageConstant.MAX_LIMIT);
        int start = (page - 1) * limit;
        Map<String,Object> query = new HashedMap();
        query.put("numberFirst",coverBlankToNull(numberFirst));
        query.put("uidFirst",uidFirst);
        query.put("stateFirst",stateFirst);
        Integer count = ordersService.getModelListCount(query);
        query.put("start",start);
        query.put("limit",limit);
        query.put("safeOrderBy",safeOrderBy);
        return new ResponseMsg(count,ordersService.getModelList(query));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.commonService = ordersService;
        super.primaryKey = "id";//硬编码此实体的主键名称
    }


    @Override
    @RequestMapping(value="update/{magicalCoderId}", method={RequestMethod.POST})
    public ResponseMsg update(@PathVariable Integer magicalCoderId, Orders entity) {
        Orders model = ordersService.getModel(magicalCoderId);
        String number = model.getNumber();
        jiguangPushService.jiguangPush(model.getUid().toString(),"订单号"+number +"物流有变化了");
        ResponseMsg update = super.update(magicalCoderId, entity);
        return update;
    }
}
