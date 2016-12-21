package cn.edu.gdmec.s07150843.myguard.m9advancedtools.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import cn.edu.gdmec.s07150843.myguard.m9advancedtools.adapter.AppLockAdapter;
import cn.edu.gdmec.s07150843.myguard.m9advancedtools.db.dao.AppLockDao;
import cn.edu.gdmec.s07150843.myguard.m9advancedtools.entity.AppInfo;

/**
 * Created by chen on 2016/12/20.
 */
public class AppLockFragment extends Fragment{

    private TextView mUnLockTV;
    private ListView mUnLockLV;
    List<AppInfo> unlockApps=new ArrayList<AppInfo>();
    private AppLockAdapter adapter;
    private AppLockDao dao;
    private Uri uri=Uri.parse("content://com.itcast.mobilesafe.applock");
    private List<AppInfo> appInfos;
    private Handler mhandler=new Handler(){
           public void handleMessage(android.os.Message msg){
               switch (msg.what){
                   case 100:
                       unlockApps.clear();
                       unlockApps.addAll((List<AppInfo>)msg.obj);
                       if(adapter==null){
                           adapter=new AppLockAdapter(unlockApps,getActivity());
                           mUnLockLV.setAdapter(adapter);
                       }else{
                           adapter.notifyDataSetChanged();
                       }
                       mUnLockTV.setText("未加锁应用"+unlockApps.size()+"个");
                       break;
               }
           }
    };


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
       View view =inflater.inflate();

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
