package cn.edu.gdmec.s07150843.myguard.m8trafficmonitor.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import cn.edu.gdmec.s07150843.myguard.m8trafficmonitor.service.TrafficMonitoringService;
import cn.edu.gdmec.s07150843.myguard.m8trafficmonitor.utils.SystemInfoUtils;

/**
 * Created by hasee on 2016/12/22.
 */
public class BootCompleteReciever extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        if (!SystemInfoUtils. isServiceRunning(context, "cn. edu. gdmec. s071508043. myguard. m8trafficmonitor.service.TrafficMonitoringService")){

            Log. d("traff ic service","turn on");
            context. startService(new Intent(context, TrafficMonitoringService. class));
            }
        }
    }



