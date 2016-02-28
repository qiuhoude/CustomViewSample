package com.itheima.tencentqq;

import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.WindowManager;

import com.itheima.tencentqq.reminder.GooView;

/**
 * Created by Administrator on 2016/1/31.
 */
public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager windowManager = getWindowManager();
        int height = windowManager.getDefaultDisplay().getHeight();
        int width = windowManager.getDefaultDisplay().getWidth();

        //获取状态栏高度

        GooView gooView = new GooView(this);

        setContentView(gooView);
        gooView.setStatusBarHeight(50);
        gooView.setNumber(10);
        gooView.initCenter(width / 2, height / 2);

        gooView.setOnDisappearListener(new GooView.OnDisappearListener() {
            @Override
            public void onDisappear(PointF mDragCenter) {

            }

            @Override
            public void onReset(boolean isOutOfRange) {

            }
        });
    }
}
