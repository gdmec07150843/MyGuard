package cn.edu.gdmec.s07150843.myguard.m9advancedtools;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.s07150843.myguard.R;
import cn.edu.gdmec.s07150843.myguard.m9advancedtools.fragment.AppLockFragment;
import cn.edu.gdmec.s07150843.myguard.m9advancedtools.fragment.AppUnLockFragment;

public class AppLockActivity  extends FragmentActivity implements View.OnClickListener{
      private ViewPager mAppViewPager;
      List<Fragment> mFragments=new ArrayList<Fragment>(;
      private TextView mLockTVV;
     private View slideLockView;
    private View sildeUnLockView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_lock);
    }

    @Override
    public void onClick(View view) {

    }
}
