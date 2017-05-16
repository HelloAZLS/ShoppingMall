package ysg.gdcp.cn.shoppingmall.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import ysg.gdcp.cn.shoppingmall.R;

/**
 * Created by Administrator on 2017/5/15 22:33.
 *
 * @author ysg
 */

public class Indicator extends View {

    private Paint mForePaint;//前景色画笔
    private Paint mBGPaint;//背景色画笔
    private float offest;//viewPager的偏移百分比
    private int mNun =0;//Indicator的个数
    private  int raduis =2;//Indicator 的半径
    private int mBgColor = Color.RED;//Indicator 的背景色
    private  int mForeColor =Color.GRAY;//Indicator 的前景色

    public Indicator(Context context) {
        super(context);
        initPaint();
    }

    public Indicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Indicator);
        mNun   = typedArray.getInteger(R.styleable.Indicator_indicator_num, mNun);
        raduis = typedArray.getInteger(R.styleable.Indicator_indicator_raduis, raduis);
        Log.i("raduis",raduis+"");
    }

    public void setViewPro(int position, float offest) {

        this.offest = position * 3 * raduis + offest * 3 * raduis;
        invalidate();
    }

    private void initPaint() {
        mForePaint = new Paint();
        mForePaint.setStrokeWidth(2);
        mForePaint.setAntiAlias(true);
        mForePaint.setStyle(Paint.Style.FILL);
        mForePaint.setColor(mBgColor);

        mBGPaint = new Paint();
        mBGPaint.setStrokeWidth(2);
        mBGPaint.setAntiAlias(true);
        mBGPaint.setStyle(Paint.Style.STROKE);
        mBGPaint.setColor(mForeColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int endPoint = 0;
        for (int i = 0; i <mNun; i++) {
            endPoint = 10 + i * raduis * 3;
            canvas.drawCircle(10 + i * raduis * 3, 10, raduis, mBGPaint);

        }
        float rand = 10 + offest;
        if (rand > endPoint) {
            rand = endPoint;
        }
        canvas.drawCircle(rand, 10, raduis, mForePaint);
    }
}
