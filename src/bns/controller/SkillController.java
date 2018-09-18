package bns.controller;

import bns.comm.SkillConstant;
import bns.comm.UnifyEnum;
import bns.comm.Entry;
import bns.comm.SkillEnum;
import bns.util.Util;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SkillController implements Initializable {
    private Robot robot;

    private Point point;

    private String current;

    @FXML
    private TextField fx;
    @FXML
    private TextField fy;
    @FXML
    private TextField fc;
    @FXML
    private Label fs;

    @FXML
    private TextField rx;
    @FXML
    private TextField ry;
    @FXML
    private TextField rc;
    @FXML
    private Label rs;

    @FXML
    private TextField sfx;
    @FXML
    private TextField sfy;
    @FXML
    private TextField sfc;
    @FXML
    private Label sfs;

    @FXML
    private TextField srx;
    @FXML
    private TextField sry;
    @FXML
    private TextField src;
    @FXML
    private Label srs;

    @FXML
    private TextField xx;
    @FXML
    private TextField xy;
    @FXML
    private TextField xc;
    @FXML
    private Label xs;

    @FXML
    private TextField buffx;
    @FXML
    private TextField buffy;
    @FXML
    private TextField buffc;
    @FXML
    private Label buffs;

    @FXML
    private TextField cx;
    @FXML
    private TextField cy;
    @FXML
    private TextField cc;
    @FXML
    private Label cs;

    @FXML
    private TextField vx;
    @FXML
    private TextField vy;
    @FXML
    private TextField vc;
    @FXML
    private Label vs;

    private Map<String, Entry> keys;

    public Button saveButton;
    public Button saveAndReturnButton;


    /**
     * 判断是否按下
     *
     * @param event
     */
    @FXML
    public void isCtrlDown(KeyEvent event) {
        if (event.isControlDown()) {
            try {
                point = MouseInfo.getPointerInfo().getLocation();
                java.awt.Color color = robot.getPixelColor(point.x, point.y);
                if (current != null && !"".equals(current)) {
                    System.out.println(UnifyEnum.SAVING_KEY_INFO.v());
                    keyDataHandle(point.x, point.y, color.getRed(), color.getGreen(), color.getBlue());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 填充保存某个按键数据
     */
    public void keyDataHandle(int x, int y, int red, int green, int blue) {
        if (red > 255 || green > 255 || blue > 255) {
            System.out.println(UnifyEnum.COLOR_FORMAT_ERROR.v());
            return;
        }

        Entry entry = new Entry(x, y, red, green, blue, 50, 100, current);
        keys.put(current, entry);

        switch (current) {
            case SkillConstant.F:
                fillData(fx, fy, fc, fs, SkillEnum.F.k());
                break;
            case SkillConstant.R:
                fillData(rx, ry, rc, rs, SkillEnum.R.k());
                break;
            case SkillConstant.SF:
                fillData(sfx, sfy, sfc, sfs, SkillEnum.SF.k());
                break;
            case SkillConstant.SR:
                fillData(srx, sry, src, srs, SkillEnum.SR.k());
                break;
            case SkillConstant.X:
                fillData(xx, xy, xc, xs, SkillEnum.X.k());
                break;
            case SkillConstant.BUFF:
                fillData(buffx, buffy, buffc, buffs, SkillEnum.BUFF.k());
                break;
            case SkillConstant.C:
                fillData(cx, cy, cc, cs, SkillEnum.C.k());
                break;
            case SkillConstant.V:
                fillData(vx, vy, vc, vs, SkillEnum.V.k());
                break;
            default:
                return;
        }

        Util.savaKeyMap(keys);
    }

    @FXML
    public void save() {
        getNewestKeys();
        Util.savaKeyMap(keys);
    }

    /**
     * 获取最新的数据
     */
    public void getNewestKeys() {
        Map<String, Entry> newKeys = new HashMap<>();
        newKeys.put(SkillEnum.F.k(), Util.getEntry(SkillEnum.F, fx, fy, fc));
        newKeys.put(SkillEnum.SF.k(), Util.getEntry(SkillEnum.SF, sfx, sfy, sfc));
        newKeys.put(SkillEnum.R.k(), Util.getEntry(SkillEnum.R, rx, ry, rc));
        newKeys.put(SkillEnum.SR.k(), Util.getEntry(SkillEnum.SR, srx, sry, src));
        newKeys.put(SkillEnum.X.k(), Util.getEntry(SkillEnum.X, xx, xy, xc));
        newKeys.put(SkillEnum.BUFF.k(), Util.getEntry(SkillEnum.BUFF, buffx, buffy, buffc));
        newKeys.put(SkillEnum.C.k(), Util.getEntry(SkillEnum.C, cx, cy, cc));
        newKeys.put(SkillEnum.V.k(), Util.getEntry(SkillEnum.V, vx, vy, vc));
        keys = Util.removeMapEmptyValue(newKeys);
    }


    /**
     * 保存配置并返回
     */
    public void saveAndReturn() {
        getNewestKeys();
        Util.savaKeyMap(keys);
        //关闭事件
        Stage stage = (Stage) saveAndReturnButton.getScene().getWindow();
        stage.close();
    }

    /**
     * 界面填充数据
     */
    public void fillFullData() {
        fillData(fx, fy, fc, fs, SkillEnum.F.k());
        fillData(rx, ry, rc, rs, SkillEnum.R.k());

        fillData(sfx, sfy, sfc, sfs, SkillEnum.SF.k());
        fillData(srx, sry, src, srs, SkillEnum.SR.k());

        fillData(xx, xy, xc, xs, SkillEnum.X.k());
        fillData(buffx, buffy, buffc, buffs, SkillEnum.BUFF.k());

        fillData(cx, cy, cc, cs, SkillEnum.C.k());
        fillData(vx, vy, vc, vs, SkillEnum.V.k());
    }

    /**
     * 填充数据
     */
    public void fillData(TextField x, TextField y, TextField c, Label s, String skill) {
        Entry entry = keys.get(skill);
        if (entry == null) {
            return;
        }
        x.setText(entry.x + "");
        y.setText(entry.y + "");
        String rgbStr = entry.r + "," + entry.g + "," + entry.b;
        c.setText(rgbStr);
        s.setTextFill(Util.getColorByRgbStr(rgbStr));
    }


    /**
     * 获取f键信息
     *
     * @param event
     */
    @FXML
    public void getf(MouseEvent event) {
        current = SkillEnum.F.k();
    }

    @FXML
    public void getr(MouseEvent event) {
        current = SkillEnum.R.k();
    }

    @FXML
    public void getsf(MouseEvent event) {
        current = SkillEnum.SF.k();
    }

    @FXML
    public void getsr(MouseEvent event) {
        current = SkillEnum.SR.k();
    }

    @FXML
    public void getx(MouseEvent event) {
        current = SkillEnum.X.k();
    }

    @FXML
    public void getbuff(MouseEvent event) {
        current = SkillEnum.BUFF.k();
    }

    @FXML
    public void getc(MouseEvent event) {
        current = SkillEnum.C.k();
    }

    @FXML
    public void getv(MouseEvent event) {
        current = SkillEnum.V.k();
    }


    /**
     * 初始化
     */
    public void init() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.out.println(UnifyEnum.ROBOT_INIT_ERROR.v());
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(UnifyEnum.INITING.v());
        init();
        System.out.println(UnifyEnum.READING_CONFIG.v());
        keys = Util.loadingConfigFiles();
        System.out.println(UnifyEnum.FILLING_DATA.v());
        fillFullData();
    }

}
