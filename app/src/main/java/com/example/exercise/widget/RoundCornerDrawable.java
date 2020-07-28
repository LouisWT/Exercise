package com.example.exercise.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class RoundCornerDrawable extends Drawable {
    final int color;
    final float radius;
    final Paint paint;
    final RectF rectF;

    public RoundCornerDrawable(int color, float radius) {
        this.color = color;
        this.radius = radius;

        // 实心的画笔
        this.paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(color);

        this.rectF = new RectF();
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int i) {
        paint.setAlpha(i);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public void setRect(int width, int height) {
        this.rectF.left = 0;
        this.rectF.top = 0;
        this.rectF.right = width;
        this.rectF.bottom = height;
    }

    public void draw(@NonNull Canvas canvas) {
        canvas.drawRoundRect(rectF, radius, radius, paint);
    }
}