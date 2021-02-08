package com.example.administrator.kuaidi.Http.HttpApi;

import com.example.administrator.kuaidi.Http.HttpBean.BaseResponse;
import com.example.administrator.kuaidi.Http.HttpBean.FriendComments;
import com.example.administrator.kuaidi.Http.HttpBean.LoginBeanRep;
import com.example.administrator.kuaidi.Http.HttpBean.RepMykuaidi;
import com.example.administrator.kuaidi.Http.HttpBean.RepSendKd;
import com.example.administrator.kuaidi.Http.HttpBean.UserBean;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    String UrlPath = " http://7bb96j.natappfree.cc";
    String Urlmiddle = "/kuaidi-admin/app/";
    String Base_Url  = UrlPath  + "/kuaidi-admin/app/";
   //String Base_Url = "http://www.wanandroid.com/";

    //注册
    @FormUrlEncoded
    @POST("reg")
    Call<BaseResponse> postRegister(@FieldMap Map<String, String> params);
    //Call<BaseResponse> postRegister(@Body RegisterBean registerBean);

    //登录
    @FormUrlEncoded
    @POST("login")
    Call<BaseResponse<LoginBeanRep>> GetLogin(@FieldMap Map<String, String> params);

    //发送快递
    @FormUrlEncoded
    @POST("send")
    Call<BaseResponse<RepSendKd>> SendKuaidi(@FieldMap Map<String, String> params);

    //我的快递
    @FormUrlEncoded
    @POST("myOrderList")
    Call<BaseResponse<List<RepMykuaidi>>> MyKuaidi(@FieldMap Map<String, String> params);

    //我被委托的快递
    @FormUrlEncoded
    @POST("myFriendOrder")
    Call<BaseResponse<List<RepMykuaidi>>> MyFriendKuaidi(@FieldMap Map<String, String> params);

    //签收订单
    @FormUrlEncoded
    @POST("enterReceive")
    Call<BaseResponse> MyReceiveKuaidi(@FieldMap Map<String, String> params);

    //用户列表  添加好友使用
    @FormUrlEncoded
    @POST("userList")
    Call<BaseResponse<List<UserBean>>> GetUserList(@FieldMap Map<String, String> params);

    //好友列表
    @FormUrlEncoded
    @POST("friends")
    Call<BaseResponse<List<UserBean>>> GetFriendList(@FieldMap Map<String, String> params);

    //评论发布朋友圈
    @FormUrlEncoded
    @POST("moments/public")
    Call<BaseResponse> SendCommentToFriends(@FieldMap Map<String, String> params);

    //朋友圈动态列表
    @FormUrlEncoded
    @POST("moments/list")
    Call<BaseResponse<List<FriendComments>>> GetCommentFromFriends(@FieldMap Map<String, String> params);

    //添加朋友
    @FormUrlEncoded
    @POST("addFriend")
    Call<BaseResponse> AddFriends(@FieldMap Map<String, String> params);

    //授权
    @FormUrlEncoded
    @POST("authorize")
    Call<BaseResponse> SerAuthor(@FieldMap Map<String, String> params);

    //获取授权信息
    @FormUrlEncoded
    @POST("authorizeInfo")
    Call<BaseResponse<UserBean>> GerAuthor(@FieldMap Map<String, String> params);

    //根据快递单号 获取快递单详情
    @FormUrlEncoded
    @POST("search")
    Call<BaseResponse<RepMykuaidi>> SearchOrderDetail(@FieldMap Map<String, String> params);
}
