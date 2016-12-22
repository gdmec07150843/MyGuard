package cn.edu.gdmec.s07150843.myguard.m7processmanager.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/**
 * Created by hasee on 2016/12/21.
 */
public class AutoKillProcessService extends Service{
    private ScreenLockReceiver receiver;

    public IBinder onBind(Intent intent) {
        return null;
        }


    public void onCreate() {
        super.onCreate();
        receiver = new ScreenLockReceiver();
        registerReceiver(receiver, new IntentFilter(Intent. ACTION_SCREEN_OFF));
        }




    public void	onDestroy()	{
        unregisterReceiver (receiver);
        receiver = null;
        super. onDestroy() ;
        }
    class ScreenLockReceiver	extends BroadcastReceiver {

        public void onReceive(Context context, Intent intent) {
            ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            for(ActivityManager. RunningAppProcessInfo info: am. getRunningAppProcesses()){
                String packname = info. processName;
                am. killBackgroundProcesses(packname);
                }
            }

	}
  	}


