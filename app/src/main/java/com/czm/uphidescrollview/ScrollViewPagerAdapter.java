package com.czm.uphidescrollview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.czm.model.TabItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caizhiming on 2016/11/14.
 */
public class ScrollViewPagerAdapter extends FragmentPagerAdapter {
    private List<BaseListFragment> mFragmentList = new ArrayList<>();
    private List<TabItem> mTitleList = new ArrayList<>();
    public BaseListFragment getFragment(int pos){
        return mFragmentList.get(pos);
    }
    public void setTabLayoutData(List<TabItem> list){
        if(list != null && list.size() > 0){
            mTitleList.clear();
            mTitleList.addAll(list);

            mFragmentList.clear();
            for (int i = 0; i < mTitleList.size(); i++) {
                ListFragment item = new ListFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("tab_item",mTitleList.get(i));
                item.setArguments(bundle);
                mFragmentList.add(item);
            }
        }
    }
    public ScrollViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    public View getSlidableView (int index) {
        return mFragmentList.get(index).getSlidableView();
    }

    @Override
    public Fragment getItem(int position) {
        if(mFragmentList!= null && mFragmentList.size() > 0){
            return mFragmentList.get(position);
        }else{
            return null;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return mTitleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position).getItemName();
    }
}
