package com.DoodleApp.doodle_app_2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawingView extends View {

    private Path drawPath;
    private Paint drawPaint;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint canvasPaint;


    public DrawingView(Context context) {
        super(context);
        setupDrawing(null);
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupDrawing(attrs);
    }



    public void setupDrawing(@Nullable AttributeSet set) {
        drawPath = new Path();

        drawPaint = new Paint();
        drawPaint.setColor(Color.MAGENTA);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5f);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint=new Paint(Paint.DITHER_FLAG);
        canvasPaint.setColor(Color.WHITE);

    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        bitmap.eraseColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(drawPath, drawPaint);
        canvas.drawBitmap(bitmap,0,0,canvasPaint);

    }

    public void clearImage(){
      setDrawingCacheEnabled(false);
      onSizeChanged(getWidth(),getHeight(),getWidth(),getHeight());
      invalidate();
      setDrawingCacheEnabled(true);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                canvas.drawPath(drawPath,drawPaint);
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                canvas.drawPath(drawPath,drawPaint);
                drawPath.lineTo(touchX,touchY);
                drawPath.reset();
                break;
            default:
                return false;
        }
        postInvalidate();
        return true;
    }



}
