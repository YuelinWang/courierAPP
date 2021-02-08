package com.example.administrator.kuaidi.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.kuaidi.ModelBean.UserInfoItem;
import com.example.administrator.kuaidi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserCommonActivity extends AppCompatActivity {

    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_userphone)
    TextView tvUserphone;

    private UserInfoItem userInfoItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_common);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        userInfoItem = (UserInfoItem)bundle.get("data");

        tvUsername.setText(userInfoItem.getRealname());
        tvUserphone.setText(userInfoItem.getPhone());
    }
}
