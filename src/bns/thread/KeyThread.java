package bns.thread;

import bns.comm.DDKeyEvent;
import bns.comm.Entry;
import bns.util.Util;

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
            //有雷炎闪
            isPressKey = Util.pressKey(robot, keys.get("sf"), DDKeyEvent.F);
            if (isPressKey) {
                System.out.println("释放觉醒雷炎闪");
                isPressKey = Util.pressKey(robot, keys.get("r"), DDKeyEvent.R);
                continue;
            }
            isPressKey = Util.pressKey(robot, keys.get("f"), DDKeyEvent.F);
            if (isPressKey) {
                System.out.println("释放雷炎闪");
                isPressKey = Util.pressKey(robot, keys.get("r"), DDKeyEvent.R);
                continue;
            }
            isBuffExit = Util.isEquals(robot, keys.get("buff"));
            if (isBuffExit) {
                //有buff 无雷炎闪
                System.out.println("雷电buff存在");
                isPressKey = Util.pressKey(robot, keys.get("r"), DDKeyEvent.R);
                if (isPressKey) {
                    System.out.println("释放拔剑");
                    continue;
                }
                isPressKey = Util.pressKey(robot, keys.get("sr"), DDKeyEvent.R);
                if (isPressKey) {
                    System.out.println("释放觉醒拔剑");
                    continue;
                }
            } else {
                //无buff 无雷炎闪
                isPressKey = Util.doubleC(robot, keys.get("c"), DDKeyEvent.C);
                if (isPressKey) {
                    System.out.println("释放天隙流光");
                    continue;
                }
                isPressKey = Util.pressKey(robot, keys.get("v"), DDKeyEvent.V);
                if (isPressKey) {
                    System.out.println("释放残月斩");
                    continue;
                }
                isPressKey = Util.pressKey(robot, keys.get("x"), DDKeyEvent.X);
                if (isPressKey) {
                    System.out.println("释放雷鸣斩");
                    continue;
                }
            }
            //无buff 无雷炎闪 无姿态切换
            Util.pressKey(robot, keys.get("r"), DDKeyEvent.R);
        }
        System.out.println("脚本已停止。");
    }

    public static void kill() {
        state = false;
    }
}
