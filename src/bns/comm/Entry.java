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
    public String skill;

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
            System.out.println(UnifyEnum.PARAMETER_IS_EMPTY.v());
        }
        String[] array = str.split(UnifyEnum.CONFIG_SPLIT.v(), -1);
        if (array.length != 2) {
            System.out.println(UnifyEnum.ENTRY_INIT_ERROR.v());
        }
        this.key = array[0];
        setFull(array[1]);
    }

    public void setFull(String str) {
        String[] array = str.split(UnifyEnum.COLOR_SPLIT.v(), -1);
        if (array.length != 8) {
            System.out.println(UnifyEnum.ENTRY_INIT_ERROR.v());
        }
        this.x = Integer.parseInt(array[0]);
        this.y = Integer.parseInt(array[1]);
        this.r = Integer.parseInt(array[2]);
        this.g = Integer.parseInt(array[3]);
        this.b = Integer.parseInt(array[4]);
        this.press = Integer.parseInt(array[5]);
        this.release = Integer.parseInt(array[6]);
        this.skill = array[7];
    }

    @Override
    public String toString() {
        return key + UnifyEnum.CONFIG_SPLIT.v()
                + x + UnifyEnum.COLOR_SPLIT.v() + y + UnifyEnum.COLOR_SPLIT.v()
                + r + UnifyEnum.COLOR_SPLIT.v() + g + UnifyEnum.COLOR_SPLIT.v() + b + UnifyEnum.COLOR_SPLIT.v()
                + press + UnifyEnum.COLOR_SPLIT.v() + release + UnifyEnum.COLOR_SPLIT.v() + skill;
    }
}
