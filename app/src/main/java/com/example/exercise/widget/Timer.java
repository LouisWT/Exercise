package com.example.exercise.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.example.exercise.R;

public class Timer extends AppCompatButton {
    private CircleDrawable bgDrawable;
    private float radius;
    private int bgColor;
    Paint paint;

    public Timer(Context context) {
        super(context);
    }

    public Timer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Timer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Timer, defStyleAttr, 0);

        this.radius = a.getDimension(R.styleable.Timer_radius, 0);
        this.bgColor = a.getColor(R.styleable.Timer_bg_color, 0);
//        makeBackgroundDrawable();
        paint = new Paint();
        a.recycle();
    }

    public void makeBackgroundDrawable() {
        bgDrawable = new CircleDrawable(this.bgColor, this.radius);

        StateListDrawable bgDrawableList = new StateListDrawable();
        bgDrawableList.addState(new int[]{android.R.attr.state_enabled, -android.R.attr.state_pressed}, bgDrawable);
        bgDrawableList.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed}, bgDrawable);
        bgDrawableList.addState(new int[]{-android.R.attr.state_enabled}, bgDrawable);
        setBackgroundDrawable(bgDrawableList);
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        int x = getWidth();
//        int y = getHeight();
//        int radius;
//        radius = 100;
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(getResources().getColor(R.color.black));
//        canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors
//        paint.setColor(getResources().getColor(R.color.black));
//        canvas.drawCircle((float) x / 2, (float) y / 2, radius, paint);
//    }

//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//        if (bgDrawable != null) {
//            bgDrawable.setRect(right - left, bottom - top);
//        }
//    }
}
