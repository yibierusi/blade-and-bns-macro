package bns.comm;

/**
 * @Auther: zhouhy
 * @Description: SkillEnum
 * @Date: Create in 17:00 2018/8/20
 * @Modified By
 */
public enum SkillEnum {
    BUFF(SkillConstant.BUFF,"雷电BUFF"),
    SF(SkillConstant.SF,"觉醒雷炎闪"),
    SR(SkillConstant.SR,"觉醒拔剑"),
    F(SkillConstant.F,"雷炎闪"),
    R(SkillConstant.R,"拔剑"),
    Z(SkillConstant.Z,"鬼剑令"),
    X(SkillConstant.X,"雷鸣斩"),
    C(SkillConstant.C,"天隙流光"),
    V(SkillConstant.V,"残月"),

    PRESS("press","按下"),
    RELEASE("release","释放"),
    MS("Millisecond","ms");


    SkillEnum(String k, String v) {
        this.k = k;
        this.v = v;
    }

    private String k;

    private String v;

    public String k() {
        return k;
    }

    public String v() {
        return v;
    }
}
