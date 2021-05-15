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

import java.util.Random;

public class Kuaishou_AccessibilityService extends AccessibilityService {
    public static final String TAG = "--MyService--";

    private GestureDescription gestureDescription;

    private boolean b = true;

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initService() {
        GestureDescription.Builder builder = new GestureDescription.Builder();
        Path path = new Path();
        path.moveTo(540, 1600);
        path.lineTo(540, 0);
        gestureDescription = builder.addStroke(new GestureDescription.StrokeDescription(path, 20, 200)).build();
    }

    public Kuaishou_AccessibilityService() {
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
//        Log.e(TAG, "onAccessibilityEvent: ", );
        String packageName = event.getPackageName().toString();
        String className = event.getClassName().toString();
        Log.d(TAG, "----1-----" + packageName);
        Log.d(TAG, "----2-----" + className);
            //被监听的界面
            AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
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
            sleep(2000, 6000);
//        }else {
//            Log.e(TAG, "onAccessibilityEvent: ????!!" );
//        }
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

    private void sleep(int sleepTime, int sleepRandomTime) {
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
}