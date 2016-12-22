package cn.edu.gdmec.s07150843.myguard.m4appmanager;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import cn.edu.gdmec.s07150843.myguard.m4appmanager.adapter.AppManagerAdapter;
import cn.edu.gdmec.s07150843.myguard.m4appmanager.entity.AppInfo;

public class AppManagerActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mPhoneMemoryTV;
    private TextView mSDMemoryTV;
    private ListView mListView;
    private List<AppInfo> appInfos;
    private List<AppInfo> userAppInfos=new ArrayList<AppInfo>();
    private List<AppInfo> systemAppInfos=new ArrayList<AppInfo>();
    private AppManagerAdapter adapter;
    private UninstallRececiver receciver;
    private Handler mHandler =new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what){
                case 10:
                    if(adapter==null){
                        adapter=new AppManagerAdapter(userAppInfos,systemAppInfos,AppManagerActivity.this);
                    }
                    mListView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 15:
                    adapter.notifyDataSetChanged();
                    break;

            }
        };
    };
    private TextView mAppNumTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_app_manager);
        receciver=new UninstallRececiver();
        IntentFilter intentFilter=new IntentFilter(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addDataScheme("package");
        registerReceiver(receciver,intentFilter);
        initView();
    }

    private void initView() {
        findViewById(R.id.rl_titlebar).setBackgroundColor(getResources().getColor(R.color.bright_yellow));
        ImageView mLeftImgv=(ImageView)findViewById(R.id.imgv_leftbtn);
        ((TextView)findViewById(R.id.tv_title)).setText("软件管家");
        mLeftImgv.setOnClickListener(this);
        mLeftImgv.setImageResource(R.drawable.back);
        mPhoneMemoryTV=(TextView)findViewById(R.id.tv_phonememory_appmanager);
    }

    @Override
    public void onClick(View v) {

    }
}
