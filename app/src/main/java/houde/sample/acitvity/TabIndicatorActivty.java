package houde.sample.acitvity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import houde.sample.R;
import houde.sample.fragment.VpSimpleFragment;
import houde.sample.widget.PagerSlidingTabStrip;
import houde.sample.widget.TabPageIndicator;

public class TabIndicatorActivty extends AppCompatActivity {


    private List<Fragment> mTabContents = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private TabPageIndicator mIndicator;
    private PagerSlidingTabStrip mPsIndicator;

    private List<String> mDatas = Arrays.asList("资讯", "热点", "博客","推荐","再推荐");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_indicator_activty);
        initView();
        initDatas();
        mViewPager.setAdapter(mAdapter);

         //设置Tab上的标题
        mIndicator.setTabItemTitles(mDatas);
        //设置关联的ViewPager
        mIndicator.setViewPager(mViewPager,0);

        mPsIndicator.setViewPager(mViewPager);
    }

    private void initDatas() {
        for (String data : mDatas)
        {
            VpSimpleFragment fragment = VpSimpleFragment.newInstance(data);
            mTabContents.add(fragment);
            TextView tv = new TextView(getApplication());
            tv.setText(data);
            mPsIndicator.addTab(tv);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public int getCount()
            {
                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position)
            {
                return mTabContents.get(position);
            }
        };
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mPsIndicator = (PagerSlidingTabStrip) findViewById(R.id.ps_indicator);
        mIndicator = (TabPageIndicator) findViewById(R.id.indicator);
    }
}
