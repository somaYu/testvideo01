package com.sike.testvideo01.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;

/**
 * @author wodemingziyouyidianchang
 * @version 1.0.0
 * @since 2018/9/10 23:10
 */

// 强行再抄一遍
public class WindowUtil {

    private static final int FLAGS =
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;

    // 先从wm开始
    public static WindowManager getMyWm(Context context) {
        return (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    // 是否存在nb
    public static boolean hasNb(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {

            Display display = getMyWm(context).getDefaultDisplay();

            Point point1 = new Point();
            Point point2 = new Point();

            display.getSize(point1);
            display.getRealSize(point2);

            // 这是啥意思
            return point2.x != point1.x
                    || point2.y != point1.y;

        } else {

            boolean menu = ViewConfiguration.get(context).hasPermanentMenuKey();
            boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);

            return !(menu || back);

        }

    }

    // 获取状态栏高度
    public static double getSbHeight(Context context) {

        int h = 0;

        Resources resources = context.getResources();

        int id = resources.getIdentifier("status_bar_height", "dimen", "android");

        if (id > 0) {

            // 根据资源id获取相应的尺寸值
            h = context.getResources().getDimensionPixelSize(id);

        }

        return h;
    }

    // 获取NavigationbBar的高度
    public static int getNbHeight(Context context) {

        if (!hasNb(context)) {
            return 0;
        }

        Resources resources = context.getResources();
        int id = resources.getIdentifier(
                "navigation_bar_height"
                , "dimen"
                , "android"
        );

        return resources.getDimensionPixelOffset(id);

    }

    /**
     * 获取屏幕宽度
     */
    public static int getMyScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     */
    public static int getMyScreenHeight(Context context, boolean isIncludeNav) {
        if (isIncludeNav) {
            return context.getResources().getDisplayMetrics().heightPixels + getNbHeight(context);
        } else {
            return context.getResources().getDisplayMetrics().heightPixels;
        }
    }

    public static AppCompatActivity getAppCompActivity(Context context) {
        if (context == null) return null;
        if (context instanceof AppCompatActivity) {
            return (AppCompatActivity) context;
        } else if (context instanceof ContextThemeWrapper) {
            return getAppCompActivity(((ContextThemeWrapper) context).getBaseContext());
        }
        return null;
    }

    public static Activity scanForActivity(Context context) {
        return context == null ? null : (context instanceof Activity ? (Activity) context : (context instanceof ContextWrapper ? scanForActivity(((ContextWrapper) context).getBaseContext()) : null));
    }

    private static void hideNavigationBar(Context context) {
        View decorView = scanForActivity(context).getWindow().getDecorView();
        decorView.setSystemUiVisibility(FLAGS);
    }

    private static void showNavigationBar(Context context) {
        View decorView = scanForActivity(context).getWindow().getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        systemUiVisibility &= ~FLAGS;
        decorView.setSystemUiVisibility(systemUiVisibility);
    }

    /**
     * 隐藏ActionBar，StatusBar，NavigationBar
     */
    @SuppressLint("RestrictedApi")
    public static void hideSystemBar(Context context) {
        AppCompatActivity appCompatActivity = getAppCompActivity(context);
        if (appCompatActivity != null) {
            ActionBar ab = appCompatActivity.getSupportActionBar();
            if (ab != null && ab.isShowing()) {
                ab.setShowHideAnimationEnabled(false);
                ab.hide();
            }
        }
        hideNavigationBar(context);
    }

    /**
     * 显示ActionBar，StatusBar，NavigationBar
     */
    @SuppressLint("RestrictedApi")
    public static void showSystemBar(final Context context) {
        showNavigationBar(context);
        AppCompatActivity appCompatActivity = getAppCompActivity(context);
        if (appCompatActivity != null) {
            ActionBar ab = appCompatActivity.getSupportActionBar();
            if (ab != null && !ab.isShowing()) {
                ab.setShowHideAnimationEnabled(false);
                ab.show();
            }
        }
    }

    // 屏幕边缘检测
    public static boolean isEdge(Context context, MotionEvent event) {

        int size = dp2px(context, 50);

        // 奇葩的判断逻辑
        return event.getRawX() < size
                || event.getRawX() > getMyScreenWidth(context) - size
                || event.getRawY() < size
                || event.getRawY() > getMyScreenHeight(context, true) - size;

    }

    public static int dp2px(Context context, float value) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP
                , value
                , context.getResources().getDisplayMetrics()

        );
    }

    public static int sp2px(Context context, float value) {

        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP
                , value
                , context.getResources().getDisplayMetrics()
        );

    }


}
