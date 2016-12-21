package cn.edu.gdmec.s07150843.myguard.m9advancedtools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import cn.edu.gdmec.s07150843.myguard.R;

public class AdvancedToolsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_advanced_tools);
        initView();
    }

    private void initView() {
         findViewById(R.id.rl_titlebar).setBackgroundColor(getResources().getColor(R.color.bright_red));
        ImageView mLeftImgv=(ImageView)findViewById(R.id.imgv_leftbtn);


    }


    @Override
    public void onClick(View view) {

    }
}
