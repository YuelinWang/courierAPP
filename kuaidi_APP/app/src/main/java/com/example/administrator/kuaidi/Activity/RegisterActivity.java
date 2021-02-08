package com.example.administrator.kuaidi.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.kuaidi.Http.HttpApi.ApiService;
import com.example.administrator.kuaidi.Http.HttpBean.BaseResponse;
import com.example.administrator.kuaidi.Http.HttpBean.RegisterBean;
import com.example.administrator.kuaidi.Http.HttpUtils.RetrofitClient;
import com.example.administrator.kuaidi.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.et_reg_acount)
    EditText etRegAcount;
    @BindView(R.id.et_reg_pwd)
    EditText etRegPwd;
    @BindView(R.id.et_confirm_pwd)
    EditText etConfirmPwd;
    @BindView(R.id.et_realname)
    EditText etRealname;
    @BindView(R.id.et_identityId)
    EditText etIdentityId;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_grade)
    EditText etGrade;
    @BindView(R.id.et_reg_nicheng)
    EditText etRegNicheng;
    @BindView(R.id.bt_reg)
    Button btReg;
    private RegisterBean registerBean = new RegisterBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        btReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_reg:
                if (handleRegClickEvent()) {
                    // ToastUtils.showShort("注册成功");

                    Map params = new HashMap<>();
                    params.put("username", registerBean.getUsername());
                    params.put("password", registerBean.getPassword());
                    params.put("realname", registerBean.getRealname());
                    params.put("phone", registerBean.getPhone());

                    ApiService apiService = RetrofitClient.getInstance(this).CreateBaseApi();
                    Call<BaseResponse> call = apiService.postRegister(params);
                    call.enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            try{
                                if (response.body().getCode() == 0) {
                                    ToastUtils.showShort("注册" + response.body().getDesc());
                                    finish();
                                } else {
                                    ToastUtils.showShort(response.body().getDesc());
                                }
                            }catch (Exception e){
                                ToastUtils.showShort(e.toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {
                            t.printStackTrace();
                            ToastUtils.showShort("网络异常请重试");
                        }
                    });
                }
                break;
        }
    }

    private boolean handleRegClickEvent() {
        boolean isCanRegister = true;
        if (TextUtils.isEmpty(etRegAcount.getText())) {
            ToastUtils.showShort("用户名不能为空");
            isCanRegister = false;
        } else if (TextUtils.isEmpty(etRegPwd.getText()) || TextUtils.isEmpty(etConfirmPwd.getText())) {
            ToastUtils.showShort("密码或者确认密码不能为空");
            isCanRegister = false;
        } else if (TextUtils.isEmpty(etRealname.getText())) {
            ToastUtils.showShort("真实姓名不能为空");
            isCanRegister = false;
        } else if (TextUtils.isEmpty(etPhone.getText())) {
            ToastUtils.showShort("电话号码不能为空");
            isCanRegister = false;
        }

        registerBean.setUsername(etRegAcount.getText().toString());
        registerBean.setPassword(etRegPwd.getText().toString());
        registerBean.setRealname(etRealname.getText().toString());
        registerBean.setPhone(etPhone.getText().toString());

        return isCanRegister;
    }
}
