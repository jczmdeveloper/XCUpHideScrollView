package com.czm.main;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.czm.model.TabItem;
import com.czm.tablayout.XCTabLayout;
import com.czm.uphidescrollview.ObservableScrollView;
import com.czm.uphidescrollview.ScrollViewPager;
import com.czm.uphidescrollview.ScrollViewPagerAdapter;
import com.czm.uphidescrollview.XCUpHideScrollView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private View mRootView;
    private View mHeaderView;
    private ScrollViewPager mScrollViewPager;
    private ScrollViewPagerAdapter mScrollViewPagerAdapter;
    private XCTabLayout mTabLayout;
    private XCUpHideScrollView mScrollView;
    private int mCurPos = 0;
    private View mScrollBackView;


    private boolean mIsCeiling = false;//是否吸顶

    private String[] mTabArr = new String[]{
      "热点","推荐","财经","娱乐","NBA"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRootView = findViewById(R.id.sv_root);
        mScrollView = (XCUpHideScrollView) mRootView.findViewById(R.id.sv_root);
        mScrollBackView = mRootView.findViewById(R.id.btn_back);
        mHeaderView = mRootView.findViewById(R.id.lay_header);
        mScrollView.setHeaderView(mHeaderView);
        mScrollView.setContentView(mRootView.findViewById(R.id.lay_content));

        mTabLayout = (XCTabLayout) mRootView.findViewById(R.id.tab_layout_vp);

        mScrollViewPager = (ScrollViewPager) mRootView.findViewById(R.id.vp_list);


        setListener();
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<TabItem> list = new ArrayList<TabItem>();
                for(int i = 0; i < mTabArr.length;i ++){
                    TabItem item = new TabItem();
                    item.setItemName(mTabArr[i]);
                    list.add(item);
                }
                setTabView(list);
            }
        });
    }
    private void setListener() {
        mScrollView.setOnScrollListener(new ObservableScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldX, int oldY) {
                if(mHeaderView.getMeasuredHeight() >0
                        && y >= mHeaderView.getMeasuredHeight()){
                    if(!mIsCeiling){
                        mScrollBackView.setVisibility(View.VISIBLE);
                        mIsCeiling = true;
                    }

                }else{
                    if(mIsCeiling){
                        mScrollBackView.setVisibility(View.GONE);
                        mIsCeiling = false;
                    }
                }
            }
        });
        mScrollBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScrollBackView.setVisibility(View.GONE);
                mIsCeiling = false;
                mScrollView.smoothScrollToSlow(0,0,800);
                ((RecyclerView) mScrollViewPagerAdapter.getSlidableView(mCurPos)).scrollToPosition(0);
            }
        });

        mScrollViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurPos = position;
                mScrollView.setContentInnerScrollableView(mScrollViewPagerAdapter.getSlidableView(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void setTabView(List<TabItem> data) {
        if(mScrollViewPagerAdapter == null){
            mScrollViewPagerAdapter = new ScrollViewPagerAdapter(this.getSupportFragmentManager());
            mScrollViewPagerAdapter.setTabLayoutData(data);
            mScrollViewPager.setAdapter(mScrollViewPagerAdapter);
            mTabLayout.setViewPager(mScrollViewPager);
            mScrollViewPagerAdapter.notifyDataSetChanged();
        }

        mScrollViewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScrollView.setContentInnerScrollableView(mScrollViewPagerAdapter.getSlidableView(0));
                mScrollViewPager.setHeight(mScrollView.getMeasuredHeight()-mTabLayout.getMeasuredHeight());
            }
        },100);
    }
}
