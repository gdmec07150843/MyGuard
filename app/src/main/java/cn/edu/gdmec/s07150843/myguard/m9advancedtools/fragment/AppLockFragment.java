package cn.edu.gdmec.s07150843.myguard.m9advancedtools.fragment;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.s07150843.myguard.R;
import cn.edu.gdmec.s07150843.myguard.m9advancedtools.adapter.AppLockAdapter;
import cn.edu.gdmec.s07150843.myguard.m9advancedtools.db.dao.AppLockDao;
import cn.edu.gdmec.s07150843.myguard.m9advancedtools.entity.AppInfo;
import cn.edu.gdmec.s07150843.myguard.m9advancedtools.utils.AppInfoParser;

/**
 * Created by chen on 2016/12/20.
 */
public class AppLockFragment extends Fragment {

    private TextView mLockTV;
    private ListView mLockLV;
    List<AppInfo> mLockApps = new ArrayList<AppInfo>();
    private AppLockAdapter adapter;
    private AppLockDao dao;
    private Uri uri = Uri.parse("content://com.itcast.mobilesafe.applock");
    /*  private List<AppInfo> appInfos;*/
    private Handler mhandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 100:
                    mLockApps.clear();
                    mLockApps.addAll((List<AppInfo>) msg.obj);
                    if (adapter == null) {
                        adapter = new AppLockAdapter(mLockApps, getActivity());
                        mLockLV.setAdapter(adapter);
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                    mLockTV.setText("未加锁应用" + mLockApps.size() + "个");
                    break;
            }

        }
    };
  private List<AppInfo>appInfos;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
       View view =inflater.inflate(R.layout.fragment_applock,null);
        mLockTV =(TextView)view.findViewById(R.id.tv_lock);
        mLockLV=(TextView)view.findViewById(R.id.lv_lock);
        return view;
    }

    @Override
    public void onResume() {
        dao=new AppInfoParser.getAppInfos(getActivity());
        fillData();
        initListener();
        getActivity().getContentResolver().registerContentObserver(uri, true, new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
              fillData();
            }
        });
        super.onResume();
    }
    private void fillData(){
        final List<AppInfo>aInfos=new ArrayList<AppInfo>();
        new Thread(){
            public void run(){
                for(AppInfo appInfo : appInfos){
                    if(dao.find(appInfo.packageName)){
                        appInfo.isLock=true;
                        aInfos.add(appInfo);
                    }
                }
                Message msg=new Message();
                msg.obj=aInfos;
                msg.what=10;
                mhandler.sendMessage(msg);
            }
        }.start();
    }
    private void initListener(){
        mLockLV.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                TranslateAnimation ta=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,-1.0f,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
                       ta.setDuration(300);
                view.startAnimation(ta);
                new Thread(){
                    public void run(){
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               /* dao.delete(mLockApps.get(position).packageName);*/
                                dao.delete(mLockApps.get(position).packageName);
                                mLockApps.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                }.start();
            }
        });
    }
}
