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


public class FriendsFragment extends Fragment {

    @BindView(R.id.data)
    TextView data;
    Unbinder unbinder;
    private RecyclerView mRecyclerView;
    private AdapterFriends mAdapter;
    private List<UserInfoItem> UserInfoList = new ArrayList<UserInfoItem>();

    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new AdapterFriends(R.layout.item_friendslist, UserInfoList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.fd_rl:
                        UserInfoItem userInfoItem = mAdapter.getItem(position);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", userInfoItem);
                        Intent intent = new Intent(getActivity(), UserCommonActivity.class);
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
                        break;
                }
            }
        });
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Log.e(TAG, "setUserVisibleHint: msg true" );
            Send_ReqGetFriendsList();
        } else {
            // Log.e(TAG, "setUserVisibleHint: msg false" );
        }
    }

    //uid 自己的id  fid 朋友id
    private void Send_ReqAddFriend(int uid, int fid) {


    }

    private void Send_ReqGetFriendsList() {
        ApiService apiService = RetrofitClient.getInstance(getActivity()).CreateBaseApi();
        Map map = new HashMap();
        map.put("uid", SPUtils.getInstance().getInt("uid"));
        Call<BaseResponse<List<UserBean>>> call = apiService.GetFriendList(map);
        call.enqueue(new Callback<BaseResponse<List<UserBean>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<UserBean>>> call, Response<BaseResponse<List<UserBean>>> response) {
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
                    userInfoItem.setStatus(1);
                    UserInfoList.add(userInfoItem);
                }
                mAdapter.setNewData(UserInfoList);
            }

            @Override
            public void onFailure(Call<BaseResponse<List<UserBean>>> call, Throwable t) {

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
