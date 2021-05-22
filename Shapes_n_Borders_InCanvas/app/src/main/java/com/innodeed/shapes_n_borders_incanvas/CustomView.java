package com.innodeed.shapes_n_borders_incanvas;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {
    private static final int SQUARE_SIZE_DEFAULT = 200;
    private Rect rect;
    private Paint paint;
    private Paint mPaintCircle;
    private int SquareColor;
    private int SquareSize;
    private float mCircleX, mCircleY;
    private float mCircleRadius = 100f;

    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircle.setColor(Color.parseColor("#EA80FC"));
        // paint.setColor(Color.GREEN);
        rect = new Rect();
        // now parsing and obtaining those attribute values from attribute set in xml layout file
        if (set == null) {
            return;// to avoid null pointer exception
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.CustomView);
        SquareColor = typedArray.getColor(R.styleable.CustomView_square_color, Color.GREEN);
        SquareSize = typedArray.getDimensionPixelSize(R.styleable.CustomView_square_size, SQUARE_SIZE_DEFAULT);
        /*recycle is called so that we could avoid messing up with garbage
        collector and is called  after obtaining value from attribute sets*/
        paint.setColor(SquareColor);
        typedArray.recycle();


    }

    public void swapColor() {

        paint.setColor(paint.getColor() == SquareColor ? (Color.BLUE) : (SquareColor));
        postInvalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rect.left = 50;
        rect.top = 50;
        rect.right = rect.left + SquareSize;
        rect.bottom = rect.top + SquareSize;
        canvas.drawRect(rect, paint);
        // canvas.drawRect(200,200,300,300,paint);
        // float cx,cy;
        // float radius=120f;
        //cx=getWidth()-radius-80f;
        //cy= rect.top +(rect.height()/2);
        if (mCircleX == 0f || mCircleY == 0f) {
            mCircleX = getWidth() / 2;
            mCircleY = getHeight() / 2;
        }
        canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaintCircle);
    }

    //  for making draggable circle
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                float x = event.getX();
                float y = event.getY();

                if (rect.left < x && rect.right > x)
                    if (rect.top < y && rect.bottom > y) {

                        mCircleRadius += 10f;
                        postInvalidate();

                    }


                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                // detecting touch event on circle(position of touch in coordinate system)
                float x = event.getX();
                float y = event.getY();
                // detecting the touch is in the circle
                double dx = Math.pow(x - mCircleX, 2);
                double dy = Math.pow(y - mCircleY, 2);

                if (dx + dy < Math.pow(mCircleRadius, 2)) {
                    // touched
                    mCircleY = y;
                    mCircleX = x;

                    postInvalidate();
                }
                return true;
            }
        }
        return value;
    }
}
