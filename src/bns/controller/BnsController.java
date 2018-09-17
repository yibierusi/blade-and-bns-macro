package bns.controller;

import bns.application.SkillApplication;
import bns.comm.Constant;
import bns.thread.KeyThread;
import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;
import javafx.application.Platform;
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
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //更新JavaFX的主线程的代码放在此处
                    scriptControlButton.setText(Constant.STOP_SCRIPT.v());
                }
            });
            System.out.println("脚本启动中...");
            return;
        }
        keyThread.kill();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //更新JavaFX的主线程的代码放在此处
                scriptControlButton.setText(Constant.START_SCRIPT.v());
            }
        });
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

    public void addHotKeyListener(){
        //第一步：注册热键，第一个参数表示该热键的标识，第二个参数表示组合键，如果没有则为0，第三个参数为定义的主要热键
        JIntellitype.getInstance().registerHotKey(0, JIntellitype.MOD_ALT, (int)'S');

        //第二步：添加热键监听器
        JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
            @Override
            public void onHotKey(int code) {
                try {
                    //如果有子线程中更新主线程的代码，更新JavaFX的主线程的代码放在Platform.runLater run 中
                    startAndStop();
                }catch (Exception e){}
            }
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        keyThread = null;
        addHotKeyListener();
        System.out.println("initialize...");
    }
}
