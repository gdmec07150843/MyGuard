package cn.edu.gdmec.s07150843.myguard.m4appmanager.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by 姚永楠 on 2016/12/22.
 */
public class AppInfo {
    public String packageName;
    public Drawable icon;
    public String appName;
    public String apkPath;
    public  long appSize;
    public boolean isInRoom;
    public boolean isSelected=false;
    public boolean isUserApp;
    public String getAppLocation(boolean isInRoom){
        if(isInRoom){
            return "手机内存";
        }else {
            return "外部存储";
        }
    }
}
