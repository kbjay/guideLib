package com.example.kjguidelib;

import android.view.View;


/**
 * *
 * @author kb_jay
 * created at 2018/4/18 下午4:24
 */
public abstract class KJAvoidDoubleClickListener implements View.OnClickListener {

   private long mPreClickTime;
   private static final int INTER_TIME = 1000;

   @Override
   public void onClick(View v) {
       long currentTimeMillis = System.currentTimeMillis();
       if (currentTimeMillis - mPreClickTime > INTER_TIME) {
           mPreClickTime = currentTimeMillis;
           onAvoidDoubleClick();
       }
   }

   public abstract void onAvoidDoubleClick();
}
