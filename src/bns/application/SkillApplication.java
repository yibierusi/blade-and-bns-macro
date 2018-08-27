package bns.application;

import bns.comm.Constant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @Auther: zhouhy
 * @Description:
 * @Date: Create in 11:06 2018/8/21
 * @Modified By
 */
public class SkillApplication extends Application {
    public SkillApplication() {
        Stage stage = new Stage();
        try {
            start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(Constant.SKILL.v()));
        primaryStage.setTitle("Skill");
        primaryStage.setScene(new Scene(root, 435, 600));
        primaryStage.getIcons().add(new Image(
                SkillApplication.class.getResourceAsStream(Constant.JAVA.v())));
        primaryStage.setAlwaysOnTop(true);//窗口总是在最前面
        primaryStage.setResizable(false);//禁止改变窗口大小
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
