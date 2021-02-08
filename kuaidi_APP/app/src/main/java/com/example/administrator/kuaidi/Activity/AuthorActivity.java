package com.example.administrator.kuaidi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.kuaidi.Adapter.AdapterFriends;
import com.example.administrator.kuaidi.Http.HttpApi.ApiService;
import com.example.administrator.kuaidi.Http.HttpBean.BaseResponse;
import com.example.administrator.kuaidi.Http.HttpBean.UserBean;
import com.example.administrator.kuaidi.Http.HttpUtils.RetrofitClient;
import com.example.administrator.kuaidi.ModelBean.UserInfoItem;
import com.example.administrator.kuaidi.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.data)
    TextView data;

    private AdapterFriends mAdapter;
    private List<UserInfoItem> UserInfoList = new ArrayList<UserInfoItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        ButterKnife.bind(this);

        initToolbar();
        initView();
        Send_ReqGetFriendsList();
    }

    private void initToolbar() {
        toolbar.setTitle("授权人选择");
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new AdapterFriends(R.layout.item_friendslist, UserInfoList);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.fd_rl:
                        UserInfoItem userInfoItem = mAdapter.getItem(position);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data",userInfoItem);
                        Intent intent = new Intent();
                        intent.putExtras(bundle);
                        setResult(22,intent);
                        finish();
                        break;
                }
            }
        });
    }

    private void Send_ReqGetFriendsList() {
        ApiService apiService = RetrofitClient.getInstance(this).CreateBaseApi();
        Map map = new HashMap();
        map.put("uid", SPUtils.getInstance().getInt("uid"));
        Call<BaseResponse<List<UserBean>>> call = apiService.GetFriendList(map);
        call.enqueue(new Callback<BaseResponse<List<UserBean>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<UserBean>>> call, Response<BaseResponse<List<UserBean>>> response) {
                try{
                    List<UserBean> userBeanList = response.body().getData();
                    if (userBeanList.size() > 0) {
                        data.setVisibility(View.GONE);
                    } else {
                        data.setVisibility(View.VISIBLE);
                    }
                    UserInfoList.clear();
                    for (int i = 0; i < userBeanList.size(); i++) {
                        UserBean userBean = userBeanList.get(i);
                        UserInfoItem userInfoItem = new UserInfoItem();
                        userInfoItem.setId(userBean.getId());
                        userInfoItem.setUsername(userBean.getUsername());
                        userInfoItem.setPassword(userBean.getPassword());
                        userInfoItem.setPhone(userBean.getPhone());
                        userInfoItem.setRealname(userBean.getRealname());
                        userInfoItem.setState(userBean.getState());
                        userInfoItem.setStatus(1);
                        UserInfoList.add(userInfoItem);
                    }
                    mAdapter.setNewData(UserInfoList);
                }catch (Exception e){
                    ToastUtils.showShort(e.toString());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<UserBean>>> call, Throwable t) {

            }
        });
    }
}
