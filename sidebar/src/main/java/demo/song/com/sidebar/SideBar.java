package demo.song.com.sidebar;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SideBar extends View {
    private String initials[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private Paint mPaint;
    private int initialHeight;
    private SideBarItemSelectedListener listener;
    public SideBar(Context context) {
        this(context, null);
    }

    public SideBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView(){
        mPaint=new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height=getHeight();
        initialHeight=height/initials.length;
        for(int i=0;i<initials.length;i++){
            mPaint.setColor(Color.BLUE);
            mPaint.setAntiAlias(true);
            mPaint.setTextSize(30);
            mPaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(initials[i],100,(initialHeight*i),mPaint);
            mPaint.reset();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
                float point = event.getY();
                int index = (int) (point / initialHeight);
                if(listener!=null){
                    listener.itemSelected(initials[index]);
                }
                return true;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }
    public void setItemSelectedListener(SideBarItemSelectedListener listener){
        this.listener=listener;
    }
    public interface SideBarItemSelectedListener{
        void itemSelected(String str);
    }
}
