package bns.comm;

/**
 * @Auther: zhouhy
 * @Description: UnifyEnum
 * @Date: Create in 17:00 2018/8/20
 * @Modified By
 */
public enum UnifyEnum {
    //分分隔符
    CONFIG_SPLIT(1, ": "),
    COLOR_SPLIT(2, ","),
    //文件路径
    CONFIG_PATH(11, "config"),
    BNS(12, "/bns/view/bns.fxml"),
    SKILL(13, "/bns/view/skill.fxml"),
    JAVA(14, "/res/img/java.png"),
    DDXOFT64(15,"ddxoft64"),
    //界面显示
    START_SCRIPT(21, "1.启动脚本(F11)"),
    STOP_SCRIPT(22, "1.停止脚本(F11)"),
    //后台提示
    INITING(31,"正在初始化..."),
    READING_CONFIG(32,"读取配置中..."),
    SCRIPT_STARTUP(33,"脚本启动中..."),
    SCRIPT_STOPUP(34,"脚本停止中..."),
    SCRIPT_STARTED(35,"脚本已启动"),
    SCRIPT_STOPPED(36,"脚本已停止"),
    ADD_HOT_KEY_LISTENER(37,"注册热键监听中..."),
    RECREATE_CONFIG(38,"重新创建配置文件:"),
    DATA_SAVE_FINISH(39,"数据保存完成"),
    FILLING_DATA(39,"正在填充数据..."),
    SAVING_KEY_INFO(39,"保存按键信息中..."),


    //错误信息
    PARAMETER_IS_EMPTY(41,"参数不能为空"),
    ENTRY_INIT_ERROR(42,"实体类初始化出错"),
    KEY_MAP_IS_EMPTY(43,"keyMap 为空"),
    NOT_FOUND_CONFIG(44,"未找到配置文件:"),
    RECREATE_CONFIG_FAILD(45,"重新创建配置文件失败"),
    KEY_FORMAT_ERROR(46," 指针数据格式有问题，未保存"),
    COLOR_FORMAT_ERROR(47,"颜色格式不正确"),
    ROBOT_INIT_ERROR(47,"初始化Robot出错"),


    ERROR(99,"未知错误");




    UnifyEnum(int k, String v) {
        this.k = k;
        this.v = v;
    }

    private int k;

    private String v;

    public int k() {
        return k;
    }

    public String v() {
        return v;
    }
}
