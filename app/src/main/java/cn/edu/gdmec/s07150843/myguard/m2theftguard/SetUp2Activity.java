package cn.edu.gdmec.s07150843.myguard.m2theftguard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import cn.edu.gdmec.s07150843.myguard.R;
public class SetUp2Activity extends BaseSetUpActivity implements View.OnClickListener {
    private TelephonyManager mTelephonyManager;
    private Button mBindSIMBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up2);
        mTelephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        initView();
    }

    private void initView() {
        //设置第二个小圆点的颜色
        ((RadioButton)findViewById(R.id.rb_second)).setChecked(true);
        mBindSIMBtn = (Button)findViewById(R.id.btn_bind_sim);
        mBindSIMBtn.setOnClickListener(this);
    }

    private boolean isBind(){
        String simString = sp.getString("sim", null);
        if (TextUtils.isEmpty(simString)){
            return false;
        }
        return true;
    }

    @Override
    public void showNext(){
        if (!isBind()){
            Toast.makeText(this, "您还么有绑定SIM卡", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivityAndFinishSelf(SetUp3Activity.class);
    }

    @Override
    public void showPre(){
        startActivityAndFinishSelf(SetUp1Activity.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_bind_sim:
                //绑定SIM卡
                bindSIM();
                break;
        }
    }

    /*绑定sim卡*/
    private void bindSIM(){
        if (!isBind()){
            String simSerialNumber = mTelephonyManager.getSimSerialNumber();
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("sim", simSerialNumber);
            edit.commit();
            Toast.makeText(this, "SIM绑定成功！", Toast.LENGTH_SHORT).show();
            mBindSIMBtn.setEnabled(false);
        }else{
            //已经绑定，提醒用户
            Toast.makeText(this, "SIM卡已经绑定", Toast.LENGTH_SHORT).show();
            mBindSIMBtn.setEnabled(false);
        }
    }
}