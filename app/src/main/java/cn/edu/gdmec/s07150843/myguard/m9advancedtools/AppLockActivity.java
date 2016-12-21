package cn.edu.gdmec.s07150843.myguard.m9advancedtools;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class AppLockActivity  extends FragmentActivity implements View.OnClickListener{
      private ViewPager mAppViewPager;
      List<Fragment> mFragments=new ArrayList<Fragment>();
      private TextView mLockTV;
    private TextView mUnLockTV;
      private View slideLockView;
      private View sildeUnLockView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_app_lock);
        initView();
        initListener();
    }
  private void initListener(){
      mAppViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
          @Override
          public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

          }

          @Override
          public void onPageSelected(int arg0) {
                if(arg0==0){
                    slideLockView.setBackgroundResource(R.drawable.slide_view);
                    slideLockView.setBackgroundColor(getResources().getColor(R.color.transparent));
                    mLockTV.setTextColor(getResources().getColor(R.color.black));
                    mUnLockTV.setTextColor(getResources().getColor(R.color.bright_red));

                }else{
                    slideLockView.setBackgroundResource(R.drawable.slide_view);
                    slideLockView.setBackgroundColor(getResources().getColor(R.coor.slide_view));

                    mLockTV.setTextColor(getResources().getColor(R.color.bright_red));
                    mUnLockTV.setTextColor(getResources().getColor(R.color.black));
                }
          }

          @Override
          public void onPageScrollStateChanged(int state) {

          }
      });
  }
    private void initView() {
        findViewById(R.id.rl_titlebar).setBackgroundColor(getResources().getColor(R.color.bright_red));
        ImageView mLeftImgv=findViewById(R.id.imgv_leftbtn);
        ((TextView)findViewById(R.id.tv_title).setText("程序锁");

    }

    @Override
    public void onClick(View view) {

    }
}
