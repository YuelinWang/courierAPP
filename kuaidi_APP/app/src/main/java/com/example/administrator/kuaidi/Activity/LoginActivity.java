package com.example.administrator.kuaidi.Activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.kuaidi.Const.Const;
import com.example.administrator.kuaidi.Http.HttpApi.ApiService;
import com.example.administrator.kuaidi.Http.HttpBean.BaseResponse;
import com.example.administrator.kuaidi.Http.HttpBean.LoginBean;
import com.example.administrator.kuaidi.Http.HttpBean.LoginBeanRep;
import com.example.administrator.kuaidi.Http.HttpUtils.RetrofitClient;
import com.example.administrator.kuaidi.R;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "++";

    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.et_acount)
    EditText etAcount;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.rg_login)
    RadioGroup rgLogin;
    @BindView(R.id.bt_lgn_reg)
    TextView btLgnReg;
    @BindView(R.id.bt_lgn_setserver)
    TextView btLgnSetserver;

    private SPUtils spUtils;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        spUtils = SPUtils.getInstance();
        int uid = spUtils.getInt("uid");
        String account = spUtils.getString("Account");
        String pwd = spUtils.getString("pwd");

        etAcount.setText(account);
        etPwd.setText(pwd);

        requestPermissions();

        btLogin.setOnClickListener(this);
        btLgnReg.setOnClickListener(this);

        //apiService = RetrofitClient.getInstance(this).CreateBaseApi();
        btLgnSetserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,TestServerActivity.class);
                startActivity(intent);
            }
        });

    }

    private void requestPermissions() {
        RxPermissions rxPermissions = new RxPermissions(LoginActivity.this);
        rxPermissions.requestEachCombined(Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            Log.d(TAG, permission.name + "is granted");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            Log.d(TAG, permission.name + "is denied. More info should be provided");
                        } else {
                            Log.d(TAG, permission.name + "is denied");
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                Log.e(TAG, "onClick: " + etAcount.getText() + "  " + etPwd.getText());
                if (TextUtils.isEmpty(etAcount.getText()) || TextUtils.isEmpty(etPwd.getText())) {
                    ToastUtils.showShort("账号或者密码不能为空");
                } else {
                    int nType = rgLogin.getCheckedRadioButtonId();
                    int nLoginType = 0;
                    if (nType == R.id.rb_find) {
                        // ToastUtils.showShort("我要找");
                        nLoginType = 0;
                    } else {
                        //ToastUtils.showShort("我要招");
                        nLoginType = 1;
                    }

                    LoginBean loginBean = new LoginBean();
                    loginBean.setUsername(etAcount.getText().toString());
                    loginBean.setPassword(etPwd.getText().toString());
                    loginBean.setRole(nLoginType);

                    ApiService apiService = RetrofitClient.getInstance(this).CreateBaseApi();
                    Map map = new HashMap();
                    map.put("username", loginBean.getUsername());
                    map.put("password", loginBean.getPassword());
                    Call<BaseResponse<LoginBeanRep>> call = apiService.GetLogin(map);
                    call.enqueue(new Callback<BaseResponse<LoginBeanRep>>() {
                        @Override
                        public void onResponse(Call<BaseResponse<LoginBeanRep>> call, Response<BaseResponse<LoginBeanRep>> response) {
                            try{
                                if (response.body().getCode() == 0) {
                                    Log.e(TAG, "onResponse: " + response.body().getDesc() + "  " + response.body().getData().getId() + " " + response.body().getData().getRealname()
                                            + "  " + response.raw().toString());
                                    LoginBeanRep rep = (LoginBeanRep) response.body().getData();
                                    if (rep != null) {
                                        Log.e(TAG, "LoginBeanRep: " + response.body());

                                        spUtils.put("uid", rep.getId());
                                        spUtils.put("Account", etAcount.getText().toString());
                                        spUtils.put("pwd", etPwd.getText().toString());

                                        setJpushAlias(etAcount.getText().toString(), rep.getId());
                                        Intent intent = new Intent(LoginActivity.this, Main2Activity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                } else {
                                    ToastUtils.showShort("账号或者密码输入错误，请重新输入");
                                    etAcount.setText("");
                                    etPwd.setText("");
                                }
                            }catch (Exception e){
                                ToastUtils.showShort(e.toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse<LoginBeanRep>> call, Throwable t) {

                        }
                    });
                }
                break;
            case R.id.bt_lgn_reg:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void setJpushAlias(String user, int uid) {
        String macAddress = DeviceUtils.getMacAddress();
        String strUid = uid + "";
        JPushInterface.setAlias(this, uid, strUid);
        Log.e("++", "setJpushAlias: " + uid + "   " + user );
    }
}
