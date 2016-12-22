package cn.edu.gdmec.s07150843.myguard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.view.Window;
import android.widget.TextView;

import cn.edu.gdmec.s07150843.myguard.m1home.untils.MyUtils;
import cn.edu.gdmec.s07150843.myguard.m1home.untils.VersionUpdateUtils;

/**
 * Created by 姚永楠 on 2016/12/19.
 */

public class SplashActivity extends AppCompatActivity{
    private TextView mVersionTV;
    private String mVersion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        mVersion= MyUtils.getVersion(getApplicationContext());
        initView();
        final VersionUpdateUtils updateUtils=new VersionUpdateUtils(mVersion,SplashActivity.this);
        new Thread(){
            public void run(){
                updateUtils.getCloudVersion();
            };
        }.start();
    }

    private void initView() {
        mVersionTV=(TextView)findViewById(R.id.tv_splash_version);
        mVersionTV.setText("版本号："+mVersion);
    }
}
