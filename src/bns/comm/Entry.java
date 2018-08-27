package bns.comm;

import bns.util.Util;

/**
 * @Auther: zhouhy
 * @Description:
 * @Date: Create in 14:25 2018/8/22
 * @Modified By
 */
public class Entry {
    public int x;
    public int y;
    public int r;
    public int g;
    public int b;
    public int press;
    public int release;
    public String key;

    public Entry() {
        press = 50;
        release = 100;
    }

    public Entry(int x, int y, int r, int g, int b, int press, int release, String key) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.g = g;
        this.b = b;
        this.press = press;
        this.release = release;
        this.key = key;
    }

    public Entry(String str) {
        str = str.trim();
        if (Util.isEmpty(str)) {
            System.out.println("实体类参数不能为空");
        }
        String[] array = str.split(Constant.CONFIG_SPLIT.v(), -1);
        if (array.length != 2) {
            System.out.println("实体类初始化出错");
        }
        this.key = array[0];
        setFull(array[1]);
    }

    public void setFull(String str) {
        String[] array = str.split(Constant.COLOR_SPLIT.v(), -1);
        if (array.length != 7) {
            System.out.println("实体类初始化出错");
        }
        this.x = Integer.parseInt(array[0]);
        this.y = Integer.parseInt(array[1]);
        this.r = Integer.parseInt(array[2]);
        this.g = Integer.parseInt(array[3]);
        this.b = Integer.parseInt(array[4]);
        this.press = Integer.parseInt(array[5]);
        this.release = Integer.parseInt(array[6]);
    }

    @Override
    public String toString() {
        return key + Constant.CONFIG_SPLIT.v()
                + x + Constant.COLOR_SPLIT.v() + y + Constant.COLOR_SPLIT.v()
                + r + Constant.COLOR_SPLIT.v() + g + Constant.COLOR_SPLIT.v() + b + Constant.COLOR_SPLIT.v()
                + press + Constant.COLOR_SPLIT.v() + release;
    }
}
