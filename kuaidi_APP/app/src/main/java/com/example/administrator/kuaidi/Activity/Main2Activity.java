package com.example.administrator.kuaidi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.kuaidi.Adapter.ViewPagerAdapter;
import com.example.administrator.kuaidi.Fragment.FriendsFragment;
import com.example.administrator.kuaidi.Fragment.MsgFragment;
import com.example.administrator.kuaidi.Fragment.MyFragment;
import com.example.administrator.kuaidi.Fragment.MyKuaidiFragment;
import com.example.administrator.kuaidi.Fragment.UsersFragment;
import com.example.administrator.kuaidi.Http.HttpApi.ApiService;
import com.example.administrator.kuaidi.Http.HttpBean.BaseResponse;
import com.example.administrator.kuaidi.Http.HttpBean.LoginBeanRep;
import com.example.administrator.kuaidi.Http.HttpBean.RepMykuaidi;
import com.example.administrator.kuaidi.Http.HttpUtils.RetrofitClient;
import com.example.administrator.kuaidi.R;
import com.example.administrator.kuaidi.Utils.MyUtils;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tablayout)
    TabLayout tablayout;

    private static final String TAG = "++";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] titles = new String[]{"所有用户", "我的好友", "我的快递", "我的"};
    private ViewPagerAdapter viewpagerAdapter;
    private static final int RQUEST_ZXING = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        initToolbar();
        initData();
        initPagerAdapter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tablayout != null) {
            tablayout.clearOnTabSelectedListeners();
        }
    }

    private void initData() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        tablayout.setupWithViewPager(viewpager);
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Fragment userFragment = new UsersFragment();
        Fragment friendFragment = new FriendsFragment();
        Fragment myOrderFragment = new MyKuaidiFragment();
        Fragment customFragment = new MyFragment();
        fragments.add(userFragment);
        fragments.add(friendFragment);
        fragments.add(myOrderFragment);
        fragments.add(customFragment);
        //Fragment msgFragment = new MsgFragment();
       // fragments.add(msgFragment);

    }

    private void initPagerAdapter() {
        viewpagerAdapter = new ViewPagerAdapter(titles, fragments, getSupportFragmentManager());
        viewpager.setAdapter(viewpagerAdapter);
        viewpager.setOffscreenPageLimit(titles.length);
        viewpager.setCurrentItem(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.publicitem, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       // switch (item.getItemId()) {
           // case R.id.pub:
                //Intent intent = new Intent(Main2Activity.this, PublicActivity.class);
                //startActivity(intent);
            //    break;
      //  }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("快递");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  finish();
            }
        });

        toolbar.inflateMenu(R.menu.publicitem);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.qrcode:
                        Intent intent = new Intent(Main2Activity.this,CaptureActivity.class);
                        startActivityForResult(intent, RQUEST_ZXING);
                        break;
                    case  R.id.sendKd:
                        Intent intent1 = new Intent(Main2Activity.this,SendKdActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.search:
                        Intent intent2 = new Intent(Main2Activity.this, SearchActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });

        /*{
            Menu menu = toolbar.getMenu();
            if(menu != null){
                MenuItem menuItem = menu.findItem(R.id.pub);
                menuItem.setVisible(false);
                MenuItem menuItem1 = menu.findItem(R.id.order);
                menuItem1.setVisible(false);
            }
        }*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RQUEST_ZXING){
            if(data != null){
                Bundle bundle = data.getExtras();
                if(bundle == null){
                    return;
                }
                if(bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS){
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    SearchOrderInfo(result);
                   // Toast.makeText(this,"解析结果:" + result, Toast.LENGTH_SHORT).show();
                    //Log.e(TAG, "onActivityResult: " + result );
                }else if(bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED){
                    ToastUtils.showShort("解析二维码失败");
                    //Log.e(TAG, "onActivityResult: 失败" );
                }
            }
        }

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
                        Intent intent = new Intent(Main2Activity.this, OrderDetailActivity.class);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK && event.getRepeatCount() == 0) {
            MyUtils.LoginOut(this);
        }
        return false;
    }
}
