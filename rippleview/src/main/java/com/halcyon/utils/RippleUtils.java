package com.halcyon.utils;

import android.graphics.Color;

/**
 * Created by DeathPluto on 2015/7/4.
 */
public class RippleUtils {

    public static int dp2px(int dp, float density) {
        return (int) (dp * density + 0.5f);
    }

    /**
     * 颜色转换（掺杂透明度）
     *
     * @param color
     * @param a
     * @return
     */
    public static int argb(int color, float a) {
        int alpha = Math.round((a * 255));
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        return Color.argb(alpha, red, green, blue);
    }
}
