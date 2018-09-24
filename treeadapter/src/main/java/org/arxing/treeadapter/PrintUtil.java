package org.arxing.treeadapter;

import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SuppressWarnings({"MalformedFormatString", "unused", "SpellCheckingInspection", "WeakerAccess"})
public class PrintUtil {
    private static final String trace = "TRACE";
    private static final String tag = "PRINTER";

    //帶tag與內容
    public static void pi(String tag, String format, Object... objs) {
        defaultPrint(Log.INFO, tag, format, objs);
    }

    public static void pd(String tag, String format, Object... objs) {
        defaultPrint(Log.DEBUG, tag, format, objs);
    }

    public static void pw(String tag, String format, Object... objs) {
        defaultPrint(Log.WARN, tag, format, objs);
    }

    public static void pv(String tag, String format, Object... objs) {
        defaultPrint(Log.VERBOSE, tag, format, objs);
    }

    public static void pe(String tag, String format, Object... objs) {
        defaultPrint(Log.ERROR, tag, format, objs);
    }

    //測試訊息

    public static void testi(String format, Object... objs) {
        testPrint(Log.INFO, format, objs);
    }

    public static void testd(String format, Object... objs) {
        testPrint(Log.DEBUG, format, objs);
    }

    public static void testw(String format, Object... objs) {
        testPrint(Log.WARN, format, objs);
    }

    public static void testv(String format, Object... objs) {
        testPrint(Log.VERBOSE, format, objs);
    }

    public static void teste(String format, Object... objs) {
        testPrint(Log.ERROR, format, objs);
    }

    //追蹤
    public static void pi() {
        defaultPrint(Log.INFO, trace, "");
    }

    public static void pd() {
        defaultPrint(Log.DEBUG, trace, "");
    }

    public static void pw() {
        defaultPrint(Log.WARN, trace, "");
    }

    public static void pv() {
        defaultPrint(Log.VERBOSE, trace, "");
    }

    public static void pe() {
        defaultPrint(Log.ERROR, trace, "");
    }

    public static <T> void pArray(String tag, String msg, T[] array) {
        pList(tag, msg, Arrays.asList(array));
    }

    public static void pList(String tag, String msg, List list) {
        defaultPrint(Log.VERBOSE, tag, msg);
        for (Object o : list)
            defaultPrint(Log.VERBOSE, tag, "" + o);
    }

    public static void pSet(String tag, String msg, Set list) {
        defaultPrint(Log.VERBOSE, tag, msg);
        for (Object o : list)
            defaultPrint(Log.VERBOSE, tag, "" + o);
    }

    private static void testPrint(int level, String format, Object... objs) {
        defaultPrint(level, "TEST", format, objs);
    }

    private static void defaultPrint(int level, String tag, String format, Object... objs) {
        String content = String.format("[%s] : %s", tag, String.format(format, objs));
        String realTag = getCurrentClass(3) + ":" + getCurrentMethod(3);
        switch (level) {
            case Log.VERBOSE:
                Log.v(realTag, content);
                break;
            case Log.DEBUG:
                Log.d(realTag, content);
                break;
            case Log.WARN:
                Log.w(realTag, content);
                break;
            case Log.ERROR:
                Log.e(realTag, content);
                break;
            case Log.INFO:
                Log.i(realTag, content);
                break;
        }
    }

    private static String getCurrentClass(int depth) {
        StackTraceElement[] stacks = new Exception().getStackTrace();
        StackTraceElement stack = stacks[depth];
        String[] splits = stack.getClassName().split("\\.");
        String fullClassName = splits[splits.length - 1];
        return fullClassName.split("\\$")[0];
    }

    private static String getCurrentMethod(int depth) {
        StackTraceElement[] stacks = new Exception().getStackTrace();
        StackTraceElement stack = stacks[depth];
        return stack.getMethodName();
    }
}
