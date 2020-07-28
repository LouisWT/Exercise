package com.example.exercise.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.exercise.R;

public class TextImageButton extends LinearLayout {
    private OnClickListener mListener;

    public TextImageButton(Context context) {
        this(context, null);
    }

    public TextImageButton(Context context, AttributeSet attrs) {

        this(context, attrs, 0);
    }


    public TextImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 对button的布局进行动态加载
        LayoutInflater.from(context).inflate(R.layout.text_image_button, this);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Main", "press btn");
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                if (x + getLeft() < getRight() && y + getTop() < getBottom()) {
                    mListener.onClick(this);
                }
                break;
        }
        return true;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        mListener = l;
    }
}
