package houde.sample.acitvity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;

import houde.sample.R;
import houde.sample.fragment.TabListFragment;
import houde.sample.widget.TabPageIndicator;

public class StickyNavActivity extends AppCompatActivity {
    private String[] mTitles = new String[]{"简介", "评价", "相关"};
    private TabPageIndicator mIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private TabListFragment[] mFragments = new TabListFragment[mTitles.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_nav);
        initViews();
        initDatas();
        initEvents();
    }

    private void initEvents() {


    }

    private void initDatas() {
        mIndicator.setTabItemTitles(Arrays.asList(mTitles));

        for (int i = 0; i < mTitles.length; i++) {
            mFragments[i] = (TabListFragment) TabListFragment.newInstance(mTitles[i]);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);

        mIndicator.setViewPager(mViewPager, 0);
        mIndicator.setTabItemTitles(Arrays.asList(mTitles));
    }

    private void initViews() {
        mIndicator = (TabPageIndicator) findViewById(R.id.id_stickynavlayout_indicator);
        mViewPager = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);

		/*
        RelativeLayout ll = (RelativeLayout) findViewById(R.id.id_stickynavlayout_topview);
		TextView tv = new TextView(this);
		tv.setText("我的动态添加的");
		tv.setBackgroundColor(0x77ff0000);
		ll.addView(tv, new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, 600));
		*/
    }
}
