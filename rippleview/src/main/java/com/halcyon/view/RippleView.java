package com.halcyon.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;

import com.halcyon.R;
import com.halcyon.utils.RippleUtils;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by DeathPluto on 2015/7/2.
 */
public class RippleView extends Button {

    private float mDownX;
    private float mDownY;
    //圆的半径
    private float mRadius = 50.0f;
    private Path mPath = new Path();
    //抗锯齿画笔
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //圆心的颜色
    private int mRippleColor = Color.parseColor("#58faac");
    //水波的透明度
    private float mRippleAlpha = 0.3f;
    private RadialGradient mRadialGradient;
    private final float mScreenDensity;
    private float mMaxRadius;

    public RippleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RippleView);
        mRippleColor = ta.getColor(R.styleable.RippleView_rippleColor, mRippleColor);
        mRippleAlpha = ta.getFloat(R.styleable.RippleView_alpha, mRippleAlpha);

        ta.recycle();
        mScreenDensity = context.getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mRadius <= 0) {
            return;
        }


        //环形渐变渲染
        //圆心颜色、圆边缘颜色
        mRadialGradient = new RadialGradient(mDownX, mDownY, mRadius, RippleUtils.argb(mRippleColor, mRippleAlpha), Color.WHITE, Shader.TileMode.REPEAT);
        mPaint.setShader(mRadialGradient);
        canvas.save();

        //path
        mPath.reset();
        //CW 逆时针划圆 ; CCW 顺时针
        //路径
        mPath.addCircle(mDownX, mDownY, mRadius, Path.Direction.CW);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ValueAnimator valueAnimator;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录下手指按下的坐标-圆心
                mDownX = event.getX();
                mDownY = event.getY();
                //半径 px
                mMaxRadius = RippleUtils.dp2px(getHeight() / 2, mScreenDensity);
                //兼容包的属性动画
                valueAnimator = ValueAnimator.ofFloat(0, mMaxRadius).setDuration(300);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float progress = Float.parseFloat(valueAnimator.getAnimatedValue().toString());
                        //圆的半径=进度
                        RippleView.this.mRadius = progress;
                        //绘制圆
                        invalidate();
                    }
                });
                valueAnimator.start();
                break;
            case MotionEvent.ACTION_MOVE:
                mDownX = event.getX();
                mDownY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                //绘制的圆半径不断变大
                final float disappearRadius = Math.max(RippleUtils.dp2px(getWidth(), mScreenDensity), mRadius);
                valueAnimator = ValueAnimator.ofFloat(mMaxRadius, disappearRadius).setDuration(200);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float progress = Float.parseFloat(valueAnimator.getAnimatedValue().toString());
                        RippleView.this.mRadius = progress;
                        if (progress == disappearRadius) {
                            RippleView.this.mRadius = 0;
                        }
                        invalidate();
                    }
                });
                //加速
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                valueAnimator.start();
                break;
        }
        return super.onTouchEvent(event);
    }
}
