package com.hanet.newpipe.utils;

import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.BatteryManager;
import android.os.Build;

import androidx.annotation.NonNull;

import static android.content.Context.BATTERY_SERVICE;
import static android.content.Context.UI_MODE_SERVICE;

public class DeviceUtils {
    private static final String AMAZON_FEATURE_FIRE_TV = "amazon.hardware.fire_tv";
    private static Boolean isTV = null;

    public static boolean isTv(final Context context) {
        if (isTV != null) {
            return isTV;
        }

        final PackageManager pm = context.getPackageManager();

        boolean isTv = ((UiModeManager) context.getSystemService(UI_MODE_SERVICE))
                .getCurrentModeType() == Configuration.UI_MODE_TYPE_TELEVISION
                || pm.hasSystemFeature(AMAZON_FEATURE_FIRE_TV)
                || pm.hasSystemFeature(PackageManager.FEATURE_TELEVISION);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final boolean isBatteryAbsent
                    = ((BatteryManager) context.getSystemService(BATTERY_SERVICE))
                    .getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY) == 0;
            isTv = isTv || (isBatteryAbsent
                    && !pm.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN)
                    && pm.hasSystemFeature(PackageManager.FEATURE_USB_HOST)
                    && pm.hasSystemFeature(PackageManager.FEATURE_ETHERNET));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            isTv = isTv || pm.hasSystemFeature(PackageManager.FEATURE_LEANBACK);
        }

        DeviceUtils.isTV = isTv;
        return DeviceUtils.isTV;
    }

    public static boolean isTablet(@NonNull final Context context) {
        return (context
                .getResources()
                .getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
