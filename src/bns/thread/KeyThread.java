package bns.thread;

import bns.comm.DDKeyEvent;
import bns.comm.Entry;
import bns.util.Util;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.Map;

/**
 * @Auther: zhouhy
 * @Description:
 * @Date: Create in 9:35 2018/8/21
 * @Modified By
 */
public class KeyThread extends Thread {
    private Robot robot;
    private Map<String, Entry> keys;
    private static boolean state;

    private boolean isPressKey; //是否按下某键
    private boolean isBuffExit; //闪电buff是否存在

    public KeyThread() {
        init();
        this.keys = Util.loadingConfigFiles();
    }

    public void init() {
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        this.state = true;
    }

    @Override
    public void run() {
        if (keys == null || keys.size() == 0) {
            System.out.println("keyMap 为空");
        }
        System.out.println("脚本已启动。");
        //线程是否存活
        while (state) {
            isBuffExit = Util.isEquals(robot, keys.get("buff"));
            //有buff
            if (isBuffExit) {
                //有觉醒雷炎闪 释放F R
                isPressKey = Util.existPressKey(robot, keys.get("sf"), DDKeyEvent.F);
                if (isPressKey) {
                    System.out.println("释放觉醒雷炎闪:" + isPressKey);
                    isPressKey = Util.pressKey(robot, keys.get("sr"), DDKeyEvent.R);
                    System.out.println("释放觉醒拔剑:unknown");
                    continue;
                }
                //有雷炎闪  释放F R
                isPressKey = Util.existPressKey(robot, keys.get("f"), DDKeyEvent.F);
                if (isPressKey) {
                    System.out.println("释放雷炎闪:" + isPressKey);
                    isPressKey = Util.pressKey(robot, keys.get("r"), DDKeyEvent.R);
                    System.out.println("释放拔剑:unknown");
                    continue;
                }
                //无雷炎闪 释放R
                isPressKey = Util.existPressKey(robot, keys.get("sr"), DDKeyEvent.R);
                if (isPressKey) {
                    System.out.println("释放觉醒拔剑:" + isPressKey);
                    continue;
                }
                //无雷炎闪 释放R
                isPressKey = Util.existPressKey(robot, keys.get("r"), DDKeyEvent.R);
                if (isPressKey) {
                    System.out.println("释放拔剑:" + isPressKey);
                    continue;
                }
            } else {
                //无雷炎闪 释放R
                isPressKey = Util.existPressKey(robot, keys.get("sr"), DDKeyEvent.R);
                if (isPressKey) {
                    System.out.println("释放觉醒拔剑:" + isPressKey);
                    continue;
                }
                isPressKey = Util.existPressKey(robot, keys.get("r"), DDKeyEvent.R);
                if (isPressKey) {
                    System.out.println("释放拔剑:" + isPressKey);
                    continue;
                }
//                //无buff
//                //有觉醒雷炎闪 释放F
//                isPressKey = Util.existPressKey(robot, keys.get("sf"), DDKeyEvent.F);
//                if (isPressKey) {
//                    System.out.println("释放觉醒雷炎闪");
//                }else{
//                    //有雷炎闪  释放F
//                    isPressKey = Util.existPressKey(robot, keys.get("f"), DDKeyEvent.F);
//                    if (isPressKey) {
//                        System.out.println("释放雷炎闪");
//                    }
//                }
//                isPressKey = Util.existPressKey(robot, keys.get("c"), DDKeyEvent.C);
//                if (isPressKey) {
//                    System.out.println("释放天隙流光");
//                    continue;
//                }
//                isPressKey = Util.existPressKey(robot, keys.get("v"), DDKeyEvent.V);
//                if (isPressKey) {
//                    System.out.println("释放残月斩");
//                    continue;
//                }
//                isPressKey = Util.existPressKey(robot, keys.get("x"), DDKeyEvent.X);
//                if (isPressKey) {
//                    System.out.println("释放雷鸣斩");
//                    continue;
//                }
            }
        }
        System.out.println("脚本已停止。");
    }

    public static void kill() {
        state = false;
    }
}
