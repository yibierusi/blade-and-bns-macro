package bns.controller;

import bns.application.SkillApplication;
import bns.comm.Constant;
import bns.thread.KeyThread;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class BnsController implements Initializable {
    private KeyThread keyThread;

    @FXML
    private Button scriptControlButton;


    /**
     * 通过按钮启动或者停止脚本
     */
    @FXML
    public void startAndStopScript(MouseEvent event) {
        startAndStop();
    }

    /**
     * 通过快捷键启动或者停止脚本
     */
    @FXML
    public void action(KeyEvent event) {
        if (event.getCode().getName().equals("F11")) {
            startAndStop();
        }
    }

    /**
     * 启动或者停止脚本
     */
    public void startAndStop() {
        if (keyThread == null) {
            keyThread = new KeyThread();
            keyThread.start();
            scriptControlButton.setText(Constant.STOP_SCRIPT.v());
            System.out.println("脚本启动中...");
            return;
        }
        keyThread.kill();
        scriptControlButton.setText(Constant.START_SCRIPT.v());
        System.out.println("脚本停止中...");
        keyThread = null;
    }

    /**
     * 生成获取技能信息界面
     */
    @FXML
    public void getSkill(MouseEvent event) throws Exception {
        new SkillApplication();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        keyThread = null;
        System.out.println("读取配置中...");
    }
}
