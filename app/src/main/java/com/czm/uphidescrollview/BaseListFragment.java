package com.czm.uphidescrollview;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by caizhiming on 2016/11/14.
 */
public abstract class BaseListFragment extends Fragment {

    protected Context mContext;
    abstract View getSlidableView();


}
