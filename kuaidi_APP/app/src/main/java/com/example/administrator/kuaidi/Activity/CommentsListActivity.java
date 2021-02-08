package com.example.administrator.kuaidi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.kuaidi.Adapter.AdapterCommentsList;
import com.example.administrator.kuaidi.Adapter.AdapterMyOrder;
import com.example.administrator.kuaidi.Http.HttpApi.ApiService;
import com.example.administrator.kuaidi.Http.HttpBean.BaseResponse;
import com.example.administrator.kuaidi.Http.HttpBean.FriendComments;
import com.example.administrator.kuaidi.Http.HttpBean.RepMykuaidi;
import com.example.administrator.kuaidi.Http.HttpUtils.RetrofitClient;
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

public class CommentsListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private AdapterCommentsList mAdapter;
    private List<FriendComments> friendCommentsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_list);
        ButterKnife.bind(this);

        initToolbar();
        InitView();
        getDatas();
    }

    private void InitView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new AdapterCommentsList(R.layout.item_comments, friendCommentsList);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    private void initToolbar() {
        toolbar.setTitle("朋友圈");
    }

    private void getDatas(){
        ApiService apiService = RetrofitClient.getInstance(this).CreateBaseApi();
        Map map = new HashMap();
        map.put("uid", SPUtils.getInstance().getInt("uid"));
        Call<BaseResponse<List<FriendComments>>>  call = apiService.GetCommentFromFriends(map);
        call.enqueue(new Callback<BaseResponse<List<FriendComments>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<FriendComments>>> call, Response<BaseResponse<List<FriendComments>>> response) {
                try{
                    mAdapter.setNewData(response.body().getData());
                }catch (Exception e){
                    ToastUtils.showShort(e.toString());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<FriendComments>>> call, Throwable t) {

            }
        });
    }
}
