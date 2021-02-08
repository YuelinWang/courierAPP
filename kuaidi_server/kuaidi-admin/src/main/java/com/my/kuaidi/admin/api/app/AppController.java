package com.my.kuaidi.admin.api.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.my.kuaidi.core.serialize.ResponseMsg;
import com.my.kuaidi.core.utils.MapUtil;
import com.my.kuaidi.model.*;
import com.my.kuaidi.service.forders.service.FordersService;
import com.my.kuaidi.service.friend.service.FriendService;
import com.my.kuaidi.service.logistics.service.LogisticsService;
import com.my.kuaidi.service.moments.service.MomentsService;
import com.my.kuaidi.service.orders.service.OrdersService;
import com.my.kuaidi.service.user.service.UserService;
import com.my.kuaidi.service.user.service.impl.JiguangPushServiceImpl;
import org.mvel2.ast.Or;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("app")
public class AppController {
    @Resource
    private UserService userService;
    @Resource
    private OrdersService ordersService;
    @Resource
    private FriendService friendService;
    @Resource
    private FordersService fordersService;
    @Resource
    private MomentsService momentsService;
    @Resource
    private JiguangPushServiceImpl jiguangPushService;
    @Resource
    private LogisticsService logisticsService;



    @RequestMapping("login")
    public ResponseMsg login(String password, String username, Integer role, HttpServletRequest request) {
        if (password == null) {
            password = "";
        }
        if (username == null) {
            username = "";
        }
        if (role == null) {
            role = 3;
        }

        User checkUser = userService.selectFirstModelIgnore(MapUtil.buildMap("username", username, "password", password, "role", role));
        if (checkUser == null) {
            return new ResponseMsg(500, "账号密码错误");
        } else {
            if (checkUser.getState() == 1) {
                return new ResponseMsg(500, "此账号已被封禁");
            }
            return new ResponseMsg(checkUser);
        }
    }

    @RequestMapping("reg")
    public ResponseMsg reg(User user) {
        User checkUser = userService.selectFirstModel(MapUtil.buildMap("username", user.getUsername()));
        if (checkUser != null) {
            return new ResponseMsg(500, "用户名已存在");
        }

        int i = userService.insertModelWithoutNull(user);
        if (i == 0) {
            return new ResponseMsg(500, "注册失败");
        } else {
            return new ResponseMsg();
        }
    }

    @RequestMapping("send")
    public ResponseMsg send(Orders orders) {
        int i = ordersService.insertModelWithoutNull(orders);
        System.out.println(i);
        return new ResponseMsg(orders);
    }

    @RequestMapping("myOrderList")
    public ResponseMsg myOrderList(Integer uid) {
        List<Orders> list = ordersService.getModelList(MapUtil.buildMap("uid", uid));
       /* List<Forders> forders = fordersService.getModelList(MapUtil.buildMap("uid", uid));
        if (forders != null) {
            for (int i = 0; i < forders.size(); i++) {
                Orders model = ordersService.getModel(forders.get(i).getOid());
                //查询这个订单的授权人
                List<Forders> o = fordersService.getModelList(MapUtil.buildMap("oid", model.getId()));
                if (o!=null){
                    Forders f = o.get(i);
                    if (f != null){
                        User user = userService.getModel(f.getUid());
                        model.setUser(user);
                    }
                }
                list.add(model);
            }
        }*/
        list = orderLog(list);
        return new ResponseMsg(list);
    }


    public List<Orders> orderLog(List<Orders> list) {
        List<Logistics> logisticss = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Orders orders = list.get(i);
                List<Logistics> modelList = logisticsService.getModelList(MapUtil.buildMap("oid", orders.getId()));
                if (modelList != null) {
                    for (int j = 0; j < modelList.size(); j++) {
                        Logistics logistics = modelList.get(j);
                        logisticss.add(logistics);
                    }
                }
                orders.setList(logisticss);
            }
        }
        return list;
    }

    @RequestMapping("myFriendOrder")
    public ResponseMsg myFriendOrder(Integer uid) {
        List<Forders> forders = fordersService.getModelList(MapUtil.buildMap("uid", uid));
        List<Orders> list = new ArrayList<>();
        if (forders != null) {
            for (int i = 0; i < forders.size(); i++) {
                Orders model = ordersService.getModel(forders.get(i).getOid());
                list.add(model);
            }
        }
        list = orderLog(list);
        return new ResponseMsg(list);
    }

    @RequestMapping("enterReceive")
    public ResponseMsg enterReceive(Integer oid) {
        Orders model = ordersService.getModel(oid);
        model.setState(2);
        int i = ordersService.updateModelWithoutNull(model);
        Push push = new Push();
        push.setUid(model.getUid());
        push.setTitle("有订单签收了");
        push.setContent("订单号："+model.getNumber()+"已签收");

        jiguangPushService.jiguangPush(model.getUid().toString(),"有新消息");
        return new ResponseMsg(push.toString());
    }

    @RequestMapping("userList")
    public ResponseMsg userList(Integer uid) {
        List<User> list = userService.getModelList(null);
        List<User> friends = new ArrayList<>();
        List<Friend> friendList = friendService.getModelList(MapUtil.buildMap("uid", uid));

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                User user = list.get(i);
                if (user.getId() == uid) {
                    continue;
                }
                boolean flag = true;
                if (friendList != null) {
                    for (int j = 0; j < friendList.size(); j++) {
                        Friend friend = friendList.get(j);
                        if (friend.getFid() == user.getId()) {
                            flag = false;
                            break;
                        }
                    }
                }

                if (flag) {
                    friends.add(user);
                }
            }

        }

        return new ResponseMsg(friends);
    }


    /**
     * 添加好友接口
     */
    @RequestMapping("addFriend")
    public ResponseMsg addFriend(Friend friend){
        friend.setTime(new Timestamp(System.currentTimeMillis()));
        int i = friendService.insertModel(friend);
        if (i == 0){
            return new ResponseMsg(500,"添加失败");
        }else {
            return new ResponseMsg("添加成功");
        }
    }

    /**
     * 好友列表
     */
    @RequestMapping("friends")
    public ResponseMsg friends(Integer uid) {
        List<Friend> list = friendService.getModelList(MapUtil.buildMap("uid", uid));
        List<User> friends = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Friend friend = list.get(i);
                User user = userService.getModel(friend.getFid());
                friends.add(user);
            }
        }
        return new ResponseMsg(friends);
    }

    /**
     * 发表动态
     */
    @RequestMapping("moments/public")
    public ResponseMsg publicMo(Moments moments) {
        moments.setTime(new Timestamp(System.currentTimeMillis()));
        int i = momentsService.insertModel(moments);
        return new ResponseMsg(moments);
    }

    @RequestMapping("moments/list")
    public ResponseMsg momentsList(Integer uid) {
        List<Moments> moments = null;
        if (uid == null) {
            moments = momentsService.getModelList(MapUtil.buildMap("uid", uid));
        } else {
            moments = momentsService.getModelList(null);
        }

        if (moments != null) {
            for (int i = 0; i < moments.size(); i++) {
                User user = userService.getModel(moments.get(i).getUid());
                moments.get(i).setUsername(user.getRealname());
            }
        }
        return new ResponseMsg(moments);
    }


    /**
     * 授权接口
     */
    @RequestMapping("authorize")
    public ResponseMsg authorize(Forders forders){
        int i = fordersService.insertModel(forders);
        if (i == 0){
            return new ResponseMsg(500,"失败");
        }else {
            return new ResponseMsg("成功");
        }
    }

    /**
     * 授权人信息
     */
    @RequestMapping("authorizeInfo")
    public ResponseMsg authorizeInfo(Integer oid){
        List<Forders> fordersList = fordersService.getModelList(MapUtil.buildMap("oid", oid));
        if (fordersList != null && fordersList.size()>0){
            Forders forders = fordersList.get(0);
            Integer uid = forders.getUid();
            User user = userService.getModel(uid);
            if (user == null){
                return new ResponseMsg(500,"没有授权人信息");
            }else {
                return new ResponseMsg(user);
            }
        }

       return new ResponseMsg(500,"没有此订单");

    }
    /**
     * 根据单号查找订单
     */
    @RequestMapping("search")
    public ResponseMsg search(String number){
        List<Orders> list = ordersService.getModelList(MapUtil.buildMap("number", number));
        List<Logistics> logisticss = new ArrayList<>();
        if (list != null && list.size() >0){
            Orders orders = list.get(0);
            List<Logistics> modelList = logisticsService.getModelList(MapUtil.buildMap("oid", orders.getId()));
            if (modelList != null) {
                for (int j = 0; j < modelList.size(); j++) {
                    Logistics logistics = modelList.get(j);
                    logisticss.add(logistics);
                }
            }
            orders.setList(logisticss);
            return new ResponseMsg(list.get(0));
        }
        return new ResponseMsg(500,"无内容");
    }

    /**
     * 签收接口
     */
    @RequestMapping("enter")
    public ResponseMsg enter(Orders orders){
        orders.setState(2);
        int i = ordersService.updateModelWithoutNull(orders);
        if (i == 0){
            return new ResponseMsg(500,"失败");
        }else {
            return new ResponseMsg("成功");
        }
    }
}
