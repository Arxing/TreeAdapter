package org.arxing.treeadapter;

import android.os.Handler;
import android.os.Looper;

public class ThreadUtil {

    public static boolean isInMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * Run a runnable on the Main (UI) Thread.
     *
     * @param runnable the runnable
     */
    public static void runOnUiThread(final Runnable runnable) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(runnable);
        } else {
            runnable.run();
        }
    }

    public static Thread runOnNewThread(Runnable runnable) {
        Thread t = new Thread(runnable);
        t.start();
        return t;
    }

    public static void post(Runnable runnable) {
        new Handler().post(runnable);
    }

    public static void postDelay(Runnable runnable, long delay) {
        new Handler().postDelayed(runnable, delay);
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
