package cn.edu.gdmec.s07150843.myguard.m9advancedtools.adapter;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import cn.edu.gdmec.s07150843.myguard.m9advancedtools.entity.AppInfo;

/**
 * Created by chen on 2016/12/20.
 */
public class AppLockAdapter extends BaseAdapter{
    private List<AppInfo> appInfos;
     private Context context;




 @Override
 public int getCount() {
  return appInfos.size();
 }

 @Override
 public Object getItem(int position) {
  return appInfos.get(position);
 }

 @Override
 public long getItemId(int position) {
  return position;
 }

 @Override
 public View getView(int position, View converView, ViewGroup parent) {
   ViewHolder holder;
  if(converView!=null&&converView instanceof RelativeLayout){
   holder=(ViewHolder)converView.getTag();

  }else{
   holder=new ViewHolder();
  }
  return converView;
 }
 static class ViewHolder{
  TextView mAppNameTV;
  ImageView mAppIconImgv;
  ImageView mLockIcon;
 }
}
