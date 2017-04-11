package com.zjf.core.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zjf.core.MyApplication;
import com.zjf.core.R;

/**
 * @author :ZJF
 * @version : 2016-12-21 上午 9:10
 */

public class DialogUtil {

    public static void simpleTipDialog(String msg, Context context) {
        new AlertDialog.Builder(context).setMessage(msg).setPositiveButton(MyApplication.getStringRes(R.string.sure), null).show();
    }

    public static void popuDialog(Context context, View view) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.show();
        Window window = dialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
        window.setWindowAnimations(R.style.popuDialogStyle);
    }

}
