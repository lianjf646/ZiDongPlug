package com.example.zidongplug;

import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.RequiresApi;

import java.util.List;

/**
 * Created by 奢华 on 2021/5/23.
 */
class GetViewInfoUtil {


    private GetViewInfoUtil() {
    }

    private static class Inner {
        final static GetViewInfoUtil getViewInfoUtil = new GetViewInfoUtil();
    }

    public static GetViewInfoUtil getInstance() {
        return Inner.getViewInfoUtil;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public List<AccessibilityNodeInfo> findTextView(AccessibilityNodeInfo info,String str) {
        List<AccessibilityNodeInfo> infos =
                info.findAccessibilityNodeInfosByViewId("com.kuaishou" +
                        ".nebula:id/video_countdown");
        return infos;
    }

}
