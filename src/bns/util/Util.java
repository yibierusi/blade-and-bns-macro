package bns.util;

import bns.comm.*;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.awt.Robot;
import java.io.*;
import java.util.*;

/**
 * @Auther: zhouhy
 * @Description:
 * @Date: Create in 9:48 2018/8/21
 * @Modified By
 */
public class Util {

    /**
     * 判断字符串是否是纯数字
     */
    public static boolean isFullNumber(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("[0-9]+");
    }

    /**
     * 判断是否为空
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 判断某点颜色是否相同
     */
    public static boolean isEquals(Robot robot, Entry entry) {
        java.awt.Color color;
        try {
            color = robot.getPixelColor(entry.x, entry.y);
        } catch (Exception e) {
            return false;
        }
        if (color.getRed() == entry.r && color.getGreen() == entry.g && color.getBlue() == entry.b) {
            return true;
        }
        return false;
    }

    /**
     * 判断某点颜色是否相同 相同则按下
     *
     * @return
     */
    public static boolean existPressKey(Robot robot, Entry entry, int ddCode) {
        if (isEquals(robot, entry)) {
            return pressKey(robot, entry, ddCode);
        }
        return false;
    }

    /**
     * 直接按下某键
     */
    public static boolean pressKey(Robot robot, Entry entry, int ddCode) {
        //robot.keyPress(keyCode);
        DdXoft.INSTANCE.DD_key(ddCode, 1);
        robot.delay(entry.press);
        //robot.keyRelease(keyCode);
        DdXoft.INSTANCE.DD_key(ddCode, 2);
        robot.delay(entry.release);
        System.out.println("{" + entry.skill + "}" +
                SkillEnum.PRESS.v() + ":" + entry.press + SkillEnum.MS.v() +
                SkillEnum.RELEASE.v() + ":" + entry.release + SkillEnum.MS.v());
        return true;
    }

    /**
     * 双C
     */
    public static boolean doubleC(Robot robot, Entry entry, int ddCode) {
        boolean isPressKey = Util.existPressKey(robot, entry, DDKeyEvent.C);
        Util.existPressKey(robot, entry, DDKeyEvent.F);
        Util.existPressKey(robot, entry, DDKeyEvent.C);
        return isPressKey;
    }

    /**
     * Color对象 转rgb字符串
     */
    public static String getRgbStrByColor(java.awt.Color color) {
        if (color == null) {
            return "";
        }
        return color.getRed() + UnifyEnum.COLOR_SPLIT.v() + color.getGreen() + UnifyEnum.COLOR_SPLIT.v() + color.getBlue();
    }


    /**
     * RGB字符串转Color对象
     */
    public static Color getColorByRgbStr(String rgb) {
        if (rgb == null || "".equals(rgb))
            return null;
        String array[] = rgb.split(UnifyEnum.COLOR_SPLIT.v(), -1);
        if (array.length == 3) {
            int red = Integer.parseInt(array[0]);
            int green = Integer.parseInt(array[1]);
            int blue = Integer.parseInt(array[2]);
            return Color.rgb(red, green, blue);
        }
        return null;
    }

    /**
     * 读取配置文件
     */
    public static Map<String, Entry> loadingConfigFiles() {
        Map<String, Entry> keys = new HashMap<>();
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(new FileInputStream(new File(UnifyEnum.CONFIG_PATH.v())));
            String line = "";
            br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                Entry entry = new Entry(line);
                keys.put(entry.key, entry);
            }
        } catch (Exception e) {
            System.out.println(UnifyEnum.NOT_FOUND_CONFIG.v() + UnifyEnum.CONFIG_PATH.v());
            createConfingFile();
        } finally {
            try {
                if (isr != null) {
                    isr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return keys;
    }


    /**
     * 重新创建配置文件
     */
    public static void createConfingFile() {
        File file = new File(UnifyEnum.CONFIG_PATH.v());
        try {
            file.createNewFile();
            String path = file.getPath();
            System.out.println(UnifyEnum.RECREATE_CONFIG.v() + path);
        } catch (Exception ex) {
            System.out.println(UnifyEnum.RECREATE_CONFIG_FAILD.v());
        }
    }

    /**
     * 保存数据
     */
    public static void savaKeyMap(Map<String, Entry> keys) {
        PrintStream ps = null;        // 声明打印流对象
        try {
            ps = new PrintStream(new FileOutputStream(new File(UnifyEnum.CONFIG_PATH.v())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, Entry> entry : keys.entrySet()) {
            if (entry != null) {
                ps.println(entry.getValue().toString());
            }
        }
        ps.close();
        System.out.println(UnifyEnum.DATA_SAVE_FINISH.v());

    }

    public static Entry getEntry(SkillEnum skill, TextField x, TextField y, TextField c) {
        Entry entry = new Entry();
        try {
            entry.x = Integer.parseInt(x.getText());
            entry.y = Integer.parseInt(y.getText());
            String array[] = c.getText().split(UnifyEnum.COLOR_SPLIT.v(), -1);
            entry.r = Integer.parseInt(array[0]);
            entry.g = Integer.parseInt(array[1]);
            entry.b = Integer.parseInt(array[2]);
            entry.key = skill.k();
            entry.skill = skill.v();
        } catch (Exception e) {
            System.out.println(skill.v() + UnifyEnum.KEY_FORMAT_ERROR.v());
            return null;
        }
        return entry;
    }

    /**
     * 移除字符串第一个字符
     */
    public static String removeFirstChar(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        return str.substring(0, str.length());
    }

    /**
     * 移除map中空的value对象
     *
     * @param paramMap
     * @return
     */
    public static Map<String, Entry> removeMapEmptyValue(Map<String, Entry> paramMap) {
        Set<String> set = paramMap.keySet();
        Iterator<String> it = set.iterator();
        List<String> listKey = new ArrayList<String>();
        while (it.hasNext()) {
            String str = it.next();
            if (paramMap.get(str) == null || "".equals(paramMap.get(str))) {
                listKey.add(str);
            }
        }
        for (String key : listKey) {
            paramMap.remove(key);
        }
        return paramMap;
    }
}
