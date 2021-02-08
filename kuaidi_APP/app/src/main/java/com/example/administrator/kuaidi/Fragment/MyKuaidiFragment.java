package com.example.administrator.kuaidi.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.kuaidi.Activity.OrderDetailActivity;
import com.example.administrator.kuaidi.Adapter.AdapterFriends;
import com.example.administrator.kuaidi.Adapter.AdapterMyOrder;
import com.example.administrator.kuaidi.Http.HttpApi.ApiService;
import com.example.administrator.kuaidi.Http.HttpBean.BaseResponse;
import com.example.administrator.kuaidi.Http.HttpBean.RepMykuaidi;
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

public class MyKuaidiFragment extends Fragment {

    @BindView(R.id.myorder)
    Button myorder;
    @BindView(R.id.friendorder)
    Button friendorder;
    @BindView(R.id.data)
    TextView data;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private int type = 0;
    private AdapterMyOrder mAdapter;
    private List<RepMykuaidi> mykuaidiList = new ArrayList<>();
    public MyKuaidiFragment() {
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
        View view = inflater.inflate(R.layout.fragment_my_kuaidi, container, false);
        unbinder = ButterKnife.bind(this, view);

        InitView();

        mykuaidiList.clear();
        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 1;
                Send_ReqMyOrderList();
            }
        });

        friendorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 2;
                Send_ReqMyFriendList();
            }
        });
        return view;
    }

    private void InitView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new AdapterMyOrder(R.layout.item_myorder, mykuaidiList);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.order_rl:
                        RepMykuaidi repMykuaidi = mAdapter.getItem(position);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", repMykuaidi);
                        Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                        intent.putExtras(bundle);
                        intent.putExtra("type",type);
                       // getActivity().startActivity(intent);
                        getActivity().startActivityForResult(intent, 1);
                        break;
                }
            }
        });
    }

    private void Send_ReqMyOrderList(){
        ApiService apiService = RetrofitClient.getInstance(getActivity()).CreateBaseApi();
        Map map = new HashMap();
        map.put("uid", SPUtils.getInstance().getInt("uid"));
        Call<BaseResponse<List<RepMykuaidi>>> call = apiService.MyKuaidi(map);
        call.enqueue(new Callback<BaseResponse<List<RepMykuaidi>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<RepMykuaidi>>> call, Response<BaseResponse<List<RepMykuaidi>>> response) {
                try{
                    List<RepMykuaidi> mykuaidiList = response.body().getData();
                    if(mykuaidiList.size() > 0 ){
                        data.setVisibility(View.GONE);
                    }else{
                        data.setVisibility(View.VISIBLE);
                    }
                    mAdapter.setNewData(mykuaidiList);
                }catch (Exception e){
                    ToastUtils.showShort(e.toString());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<RepMykuaidi>>> call, Throwable t) {

            }
        });
    }

    private void Send_ReqMyFriendList(){
        ApiService apiService = RetrofitClient.getInstance(getActivity()).CreateBaseApi();
        Map map = new HashMap();
        map.put("uid", SPUtils.getInstance().getInt("uid"));
        Call<BaseResponse<List<RepMykuaidi>>> call = apiService.MyFriendKuaidi(map);
        call.enqueue(new Callback<BaseResponse<List<RepMykuaidi>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<RepMykuaidi>>> call, Response<BaseResponse<List<RepMykuaidi>>> response) {
                try{
                    List<RepMykuaidi> mykuaidiList = response.body().getData();
                    if(mykuaidiList.size() > 0 ){
                        data.setVisibility(View.GONE);
                    }else{
                        data.setVisibility(View.VISIBLE);
                    }
                    mAdapter.setNewData(mykuaidiList);
                }catch (Exception e){
                    ToastUtils.showShort(e.toString());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<RepMykuaidi>>> call, Throwable t) {

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == 22){
                int result = data.getExtras().getInt("result");
                if(result == 1){
                    Send_ReqMyOrderList();
                }else{
                    Send_ReqMyFriendList();
                }
            }
        }
    }
}
