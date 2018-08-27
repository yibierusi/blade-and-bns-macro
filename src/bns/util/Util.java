package bns.util;

import bns.comm.Constant;
import bns.comm.DDKeyEvent;
import bns.comm.Entry;
import bns.comm.DdXoft;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.awt.Robot;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

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
    public static boolean pressKey(Robot robot, Entry entry, int ddCode) {
        if (isEquals(robot, entry)) {
            //robot.keyPress(keyCode);
            DdXoft.INSTANCE.DD_key(ddCode, 1);
            robot.delay(entry.press);
            //robot.keyRelease(keyCode);
            DdXoft.INSTANCE.DD_key(ddCode, 2);
            robot.delay(entry.release);
            return true;
        }
        return false;
    }

    /**
     *  双C
     */
    public static boolean doubleC(Robot robot, Entry entry, int ddCode) {
        boolean isPressKey = Util.pressKey(robot, entry, DDKeyEvent.C);
        Util.pressKey(robot, entry, DDKeyEvent.F);
        Util.pressKey(robot, entry, DDKeyEvent.C);
        return isPressKey;
    }

    /**
     * Color对象 转rgb字符串
     */
    public static String getRgbStrByColor(java.awt.Color color) {
        if (color == null) {
            return "";
        }
        return color.getRed() + Constant.COLOR_SPLIT.v() + color.getGreen() + Constant.COLOR_SPLIT.v() + color.getBlue();
    }


    /**
     * RGB字符串转Color对象
     */
    public static Color getColorByRgbStr(String rgb) {
        if (rgb == null || "".equals(rgb))
            return null;
        String array[] = rgb.split(Constant.COLOR_SPLIT.v(), -1);
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
            isr = new InputStreamReader(new FileInputStream(new File(Constant.CONFIG_PATH.v())));
            String line = "";
            br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                Entry entry = new Entry(line);
                keys.put(entry.key, entry);
            }
        } catch (Exception e) {
            System.out.println("未找到配置文件:" + Constant.CONFIG_PATH.v());
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
        File file = new File("config");
        try {
            file.createNewFile();
            String path = file.getPath();
            System.out.println("重新创建配置文件:" + path);
        } catch (Exception ex) {
            System.out.println("重新创建配置文件失败");
        }
    }

    /**
     * 保存数据
     */
    public static void savaKeyMap(Map<String, Entry> keys) {
        PrintStream ps = null;        // 声明打印流对象
        try {
            ps = new PrintStream(new FileOutputStream(new File(Constant.CONFIG_PATH.v())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, Entry> entry : keys.entrySet()) {
            ps.println(entry.getValue().toString());
        }
        ps.close();
        System.out.println("数据保存完成");

    }

    public static Entry getEntry(String key, TextField x, TextField y, TextField c){
        Entry entry = new Entry();
        entry.x = Integer.parseInt(x.getText());
        entry.y = Integer.parseInt(y.getText());
        String array[] = c.getText().split(Constant.COLOR_SPLIT.v(),-1);
        entry.r = Integer.parseInt(array[0]);
        entry.g = Integer.parseInt(array[1]);
        entry.b = Integer.parseInt(array[2]);
        entry.key = key;
        return entry;
    }
}
