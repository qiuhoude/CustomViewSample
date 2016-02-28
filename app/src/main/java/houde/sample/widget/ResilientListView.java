package houde.sample.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Scroller;

/**
 * Created by Administrator on 2016/1/30.
 */
public class ResilientListView extends ListView {

    public static final String TAG = "TAG";
    private ImageView imageView;
    private Scroller mScroller;


    public ResilientListView(Context context) {
        this(context, null);
    }

    public ResilientListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ResilientListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context, new OvershootInterpolator());
    }

    private int mOriginalHeight;//原始显示的高度
    private int drawableHeight;//图像的高度

    public void setParallaxImage(ImageView mImage) {
        this.imageView = mImage;
        mOriginalHeight = imageView.getHeight();
        drawableHeight = imageView.getDrawable().getIntrinsicHeight();

    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY,
                                   int scrollX, int scrollY,
                                   int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY,
                                   boolean isTouchEvent) {
        // deltaY : 竖直方向的瞬时偏移量 / 变化量 dx   顶部到头下拉为-, 底部到头上拉为+
        // scrollY : 竖直方向的偏移量 / 变化量
        // scrollRangeY : 竖直方向滑动的范围
        // maxOverScrollY : 竖直方向最大滑动范围
        // isTouchEvent : 是否是手指触摸滑动, true为手指, false为惯性

        Log.d(TAG, "deltaY: " + deltaY + " scrollY: " + scrollY + " scrollRangeY: " + scrollRangeY
                + " maxOverScrollY: " + maxOverScrollY + " isTouchEvent: " + isTouchEvent);

        //是手指触摸滑动,并且是向下拉
        if (isTouchEvent && deltaY < 0) {
            // 高度不超出图片最大高度时,才让其生效
            if (imageView.getHeight() < drawableHeight) {

                int newHeight = (int) (imageView.getHeight() + (Math.abs(deltaY) / 2.0f));

                imageView.getLayoutParams().height = newHeight;
                imageView.requestLayout();
            }
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                //手指放开时回弹
                int startHeight = imageView.getHeight();
                int endHeight = mOriginalHeight;

                //1. 使用属性动画进行完成
//                valueAnimator(startHeight, endHeight);

                //2.  自定义Animation
                RestAnimation animation = new RestAnimation(imageView, startHeight, endHeight);
                animation.setDuration(500);
                animation.setInterpolator(new OvershootInterpolator());
                startAnimation(animation);
                break;
        }

        return super.onTouchEvent(ev);
    }

    ValueAnimator animator = null;

    private void valueAnimator(final int startHeight, final int endHeight) {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
        }
        animator = ValueAnimator.ofInt(1);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                // percent 0.0 -> 1.0
                int newHeight = evaluate(fraction, startHeight, endHeight);

                imageView.getLayoutParams().height = newHeight;
                imageView.requestLayout();
            }
        });

        animator.setInterpolator(new OvershootInterpolator());
        animator.setDuration(500);
        animator.start();
    }

    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int startInt = startValue;
        return (int) (startInt + fraction * (endValue - startInt));
    }


    class RestAnimation extends Animation {

        private ImageView imageView;
        private int startHeight;
        private int endHeight;

        public RestAnimation(ImageView imageView, int startHeight, int endHeight) {
            super();
            this.startHeight = startHeight;
            this.imageView = imageView;
            this.endHeight = endHeight;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            Integer newHeight = evaluate(interpolatedTime, startHeight, endHeight);

            imageView.getLayoutParams().height = newHeight;
            imageView.requestLayout();


            super.applyTransformation(interpolatedTime, t);
        }
    }

}
