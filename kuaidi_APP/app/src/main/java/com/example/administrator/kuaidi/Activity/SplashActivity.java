package com.example.administrator.kuaidi.Activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.example.administrator.kuaidi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import yanzhikai.textpath.PathAnimatorListener;
import yanzhikai.textpath.SyncTextPathView;
import yanzhikai.textpath.painter.FireworksPainter;

public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.stpv_ForYou)
    SyncTextPathView stpvForYou;

    private SPUtils  spUtils ;
    private boolean bLogined = true;
    private String   Account;
    private String   pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

       spUtils = SPUtils.getInstance();

       bLogined = spUtils.getBoolean("Logined");
        Log.e("SplashActivity", "Logined: "+bLogined );

        stpvForYou.setPathPainter(new FireworksPainter());
        stpvForYou.startAnimation(0,1);
        stpvForYou.setAnimatorListener(new PathAnimatorListener(){
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                stpvForYou.showFillColorText();
                HandEnterActivity();
            }
        });
    }

    private void HandEnterActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                //intent.putExtra("account",Account);
               // intent.putExtra("pwd",pwd);
                startActivity(intent);
                finish();

               /* if(!bLogined){
                    spUtils.put("Logined",true);
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);;
                    startActivity(intent);
                    finish();
                }*/
            }
        },1000);
    }
}
