package com.example.administrator.kuaidi.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.kuaidi.Adapter.ChatMessageAdapter;
import com.example.administrator.kuaidi.Http.HttpApi.ApiService;
import com.example.administrator.kuaidi.Http.HttpBean.BaseResponse;
import com.example.administrator.kuaidi.Http.HttpUtils.RetrofitClient;
import com.example.administrator.kuaidi.ModelBean.ChatMessage;
import com.example.administrator.kuaidi.ModelBean.MsgBean;
import com.example.administrator.kuaidi.R;
import com.example.administrator.kuaidi.jpush.ExampleUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatMsgActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Button mButtonSend;
    private EditText mEditTextMessage;
    private ImageView mImageView;

    private ChatMessageAdapter mAdapter;

    private int oterId;
    private static boolean bisForeground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_msg);

        init();
        //Bundle bundle = getIntent().getExtras();
       // workListBean = (WorkListItem) bundle.getSerializable("work");
        oterId = getIntent().getIntExtra("oid",0);
        registerMessageReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bisForeground = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        bisForeground = false;
    }

    public static boolean isForeground(){
        return bisForeground;
    }

    private void init(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mButtonSend = (Button) findViewById(R.id.btn_send);
        mEditTextMessage = (EditText) findViewById(R.id.et_message);
        mImageView = (ImageView) findViewById(R.id.iv_image);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ChatMessageAdapter(this, new ArrayList<ChatMessage>());
        mRecyclerView.setAdapter(mAdapter);

        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEditTextMessage.getText().toString();
                if (TextUtils.isEmpty(message)) {
                    return;
                }
                sendMessage(message,"");
                SetChatReq();
                mEditTextMessage.setText("");
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendMessage();
            }
        });
    }

    private void sendMessage(String message, String username) {
        String date = new SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(new Date());
        ChatMessage chatMessage = new ChatMessage(message, true, false, username,date);
        mAdapter.add(chatMessage);

        //mimicOtherMessage(message);
    }

    private void mimicOtherMessage(String message, String username) {
        String date = new SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(new Date());
        ChatMessage chatMessage = new ChatMessage(message, false, false,username,date);
        mAdapter.add(chatMessage);

        mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
    }

    private void sendMessage() {
        ChatMessage chatMessage = new ChatMessage(null, true, true,null,null);
        mAdapter.add(chatMessage);

        mimicOtherMessage();
    }

    private void mimicOtherMessage() {
        ChatMessage chatMessage = new ChatMessage(null, false, true,null,null);
        mAdapter.add(chatMessage);

        mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
    }


    public static final String MESSAGE_RECEIVED_ACTION = "com.example.foryou2.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    private MessageReceiver mMessageReceiver;
    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    Log.e("JIGUANG", "onReceive: " + "message" );
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }

                    Bundle bundle = intent.getExtras();
                    MsgBean msgBean  = (MsgBean) bundle.getSerializable("chat");
                    if(msgBean.getUid() == SPUtils.getInstance().getInt("uid")){
                        sendMessage(msgBean.getContent(),msgBean.getTitle());
                    }else{
                        mimicOtherMessage(msgBean.getContent(),msgBean.getTitle());
                    }
                   // setCostomMsg(msgBean.getContent());
                    Log.e("JIGUANG", "onReceive: " + showMsg.toString());
                }
            } catch (Exception e){
            }
        }
    }

    private void setCostomMsg(String msg){
        //sendMessage(msg);
    }

    private  void SetChatReq(){
        ApiService apiService = RetrofitClient.getInstance(this).CreateBaseApi();
        String message = mEditTextMessage.getText().toString();
        Map map = new HashMap();
        map.put("uidOne", SPUtils.getInstance().getInt("uid"));
        map.put("uidTwo", oterId);
        map.put("content", message);
        map.put("group", 0);

      /*  Call<BaseResponse<SendChatBean>> call = apiService.SetChat(map);;
        call.enqueue(new Callback<BaseResponse<SendChatBean>>() {
            @Override
            public void onResponse(Call<BaseResponse<SendChatBean>> call, Response<BaseResponse<SendChatBean>> response) {
                if(response.body().getCode() == 0){
                    ToastUtils.showShort("发送成功");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<SendChatBean>> call, Throwable t) {

            }
        });*/
    }
}
