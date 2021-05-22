package com.innodeed.geometrical_shapes_oncanavas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class customview extends View {
    private Rect rect;
    private Paint mRectRectangle;
    private Paint mRectSquare;
    private Paint mCircle;
   // private Paint mTriangle;
    private static final int Square_Size=300;

    public customview(Context context) {
        super(context);
        init(null);
    }

    public customview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public customview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        rect=new Rect();

        mCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircle.setColor(Color.BLUE);
        mRectRectangle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectRectangle.setColor(Color.GREEN);
        mRectSquare = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectSquare.setColor(Color.RED);
       // mTriangle = new Paint(Paint.ANTI_ALIAS_FLAG);
       // mTriangle.setColor(Color.YELLOW);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(250f, 250f, 200f, mCircle);
        rect.left = 200;
        rect.top = 600;
        rect.right = rect.left + Square_Size;
        rect.bottom = rect.top + Square_Size;
        canvas.drawRect(rect, mRectSquare);
        canvas.drawRect(600,600,1000,1500,mRectRectangle);

    }
}
