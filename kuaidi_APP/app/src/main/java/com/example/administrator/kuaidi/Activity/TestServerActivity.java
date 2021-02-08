package com.example.administrator.kuaidi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.kuaidi.Http.HttpUtils.RetrofitClient;
import com.example.administrator.kuaidi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestServerActivity extends AppCompatActivity {

    @BindView(R.id.setserver)
    EditText setserver;
    @BindView(R.id.btn_set)
    Button btnSet;
    @BindView(R.id.btn_ignore)
    Button btnIgnore;

    private String server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_server);
        ButterKnife.bind(this);

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                server = setserver.getText().toString();
                RetrofitClient.SetUrl(server);
                ToastUtils.showShort("设置成功");
                Intent intent = new Intent(TestServerActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnIgnore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestServerActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
