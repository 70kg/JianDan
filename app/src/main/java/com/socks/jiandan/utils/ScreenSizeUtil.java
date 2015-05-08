package com.socks.jiandan.utils;

import android.app.Activity;
import android.content.Context;

/**
 * Created by zhaokaiqiang on 15/4/9.
 */
public class ScreenSizeUtil {

	public static int getScreenWidth(Context context) {
		return ((Activity)context).getWindowManager().getDefaultDisplay().getWidth();
	}

	public static int getScreenHeight(Context context) {
		return ((Activity)context).getWindowManager().getDefaultDisplay().getHeight();
	}

}
