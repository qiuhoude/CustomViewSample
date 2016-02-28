package houde.sample.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import houde.sample.utils.ScreemUtils;

/**
 * Created by Administrator on 2016/1/30.
 */
public class DrawViewGroup extends ViewGroup {

    public static final String TAG = "qiu";
    private Scroller mScroller;

    public DrawViewGroup(Context context) {
        this(context, null);
    }

    public DrawViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScreemHeight = ScreemUtils.getScreemHeight(context);
        mScroller = new Scroller(context);
    }

     private int mScreemHeight;

//    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
        }
//        mScreemHeight = MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG, "mScreemHeight " + mScreemHeight);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(TAG, String.format("l %d , t %d , r %d , b %d", l, t, r, b));
        int childCount = getChildCount();
        //设置viewgrop高度
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mlp.height = mScreemHeight * childCount;

        setLayoutParams(mlp);
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                child.layout(l, i * mScreemHeight, r, (i + 1) * mScreemHeight);
            }
        }
    }

    private int mLastY;
    private int mStart;
    private int mEnd;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                mStart = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                //动画没有完成就取消
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int dy = mLastY - y;
                if (getScaleY() < 0) {
                    dy = 0;
                }
                if (getScrollY() > getHeight() - mScreemHeight) {
                    dy = 0;
                }
                scrollBy(0, dy);
                mLastY = y;

                break;
            case MotionEvent.ACTION_UP:
                mEnd = getScrollY();
                int dScrollY = mEnd - mStart;
                if (dScrollY > 0) {
                    //向下滑动
                    if (dScrollY < mScreemHeight / 3) {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);

                    } else {
                        mScroller.startScroll(0, getScrollY(), 0, mScreemHeight - dScrollY);
                    }
                } else {
                    if (-dScrollY < mScreemHeight / 3) {
                        mScroller.startScroll(0, getScrollY(), 0, dScrollY);

                    } else {
                        mScroller.startScroll(0, getScrollY(), 0, mScreemHeight - dScrollY);
                    }
                }
                break;
        }
        postInvalidate();
        return true;
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }
}
