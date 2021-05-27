package com.innodeed.doodleapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class Doodle extends View {

    public static final float TOUCH_TOLERANCE = 10;
    private Bitmap bitmap;
    private Canvas bitmapCanvas;
    private Paint paintScreen;
    private Paint paintLine;
    private HashMap<Integer, Path> pathMap;
    private HashMap<Integer, Point> previousPointMap;
   // private Path path;
   // private Point point;

    public Doodle(Context context) {
        super(context);
        init(null);
    }

    public Doodle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public void init(@Nullable AttributeSet set) {
        paintScreen = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintLine.setAntiAlias(true);
        paintLine.setColor(Color.MAGENTA);
        paintLine.setStrokeWidth(8);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeJoin(Paint.Join.ROUND);
        paintLine.setStrokeCap(Paint.Cap.ROUND);

        pathMap = new HashMap<Integer, Path>();
        previousPointMap = new HashMap<Integer, Point>();

        // path = new Path();
        // point = new Point();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
        bitmap.eraseColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, paintScreen);
        for (Integer key : pathMap.keySet()) {
            canvas.drawPath(pathMap.get(key), paintLine);
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
       /*  float touchX=event.getX();
         float touchY=event.getY();
        int eventType = event.getActionMasked();
        switch (eventType){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(touchX,touchY);
                break;
            case  MotionEvent.ACTION_MOVE:
                path.lineTo(touchX,touchY);
                bitmapCanvas.drawPath(path,paintLine);
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                path.lineTo(touchX,touchY);
                bitmapCanvas.drawPath(path,paintLine);
                path.reset();
                postInvalidate();
                 break;
        } */
        int action = event.getActionMasked();
        int actionIndex = event.getActionIndex();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_UP) {
            touchStarted(event.getX(actionIndex), event.getY(actionIndex), event.getPointerId(actionIndex));

        } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP) {
            touchEnded(event.getPointerId(actionIndex));

        } else {
            touchMoved(event);
        }

        invalidate();
        return true;


    }

    private void touchMoved(MotionEvent event) {

        for (int i=0;i < event.getPointerCount();i++){
            int pointerId= event.getPointerId(i);
            int pointerIndex=event.findPointerIndex(pointerId);

            if (pathMap.containsKey(pointerId)){
                float newX=event.getX(pointerIndex);
                float newY=event.getY(pointerIndex);
                Path path=pathMap.get(pointerId);
                Point point=previousPointMap.get(pointerId);

                float deltaX=Math.abs(newX-point.x);
                float deltaY=Math.abs(newY-point.y);

                if(deltaX >= TOUCH_TOLERANCE  || deltaY>= TOUCH_TOLERANCE){
                    path.quadTo(point.x,point.y,(newX+point.x)/2,(newY+point.y)/2);

                    point.x=(int) newX;
                    point.y=(int) newY;
                }
            }
        }
    }

    private void touchEnded(int pointerId) {
        Path path=pathMap.get(pointerId);
        bitmapCanvas.drawPath(path,paintLine);
        path.reset();

    }
    public void setDrawingColor(int color){
        paintLine.setColor(color);
    }

    public int getDrawingColor(){
       return paintLine.getColor();
    }

    public void setLineWidth(int width){
        paintLine.setStrokeWidth(width);
    }
    public int getLineWidth(){
        return (int) paintLine.getStrokeWidth();
    }

    public void clear(){
        pathMap.clear();
        previousPointMap.clear();
        bitmap.eraseColor(Color.WHITE);
        invalidate();
    }

    private void touchStarted(float x, float y, int pointerId) {
        Path path;
        Point point;
        if (pathMap.containsKey(pointerId)){
            path=pathMap.get(pointerId);
            point=previousPointMap.get(pointerId);
        }else {
            path=new Path();
            pathMap.put(pointerId,path);
            point=new Point();
            previousPointMap.put(pointerId,point);
        }
        path.moveTo(x, y);
        point.x=(int) x;
        point.y=(int) y;
    }

        public void saveToInternalStorage(){
        ContextWrapper cw=new ContextWrapper(getContext());
        String filename="Doodle"+ System.currentTimeMillis();
        File directory=cw.getDir("imageDir",Context.MODE_PRIVATE);
        File myPath=new File(directory,filename+".jpg");
        FileOutputStream fos=null;
        try {
            fos=new FileOutputStream(myPath);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fos.flush();
                fos.close();
                Log.d("Image",directory.getAbsolutePath());
                Toast msg=Toast.makeText(getContext(),"Image saved",Toast.LENGTH_LONG);
                msg.setGravity(Gravity.CENTER,msg.getXOffset()/2,msg.getYOffset()/2);
                msg.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void loadImageFromStorage(String path){
        try {
            File f =new File(path,"profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

}
