package com.example.administrator.kuaidi.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.example.administrator.kuaidi.Activity.ChatMsgActivity;
import com.example.administrator.kuaidi.Adapter.ChatMessageAdapter;
import com.example.administrator.kuaidi.Http.HttpApi.ApiService;
import com.example.administrator.kuaidi.Http.HttpBean.BaseResponse;
import com.example.administrator.kuaidi.Http.HttpUtils.RetrofitClient;
import com.example.administrator.kuaidi.ModelBean.ChatMessage;
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


public class MsgFragment extends Fragment implements ChatMessageAdapter.OnItemClickListener {

    private static final String TAG = "foryou22";
    @BindView(R.id.data)
    TextView data;
    Unbinder unbinder;
    @BindView(R.id.bt_refchat)
    Button btRefchat;

    private boolean isForground;
    private RecyclerView mRecyclerView;
    private ChatMessageAdapter mAdapter;
    private List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
    public MsgFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_msg, container, false);
        unbinder = ButterKnife.bind(this, view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new ChatMessageAdapter(getActivity(), chatMessageList);
        mRecyclerView.setAdapter(mAdapter);

        btRefchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.clear();
            }
        });

        mAdapter.setItemClickListener(this);
        return view;
    }

    private void initData() {
        for (int i = 0; i < 0; i++) {
          //  sendMessage("hello", "kay");
          //  mimicOtherMessage("hello too", "user");
        }
    }

    private void sendMessage(String message, String username, String createTime ) {
        Log.e(TAG, "sendMessage: " + username );
        ChatMessage chatMessage = new ChatMessage(message, true, false, username,createTime );
       // mAdapter.add(chatMessage);
        chatMessageList.add(chatMessage);

        //mimicOtherMessage(message);
    }

    private void mimicOtherMessage(String message, String username,String createTime ) {
        ChatMessage chatMessage = new ChatMessage(message, false, false, username,createTime );
       // mAdapter.add(chatMessage);
        chatMessageList.add(chatMessage);
       // mAdapter.notify();
        Log.e(TAG, "mimicOtherMessage: " + mAdapter.getItemCount() );
        //mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
    }

    @Override
    public void onItemClick(int postiton) {
      /*  Log.e(TAG, "onItemClick: " + postiton );
        ChatRepBean chatRepBean = chatListBean.get(postiton);
        Intent intent = new Intent(getActivity(),ChatMsgActivity.class);
        int uid = 0;
        if(chatRepBean.getUidOne() == SPUtils.getInstance().getInt("uid")){
            uid = chatRepBean.getUidTwo();
        }else{
            uid = chatRepBean.getUidOne();
        }
        intent.putExtra("uid",uid );
        getActivity().startActivity(intent);*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Log.e(TAG, "setUserVisibleHint: msg true" );
            isForground = true;
            initData();
        } else {
            // Log.e(TAG, "setUserVisibleHint: msg false" );
            isForground = false;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //Log.e(TAG, "onDetach: msg" );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Log.e(TAG, "onDestroyView: msg" );
        unbinder.unbind();
    }

    private  void GetChatList(){
    /*    ApiService apiService = RetrofitClient.getInstance(getActivity()).CreateBaseApi();
        Map map = new HashMap();
        map.put("uid", SPUtils.getInstance().getInt("uid"));

        Call<BaseResponse<List<ChatRepBean>>> call = apiService.GetChatList(map);
        call.enqueue(new Callback<BaseResponse<List<ChatRepBean>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<ChatRepBean>>> call, Response<BaseResponse<List<ChatRepBean>>> response) {
                List<ChatRepBean> list = response.body().getData();
                if(list != null){
                    chatListBean.clear();
                    chatMessageList.clear();
                    if(list.size() > 0){
                        data.setVisibility(View.GONE);
                    }else{
                        data.setVisibility(View.VISIBLE);
                    }
                    for(int i = 0; i < list.size(); i++){
                        ChatRepBean chatRepBean = list.get(i);
                        chatListBean.add(chatRepBean);
                        if(chatRepBean.getUidOne() == SPUtils.getInstance().getInt("uid")){
                            sendMessage(chatRepBean.getContent(), chatRepBean.getUserOneName(),chatRepBean.getCreateTime());
                        }else{
                            mimicOtherMessage(chatRepBean.getContent(), chatRepBean.getUserTwoName(),chatRepBean.getCreateTime());
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<ChatRepBean>>> call, Throwable t) {

            }
        });*/
    }
}
