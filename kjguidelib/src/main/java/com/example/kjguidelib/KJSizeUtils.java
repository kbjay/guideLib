package com.example.kjguidelib;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by kb_jay on 2018/4/18.
 */

public class KJSizeUtils {
    public static int dp2px(Context context,float dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }
    public static int sp2px(Context context,float sp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,context.getResources().getDisplayMetrics());
    }
}
