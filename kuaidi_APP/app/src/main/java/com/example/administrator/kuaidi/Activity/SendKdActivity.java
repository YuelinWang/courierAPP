package com.example.administrator.kuaidi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.kuaidi.Http.HttpApi.ApiService;
import com.example.administrator.kuaidi.Http.HttpBean.BaseResponse;
import com.example.administrator.kuaidi.Http.HttpBean.LoginBeanRep;
import com.example.administrator.kuaidi.Http.HttpBean.RepSendKd;
import com.example.administrator.kuaidi.Http.HttpUtils.RetrofitClient;
import com.example.administrator.kuaidi.R;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendKdActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_orderid)
    EditText tvOrderid;
    @BindView(R.id.tv_revName)
    EditText tvRevName;
    @BindView(R.id.tv_revAddr)
    EditText tvRevAddr;
    @BindView(R.id.tv_revPhone)
    EditText tvRevPhone;
    @BindView(R.id.tv_sendName)
    EditText tvSendName;
    @BindView(R.id.tv_sendAddr)
    EditText tvSendAddr;
    @BindView(R.id.tv_sendPhone)
    EditText tvSendPhone;
    @BindView(R.id.sendBtn)
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_kd);
        ButterKnife.bind(this);
        initToolbar();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCanSend()){
                    sendKuaidi();
                }
            }
        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("填写快递信息");
    }

    private boolean isCanSend(){
        boolean isCanSend = true;
        if (TextUtils.isEmpty(tvOrderid.getText())) {
            ToastUtils.showShort("快递单号不能为空");
            isCanSend = false;
        }else if (TextUtils.isEmpty(tvRevName.getText())) {
            ToastUtils.showShort("收件人名字不能为空");
            isCanSend = false;
        }else if (TextUtils.isEmpty(tvRevAddr.getText())) {
            ToastUtils.showShort("收件人地址不能为空");
            isCanSend = false;
        }else if (TextUtils.isEmpty(tvRevPhone.getText())) {
            ToastUtils.showShort("收件人电话不能为空");
            isCanSend = false;
        }else if (TextUtils.isEmpty(tvSendName.getText())) {
            ToastUtils.showShort("发件人名字不能为空");
            isCanSend = false;
        }else if (TextUtils.isEmpty(tvSendAddr.getText())) {
            ToastUtils.showShort("发件人地址不能为空");
            isCanSend = false;
        }else if (TextUtils.isEmpty(tvSendPhone.getText())) {
            ToastUtils.showShort("发件人电话不能为空");
            isCanSend = false;

        }
         return isCanSend;
    }

    private void sendKuaidi(){
        ApiService apiService = RetrofitClient.getInstance(this).CreateBaseApi();
        Map map = new HashMap();
        map.put("uid", SPUtils.getInstance().getInt("uid"));
        map.put("number", tvOrderid.getText().toString());
        map.put("receiver", tvRevName.getText().toString());
        map.put("sender", tvSendName.getText().toString());
        map.put("saddress", tvSendAddr.getText().toString());
        map.put("raddress", tvRevAddr.getText().toString());
        map.put("sphone", tvSendPhone.getText().toString());
        map.put("rphone", tvRevPhone.getText().toString());
        Call<BaseResponse<RepSendKd>> call = apiService.SendKuaidi(map);
        call.enqueue(new Callback<BaseResponse<RepSendKd>>() {
            @Override
            public void onResponse(Call<BaseResponse<RepSendKd>> call, Response<BaseResponse<RepSendKd>> response) {
                try{
                    if(response.body().getCode() == 0){
                        ToastUtils.showShort("发送快递成功");
                        tvOrderid.setText("");
                        tvRevName.setText("");
                        tvSendName.setText("");
                        tvSendAddr.setText("");
                        tvRevAddr.setText("");
                        tvSendPhone.setText("");
                        tvRevPhone.setText("");
                    }else{

                    }
                }catch (Exception e){
                    ToastUtils.showShort(e.toString());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<RepSendKd>> call, Throwable t) {

            }
        });
    }
}
