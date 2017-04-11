package com.zjf.core.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author :ZJF
 * @version : 2016-12-20 上午 11:05
 */

public class JumpInto implements JumpTo {
    public void jumpTo(final Activity activity, final Class<? extends Activity> aClazz, int delay, final boolean isfinish) {
        if (delay == 0) {
            jumpTo(activity, aClazz, isfinish);
        } else {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    jumpTo(activity, aClazz, isfinish);
                }
            }, delay);
        }
    }

    public void jumpTo(final Activity activity, final Class<? extends Activity> aClazz, int delay, final Bundle bundle, final boolean isfinish) {
        if (delay == 0) {
            jumpTo(activity, aClazz, bundle, isfinish);
        } else {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    jumpTo(activity, aClazz, bundle, isfinish);
                }
            }, delay);
        }
    }


    public void jumpTo(Activity activity, Class<? extends Activity> aClazz, Bundle bundle, boolean isfinish) {
        Intent intent = new Intent(activity, aClazz);
        intent.putExtra("data", bundle);
        activity.startActivity(intent);
        if (isfinish) {
            activity.finish();
        }
    }

    public void jumpTo(Activity activity, Class<? extends Activity> aClazz, boolean isfinish) {
        activity.startActivity(new Intent(activity, aClazz));
        if (isfinish) {
            activity.finish();
        }
    }
}
