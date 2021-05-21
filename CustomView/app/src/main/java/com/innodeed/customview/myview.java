package com.innodeed.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class myview extends View {
    public myview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        @SuppressLint("DrawAllocation") Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        canvas.drawLine(100,100,200,200,paint);
        canvas.drawColor(Color.GREEN);
    }
}
