package cn.edu.gdmec.s07150843.myguard.m9advancedtools.utils;

import android.content.Context;

/**
 * Created by chen on 2016/12/20.
 */
public class DensityUtil {

    public static int dip2px(Context context, float dxValue){
        try {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dxValue * scale + 0.5f);
        }catch (Exception e){
            e.printStackTrace();
        }
        return (int) dxValue;
    }
}
