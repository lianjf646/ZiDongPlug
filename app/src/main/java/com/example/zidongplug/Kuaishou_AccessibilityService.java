package com.example.zidongplug;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.Random;

public class Kuaishou_AccessibilityService extends AccessibilityService {
    public static final String TAG = "--MyService--";

    private GestureDescription gestureDescription;

    private boolean b = true;

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initService() {
        GestureDescription.Builder builder = new GestureDescription.Builder();
        Path path = new Path();
        path.moveTo(540, 1900);
        path.lineTo(540, 200);
        gestureDescription = builder.addStroke(new GestureDescription.StrokeDescription(path, 100,
                500)).build();
    }

    public Kuaishou_AccessibilityService() {
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
//        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
//        Log.e(TAG, "onAccessibilityEvent: "+rootInActiveWindow.getPackageName() );
        if (event == null) return;
        if (event.getSource() == null) return;
        if (event.getSource().getClassName() == null) return;
        int eventType = event.getEventType();
        String packageName = event.getPackageName().toString();
        String className = event.getClassName().toString();
        Log.d(TAG, "----packageName-----:" + packageName);
        Log.d(TAG, "----className-----:" + className);
        Log.d(TAG, "---eventType----: " + eventType);


        //被监听的应用
        if (packageName.equals("com.kuaishou.nebula")) {
            kuaishunebula(event);


        }
        if (packageName.equals("com.tencent.weishi")) {
            weishiHuaDong(getRootInActiveWindow());
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void kuaishunebula(AccessibilityEvent event) {

        msleep(10 * 1000, 0);
        Click(958, 500);
//        kuaiShouJisuGuanggao();
        kuaiShouJisuGuanggao1(10);
        kuaiShouZhubo();

//        msleep(2 * 1000, 0);
//        Click(59, 149);

//        for (; ; ) {
//            tongYongHuaDong();
//
//        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void Click(int x, int y) {
        GestureDescription.Builder builder = new GestureDescription.Builder();
        Path path = new Path();
        path.moveTo(x, y);
        GestureDescription gestureDescription = builder
//                .addStroke(new GestureDescription.StrokeDescription(path, 100, 50))
                .addStroke(new GestureDescription.StrokeDescription(path, 100, 50)).build();

        dispatchGesture(gestureDescription, new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
                Log.d(TAG, "----模拟单机成功-----");
                Toast.makeText(Kuaishou_AccessibilityService.this, "模拟单机成功", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                super.onCancelled(gestureDescription);
                Log.d(TAG, "----模拟单机失败-----");
                Toast.makeText(Kuaishou_AccessibilityService.this, "模拟单机失败", Toast.LENGTH_SHORT).show();
            }
        }, null);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void tongYongHuaDong() {
        dispatchGesture(gestureDescription, new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
                Log.d(TAG, "----模拟手势成功-----");
            }

            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                super.onCancelled(gestureDescription);
                Log.d(TAG, "----模拟手势失败-----");
                Toast.makeText(Kuaishou_AccessibilityService.this, "模拟手势失败", Toast.LENGTH_SHORT).show();
            }
        }, null);

        msleep(5000, 3000);
//        Click(540,540);
//
//        sleep(200, 0);
//        Click(540,540);
//        sleep(3000, 0);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void weishiHuaDong(AccessibilityNodeInfo info) {
        List<AccessibilityNodeInfo> infos =
                info.findAccessibilityNodeInfosByViewId("com.tencent" + ".weishi:id/qrb");
        if (infos == null) return;
        if (infos.isEmpty()) return;
        AccessibilityNodeInfo accessibilityNodeInfo = infos.get(2);
//        accessibilityNodeInfo.
//        sleep(2000, 5000);

    }

    @Override
    public void onInterrupt() {
        Log.d(TAG, "----onInterrupt-----");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.d(TAG, "----onServiceConnected-----");
        initService();
    }

    private void msleep(int sleepTime, int sleepRandomTime) {
        try {
            int randomTime = 0;
            if (sleepRandomTime > 0) {
                randomTime = new Random().nextInt(sleepRandomTime);
            }
            long t = sleepTime + randomTime;
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 观看广告 2000悬赏任务
     * @param num
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void kuaiShouJisuGuanggao1(int num) {
        for (int i = 0; i < 6; i++) {//1100金币悬赏
            msleep(5 * 1000, 0);
            Click(511, 2000);
            msleep(20 * 1000, 0);
            while (true) {// com.kuaishou.nebula:id/video_close_icon   com.kuaishou
                // .nebula:id/video_close_icon com.kuaishou.nebula:id/video_textureview
                msleep(3 * 1000, 0);
                List<AccessibilityNodeInfo> infos =
                        getRootInActiveWindow().findAccessibilityNodeInfosByText("领取");
                if (infos != null) {
                    if (infos.isEmpty()) {
                        Log.e(TAG, "kuaishunebula: null");
                        msleep(3000, 0);
                        Click(49, 130);
                        break;
                    }
                }
            }
        }
    }

    /**
     * 快手主播任务
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void kuaiShouZhubo(){
        for (int i = 0; i < 3; i++) {// 观看主播
            msleep(5000, 0);
            Click(80, 2000);
//            msleep(2000,0);
//            Click(411,190); // 添加关注
            msleep(100 * 1000, 0);
            Click(1022, 150);

//            sleep(2 * 1000, 0);
//
//            Click(540, 1488);

        }
    }
}