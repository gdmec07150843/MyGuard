package cn.edu.gdmec.s07150843.myguard.m9advancedtools;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.s07150843.myguard.m9advancedtools.SMSReducitionActivity;

public class EnterPswActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView mAppIcon;
    private TextView mAppNameTV;
    private EditText mPswET;
    private ImageView mGoImgv;
    private LinearLayout mEnterPswLL;
    private SharedPreferences sp;
    private String password;
    private String packagename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestWindowFeature(Window.FEATURE_N0_TITLE);
        setContentView(R.layout.activity_enter_psw);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        password = sp.getString("PhoneAntiTheftPWD",nu11);
        Intent intent = getIntent();
        packagename = intent.getStringExtra("packagename");
        PackageManager pm = getPackageManager();
        initView();
        try {
            mAppIcon.setImageDrawable(pm.getApplicationInfo(packagename, 0).loadIcon(pm));

            mAppNameTV.setText(pm.getApplicationInfo(packagename, 0).loadLabel(pm).toString())
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void initView() {
        mAppIcon = (ImageView) findViewById(R.id.imgv_appicon_enterpsw);
        mAppNameTV = (TextView) findViewById(R.id.tv_appname_enterpsw);
        mPswET = (EditText) findViewById(R.id.et_psw_enterpsw);
        mGoImgv = (ImageView) findViewById(R.id.imgv_go_enterpsw);
        mEnterPswLL = (LinearLayout) findViewById(R.id.ll_enterpsw);
        mGoImgv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgv_go_enterpsw:
                String inputpsw=mPswET.getText().toString().trim();
                if(TextUtils.isEmpty(inputpsw)){
                    startAnim();
                    Toast.makeText(this,"请输入密码",0).show();
                    return;
                }else{
                    if (!TextUtils.isEmpty(password)) {

                        if(MD5Utils.encode(inputpsw).equals(password)){
                            Intent intent=new Intent();
                            intent.setAction("cn.itcast.mobliesafe.applock");
                            intent. putExtra("packagename", packagename) ;
                            sendBroadcast(intent) ;
                            finish();
                        }else{
                            startAnim();

                            Toast. makeText(this,"密码不正确!",0).show();
                            return;
                        }
                    }
                }
                break;
        }
    }
}
