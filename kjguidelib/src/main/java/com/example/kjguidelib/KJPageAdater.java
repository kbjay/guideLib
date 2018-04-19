package com.example.kjguidelib;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by kb_jay on 2018/4/18.
 */

public class KJPageAdater extends PagerAdapter {

    private  KJBindDataListener mBindDataListener;
    private  int mPageCount;

    public KJPageAdater(int pageCount, KJBindDataListener listener) {
        mPageCount = pageCount;
        mBindDataListener = listener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mBindDataListener != null) {
            return mBindDataListener.bindViewAndData(container, position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return mPageCount;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public interface KJBindDataListener {
        View bindViewAndData(ViewGroup container, int t);
    }
}
