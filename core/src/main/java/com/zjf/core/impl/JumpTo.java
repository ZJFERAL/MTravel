package com.zjf.core.impl;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author :ZJF
 * @version : 2016-12-20 上午 11:03
 */

public interface JumpTo {
    void jumpTo(Activity activity, Class<? extends Activity> aClazz, boolean isfinish);

    void jumpTo(Activity activity, Class<? extends Activity> aClazz, Bundle bundle, boolean isfinish);

    void jumpTo(final Activity activity, final Class<? extends Activity> aClazz, int delay, boolean isfinish);

    void jumpTo(final Activity activity, final Class<? extends Activity> aClazz, int delay, final Bundle bundle, boolean isfinish);

}
