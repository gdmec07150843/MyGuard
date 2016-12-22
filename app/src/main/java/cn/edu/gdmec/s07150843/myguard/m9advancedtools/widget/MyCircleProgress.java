package cn.edu.gdmec.s07150843.myguard.m9advancedtools.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.renderscript.Type;
import android.util.AttributeSet;
import android.widget.Button;

import cn.edu.gdmec.s07150843.myguard.R;
/*cuowu*/
import cn.edu.gdmec.s07150843.myguard.m9advancedtools.SMSBackupActivity;
import cn.edu.gdmec.s07150843.myguard.m9advancedtools.SMSReducitionActivity;
import cn.edu.gdmec.s07150843.myguard.m9advancedtools.utils.DensityUtil;

/**
 * Created by chen on 2016/12/21.
 */
public class MyCircleProgress extends Button {

    private Paint paint;
    private int progress;
    private int max;
    private int mCircleColor;
    private int mProgressColor;
    private float roundWidth;
    private int mProgressTextSize;
    private  Context context;

    private float mDistanceOFbg;
    public MyCircleProgress(Context context) {
        this(context,null);
    }
    public MyCircleProgress(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }
    public MyCircleProgress(Context context,AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        paint=new Paint();
        this.context=context;
        TypedArray typedArray=context.obtainStyledAttributes(attrs,appcompat.R.styleable.MyCircleProgress);
        progress=typedArray.getInteger(R.styleable.MyCircleProgress_progress,0);
        max=typedArray.getInteger(R.styleable.MyCircleProgress_max,100);
        mCircleColor=typedArray.getColor(R.styleable.MyCircleProgress_circleColor, Color.RED);
        mProgressColor=typedArray.getColor(R.styleable.MyCirleProgress_progressColor,Color.WHITE);
        roundWidth= DensityUtil.dip2px(context,5);
        mDistanceOFbg=DensityUtil.dip2px(context,5);
        mProgressTextSize=DensityUtil.dip2px(context,16);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX=getWidth()/2;
        int centerY=getHeight()/2;
        int radius=(int)(centerX-mDistanceOFbg);
        paint.setColor(mCircleColor);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth);
        canvas.drawCircle(centerX,centerY,radius,paint);

        paint.setColor(mProgressColor);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth);

        RectF oval=new RectF(centerX-radius,centerY-radius,centerX+radius,centerY+radius);
        paint.setStyle(Paint.Style.STROKE);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0,Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
        canvas.drawArc(oval,0,360*progress/max,false,paint);

        paint.setStrokeWidth(0);
        paint.setColor(mProgressColor);
        paint.setTextSize(mProgressTextSize);

        paint.setTypeface(Typeface.DEFAULT_BOLD);
        int percent=(int)(((float)progress/(float)max)*100);
        float textWidth=paint.measureText(percent+"%");

        if(percent>0){
            canvas.drawText(percent+"%",centerX-textWidth/2,(float)(centerY+radius-mDistanceOFbg*6),paint);
        }
    }
    public synchronized int getProcess(){
        return progress;
    }
    public synchronized void setProcess(int process) throws IllegalAccessException {
        if(process<0){
            throw new IllegalAccessException("progress not less than 0");

        }
        if(process>max){
            process=max;
        }
        if(process<=max){
            this.progress=process;
            postInvalidate();
        }
    }
    public synchronized void setMax(int max) throws IllegalAccessException {
        if(max<0){
            throw new IllegalAccessException("max not less than 0");
        }
        this.max=max;
    }



}