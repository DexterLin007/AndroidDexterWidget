package com.dexter.ui.util;

import android.view.View;
import android.view.ViewGroup;
import com.blankj.utilcode.util.ConvertUtils;

/**
 * Created by dexter on 2019/8/13.
 */
public class ViewUtils {

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    public static void setMarginsByDp(View v, int l, int t, int r, int b) {
        setMargins(v, ConvertUtils.dp2px(l), ConvertUtils.dp2px(t), ConvertUtils.dp2px(r), ConvertUtils.dp2px(b));
    }
}
