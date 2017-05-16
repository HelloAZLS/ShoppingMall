package ysg.gdcp.cn.customerview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/5/15 22:10.
 *
 * @author ysg
 */

public class MyView extends View {

    private Paint mPaint;

    public MyView(Context context) {
        super(context);
        initPaint();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
       canvas.drawCircle(60,60,60,mPaint);
    }

    private void initPaint() {
        mPaint = new Paint();
        //设置坑锯齿
        mPaint.setAntiAlias(true);
        //设置画笔宽度
        mPaint.setStrokeWidth(5);
        //设置画笔颜色
        mPaint.setColor(Color.BLUE);
        //设置画笔样式
        mPaint.setStyle(Paint.Style.STROKE);

    }

}
