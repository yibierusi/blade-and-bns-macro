package bns.thread;

import bns.comm.UnifyEnum;
import bns.comm.DDKeyEvent;
import bns.comm.Entry;
import bns.comm.SkillEnum;
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
            System.out.println(UnifyEnum.KEY_MAP_IS_EMPTY.v());
        }
        System.out.println(UnifyEnum.SCRIPT_STARTED.v());
        //线程是否存活
        while (state) {
            isBuffExit = Util.isEquals(robot, keys.get(SkillEnum.BUFF.k()));
            //有buff
            if (isBuffExit) {
                //有觉醒雷炎闪 释放SF SR
                isPressKey = Util.existPressKey(robot, keys.get(SkillEnum.SF.k()), DDKeyEvent.F);
                if (isPressKey) {
                    isPressKey = Util.pressKey(robot, keys.get(SkillEnum.SR.k()), DDKeyEvent.R);
                    continue;
                }
                //有雷炎闪  释放F R
                isPressKey = Util.existPressKey(robot, keys.get(SkillEnum.F.k()), DDKeyEvent.F);
                if (isPressKey) {
                    isPressKey = Util.pressKey(robot, keys.get(SkillEnum.R.k()), DDKeyEvent.R);
                    continue;
                }
                //无雷炎闪 释放SR
                isPressKey = Util.existPressKey(robot, keys.get(SkillEnum.SR.k()), DDKeyEvent.R);
                if (isPressKey) {
                    continue;
                }
                //无雷炎闪 释放R
                isPressKey = Util.existPressKey(robot, keys.get(SkillEnum.R.k()), DDKeyEvent.R);
                if (isPressKey) {
                    continue;
                }
            } else {
                //无雷炎闪 释放SR
                isPressKey = Util.existPressKey(robot, keys.get(SkillEnum.SR.k()), DDKeyEvent.R);
                if (isPressKey) {
                    continue;
                }
                isPressKey = Util.existPressKey(robot, keys.get(SkillEnum.R.k()), DDKeyEvent.R);
                if (isPressKey) {
                    continue;
                }
            }
        }
        System.out.println(UnifyEnum.SCRIPT_STOPPED.v());
    }

    public static void kill() {
        state = false;
    }
}
