package com.sike.testvideo01.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.dueeeke.videoplayer.util.WindowUtil;
import com.sike.testvideo01.R;

/**
 * @author wodemingziyouyidianchang
 * @version 1.0.0
 * @since 2018/9/10 23:04
 */

public class FloatView extends FrameLayout {

    Context mContext;
    Paint mPaint = new Paint();
    private WindowManager wm;
    private WindowManager.LayoutParams params;
    private int floatX;
    private int floatY;
    private boolean firstTouch = true;

    public FloatView(Context context) {
        this(context, null);
    }

    public FloatView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatView(@NonNull Context context, int x, int y) {
        super(context);
        floatX = x;
        floatY = y;
        init();
    }

    public FloatView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initView(context, attrs, defStyle);
    }


    private void init() {
        setBackgroundResource(R.drawable.shape_float_window_background);
        int padding = WindowUtil.dp2px(getContext(), 1);
        setPadding(padding, padding, padding, padding);
        initWindow();
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {

        mContext = context;
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setStrokeWidth(3.0f);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
