package com.zjf.core.utils;

import android.util.Log;

/**
 * Log统一管理类
 * 
 * @author way
 * 
 */
public class LogUtils
{

	private LogUtils()
	{
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	public static boolean isDebug = false;// 是否需要打印bug，可以在application的onCreate函数里面初始化
	private static String TAG = "LogUtils";

	// 下面四个是默认tag的函数
	public static void i(String msg)
	{
		i(TAG,msg);
	}

	public static void d(String msg)
	{
		d(TAG,msg);
	}

	public static void e(String msg)
	{
		e(TAG,msg);
	}

	public static void v(String msg)
	{
		v(TAG,msg);
	}

	// 下面是传入自定义tag的函数
	public static void i(String tag, String msg)
	{
		if (isDebug)
			Log.i(tag+"---->", "////////////////////////////////\n//    "+msg
					+"\n////////////////////////////////");
	}

	public static void d(String tag, String msg)
	{
		if (isDebug)
			Log.d(tag+"---->", "////////////////////////////////\n//    "+msg
					+"\n////////////////////////////////");
	}

	public static void e(String tag, String msg)
	{
		if (isDebug)
			Log.e(tag+"---->", "////////////////////////////////\n//    "+msg
					+"\n////////////////////////////////");
	}

	public static void v(String tag, String msg)
	{
		if (isDebug)
			Log.v(tag+"---->", "////////////////////////////////\n//    "+msg
					+"\n////////////////////////////////");
	}
}