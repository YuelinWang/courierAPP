package com.example.administrator.kuaidi.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.kuaidi.Activity.UserCommonActivity;
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
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFragment extends Fragment {

    @BindView(R.id.data)
    TextView data;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private AdapterFriends mAdapter;
    private List<UserInfoItem> UserInfoList = new ArrayList<UserInfoItem>();

    public UsersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        unbinder = ButterKnife.bind(this, view);

        InitView();
        return view;
    }

    private void InitView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new AdapterFriends(R.layout.item_friendslist, UserInfoList);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                UserInfoItem userInfoItem = (UserInfoItem) adapter.getItem(position);
                switch (view.getId()){
                    case R.id.fd_addbtn:
                        AddFriends(userInfoItem);
                        break;
                }
            }
        });
    }

    private void Send_ReqGetUsersList(){
        ApiService apiService = RetrofitClient.getInstance(getActivity()).CreateBaseApi();
        Map map = new HashMap();
        map.put("uid", SPUtils.getInstance().getInt("uid"));
        Call<BaseResponse<List<UserBean>>> call = apiService.GetUserList(map);
        call.enqueue(new Callback<BaseResponse<List<UserBean>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<UserBean>>> call, Response<BaseResponse<List<UserBean>>> response) {
                try{
                    List<UserBean> userBeanList = response.body().getData();
                    if(userBeanList.size() > 0 ){
                        data.setVisibility(View.GONE);
                    }else{
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
                        userInfoItem.setStatus(0);
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
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Log.e(TAG, "setUserVisibleHint: msg true" );
            Send_ReqGetUsersList();
        } else {
            // Log.e(TAG, "setUserVisibleHint: msg false" );
        }
    }

    private void AddFriends(final UserInfoItem userInfoItem){
        ApiService apiService = RetrofitClient.getInstance(getActivity()).CreateBaseApi();
        Map map = new HashMap();
        map.put("uid", SPUtils.getInstance().getInt("uid"));
        map.put("fid", userInfoItem.getId());
        Call<BaseResponse> call = apiService.AddFriends(map);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                try{
                    if(response.body().getCode() == 0){
                        ToastUtils.showShort("添加好友成功");
                        for(int i = 0;i < UserInfoList.size(); i++){
                            UserInfoItem infoItem = UserInfoList.get(i);
                            if(infoItem.getId() == userInfoItem.getId()){
                                UserInfoList.remove(i);
                                mAdapter.setNewData(UserInfoList);
                                break;
                            }
                        }
                    }
                }catch ( Exception e){
                    ToastUtils.showShort(e.toString());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
