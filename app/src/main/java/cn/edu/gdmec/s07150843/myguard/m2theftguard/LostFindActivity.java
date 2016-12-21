package cn.edu.gdmec.s07150843.myguard.m2theftguard;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class LostFindActivity extends Activity implements View.OnClickListener {

    private TextView mSafePhoneTV;
    private RelativeLayout mInterSetupRL;
    private SharedPreferences msharedPreferences;
    private ToggleButton mToggleButton;
    private TextView mProtectStatusTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lost_find);
        msharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        if (!isSetUp()){
            //如果没有进入过设置向导，则进入
            startSetUpActibity();
        }
        initView();
    }

    private boolean isSetUp(){
        return msharedPreferences.getBoolean("isSetUp", false);
    }

    /*初始化控件*/
    private void initView(){
        TextView mTitleTV = (TextView)findViewById(R.id.tv_title);
        mTitleTV.setText("手机防盗");
        ImageView mLeftImgv = (ImageView)findViewById(R.id.imgv_leftbtn);
        mLeftImgv.setImageResource(R.drawable.back);
        findViewById(R.id.rl_titlebar).setBackgroundColor(
                getResources().getColor(R.color.purple)
        );
        mSafePhoneTV = (TextView)findViewById(R.id.tv_safephone);

    }
    @Override
    public void onClick(View v) {

    }
}
