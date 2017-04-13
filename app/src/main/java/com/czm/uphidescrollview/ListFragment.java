package com.czm.uphidescrollview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.czm.main.R;
import com.czm.main.RecyclerViewListAdapter;
import com.czm.model.TabItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caizhiming on 2016/11/14.
 */
public class ListFragment extends BaseListFragment {
    protected RecyclerView mRecyclerView;
    private TabItem mTabItem;
    private View mRootView;
    private RecyclerViewListAdapter mListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mTabItem = (TabItem) getArguments().getSerializable("tab_item");
        mRootView =  inflater.inflate(R.layout.fragment_viewpager_list,null);

        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = view.getContext();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mListAdapter = new RecyclerViewListAdapter(getContext());
        mRecyclerView.setAdapter(mListAdapter);

        //fix  bug: recyclerView & scrollView
        //see http://stackoverflow.com/questions/38074949/recyclerview-inside-scrollview-some-items-are-not-shown
        mRecyclerView.setNestedScrollingEnabled(false);

        List<String> data = new ArrayList<>();
        int i = 0;
        while (i < 30){
            data.add(new String(mTabItem.getItemName()+": Item list "+(i+1)));
            i++;
        }
        mListAdapter.setData(data);
    }

    private int mLoginMode = -1;
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    View getSlidableView() {
        return mRecyclerView;
    }


    @Override
    public Context getContext(){
        return mContext;
    }
}
