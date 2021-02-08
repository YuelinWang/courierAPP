package com.example.administrator.kuaidi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.kuaidi.Http.HttpApi.ApiService;
import com.example.administrator.kuaidi.Http.HttpBean.BaseResponse;
import com.example.administrator.kuaidi.Http.HttpBean.RepMykuaidi;
import com.example.administrator.kuaidi.Http.HttpUtils.RetrofitClient;
import com.example.administrator.kuaidi.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.btn_search)
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        initToolbar();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = etSearch.getText().toString();
                SearchOrderInfo(number);
            }
        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("搜索快递");
    }

    private void SearchOrderInfo(String number){
        ApiService apiService = RetrofitClient.getInstance(this).CreateBaseApi();
        Map map = new HashMap();
        map.put("number", number);
        Call<BaseResponse<RepMykuaidi>> call = apiService.SearchOrderDetail(map);
        call.enqueue(new Callback<BaseResponse<RepMykuaidi>>() {
            @Override
            public void onResponse(Call<BaseResponse<RepMykuaidi>> call, Response<BaseResponse<RepMykuaidi>> response) {
                try{
                    if(response.body().getCode() == 0){
                        RepMykuaidi repMykuaidi = response.body().getData();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", repMykuaidi);
                        Intent intent = new Intent(SearchActivity.this, OrderDetailActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else{
                        ToastUtils.showShort(response.body().getDesc());
                    }
                }catch (Exception e){
                    ToastUtils.showShort(e.toString());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<RepMykuaidi>> call, Throwable t) {

            }
        });
    }
}
