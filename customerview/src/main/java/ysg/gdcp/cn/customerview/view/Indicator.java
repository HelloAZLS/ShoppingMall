package ysg.gdcp.cn.customerview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/5/15 22:33.
 *
 * @author ysg
 */

public class Indicator extends View {

    private Paint mForePaint;//前景色画笔
    private Paint  mBGPaint;//背景色画笔
    public Indicator(Context context) {
        super(context);
        initPaint();
    }

    public Indicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mForePaint = new Paint();
        mForePaint.setStrokeWidth(2);
        mForePaint.setAntiAlias(true);
        mForePaint.setStyle(Paint.Style.FILL);
        mForePaint.setColor(Color.BLUE);

        mBGPaint = new Paint();
        mBGPaint.setStrokeWidth(2);
        mBGPaint.setAntiAlias(true);
        mBGPaint.setStyle(Paint.Style.STROKE);
        mBGPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < 5; i++) {
            canvas.drawCircle(60+i*60*3,60,20,mBGPaint);
        }
        canvas.drawCircle(60,60,20,mForePaint);
    }
}
