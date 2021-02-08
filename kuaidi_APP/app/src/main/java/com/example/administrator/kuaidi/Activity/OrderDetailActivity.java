package com.example.administrator.kuaidi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.kuaidi.Adapter.AdapterOrderSendState;
import com.example.administrator.kuaidi.Http.HttpApi.ApiService;
import com.example.administrator.kuaidi.Http.HttpBean.BaseResponse;
import com.example.administrator.kuaidi.Http.HttpBean.RepMykuaidi;
import com.example.administrator.kuaidi.Http.HttpBean.UserBean;
import com.example.administrator.kuaidi.Http.HttpUtils.RetrofitClient;
import com.example.administrator.kuaidi.ModelBean.UserInfoItem;
import com.example.administrator.kuaidi.R;
import com.example.administrator.kuaidi.Utils.MyUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_orderid)
    TextView tvOrderid;
    @BindView(R.id.tv_revName)
    TextView tvRevName;
    @BindView(R.id.tv_revAddr)
    TextView tvRevAddr;
    @BindView(R.id.tv_revPhone)
    TextView tvRevPhone;
    @BindView(R.id.tv_sendName)
    TextView tvSendName;
    @BindView(R.id.tv_sendAddr)
    TextView tvSendAddr;
    @BindView(R.id.tv_sendPhone)
    TextView tvSendPhone;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btn_author)
    TextView btnAuthor;
    @BindView(R.id.btn_setauthor)
    Button btnSetauthor;
    @BindView(R.id.btn_accept)
    Button btnAccept;
    @BindView(R.id.ll_author)
    LinearLayout llAuthor;

    private RepMykuaidi repMykuaidi;
    private AdapterOrderSendState mAdapter;
    private List<RepMykuaidi.ListState> orderSendStateBeanList = new ArrayList<>();

    private int type = 0;
    private int canSelAuthor = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);

        initToolbar();
        InitView();

        type = getIntent().getIntExtra("type",0);
        Bundle bundle = getIntent().getExtras();
        repMykuaidi = (RepMykuaidi) bundle.get("data");
        tvOrderid.setText(repMykuaidi.getNumber() + "");
        tvRevName.setText(repMykuaidi.getReceiver());
        tvRevAddr.setText(repMykuaidi.getRaddress());
        tvRevPhone.setText(repMykuaidi.getRphone());
        tvSendName.setText(repMykuaidi.getSender());
        tvSendAddr.setText(repMykuaidi.getSaddress());
        tvSendPhone.setText(repMykuaidi.getSphone());

        mAdapter.setNewData(repMykuaidi.getList());

        btnAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(canSelAuthor  == 0){
                    Intent intent = new Intent(OrderDetailActivity.this, AuthorActivity.class);
                    startActivityForResult(intent, 1);
                }
            }
        });

        btnSetauthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetAuthorToServer();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AcceptOrder();
            }
        });

        GetAuthorInfoFromServer();

        Log.e("++", "order detail: " + repMykuaidi.getState());
        if(repMykuaidi.getUser() == null){
            Log.e("++", "null: " );
        }
        btnSetauthor.setVisibility(View.INVISIBLE);

        //派件中
        if (repMykuaidi.getState() == 0) {
            if (repMykuaidi.getUser() != null) {
                btnAuthor.setEnabled(false);
                btnAuthor.setClickable(false);
                Log.e("++", "btnauthor : 0: user != null" );
            } else {
                btnAuthor.setEnabled(true);
                GetAuthorInfoFromServer();
            }

            btnAccept.setVisibility(View.GONE);
        } else if (repMykuaidi.getState() == 1) {
            btnAuthor.setEnabled(false);
            btnAuthor.setClickable(false);
            canSelAuthor = 1;
            btnAccept.setVisibility(View.VISIBLE);
            if(repMykuaidi.getUser() == null){
                llAuthor.setVisibility(View.GONE);
            }
        } else {
            btnAuthor.setEnabled(false);
            btnAuthor.setClickable(false);
            canSelAuthor = 1;
            btnAccept.setVisibility(View.VISIBLE);
            btnAccept.setEnabled(false);
            btnAccept.setText("已签单");
            if(repMykuaidi.getUser() == null){
                llAuthor.setVisibility(View.GONE);
            }
        }
    }

    private void initToolbar() {
        toolbar.setTitle("快递详情");
    }

    private void InitView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new AdapterOrderSendState(R.layout.item_ordersendstate, orderSendStateBeanList);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    private UserInfoItem authorInfoItem;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 22) {
                authorInfoItem = (UserInfoItem) data.getExtras().get("data");
                btnAuthor.setText(authorInfoItem.getRealname());
                btnSetauthor.setVisibility(View.VISIBLE);
            }

        }
    }

    private void SetAuthorToServer() {
        ApiService apiService = RetrofitClient.getInstance(this).CreateBaseApi();
        Map map = new HashMap();
        map.put("uid", authorInfoItem.getId());
        map.put("oid", repMykuaidi.getId());
        Call<BaseResponse> call = apiService.SerAuthor(map);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                try {
                    if (response.body().getCode() == 0) {
                        ToastUtils.showShort("授权成功");
                    }
                } catch (Exception e) {
                    ToastUtils.showShort(e.toString());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }

    private void GetAuthorInfoFromServer() {
        ApiService apiService = RetrofitClient.getInstance(this).CreateBaseApi();
        Map map = new HashMap();
        map.put("oid", repMykuaidi.getId());
        Call<BaseResponse<UserBean>> call = apiService.GerAuthor(map);
        call.enqueue(new Callback<BaseResponse<UserBean>>() {
            @Override
            public void onResponse(Call<BaseResponse<UserBean>> call, Response<BaseResponse<UserBean>> response) {
                try {
                    UserBean userBean = response.body().getData();
                    if(userBean != null){
                        canSelAuthor = 1;
                        btnAuthor.setText(userBean.getRealname());
                    }else{

                    }

                } catch (Exception e) {
                    ToastUtils.showShort(e.toString());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<UserBean>> call, Throwable t) {

            }
        });
    }

    private void AcceptOrder() {
        ApiService apiService = RetrofitClient.getInstance(this).CreateBaseApi();
        Map map = new HashMap();
        map.put("oid", repMykuaidi.getId());
        Call<BaseResponse> call = apiService.MyReceiveKuaidi(map);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                try {
                    if (response.body().getCode() == 0) {
                        ToastUtils.showShort("签单成功");
                    }
                } catch (Exception e) {
                    ToastUtils.showShort(e.toString());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent();
            intent.putExtra("result",type);
            setResult(22,intent);
            finish();
        }
        return false;
    }
}
