package bns;

import bns.comm.Constant;
import bns.thread.KeyThread;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(Constant.BNS.v()));
        primaryStage.setTitle("BNS");
        Scene scene = new Scene(root, 122, 67);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(
                Main.class.getResourceAsStream(Constant.JAVA.v())));
        primaryStage.setAlwaysOnTop(true);//窗口总是在最前面
        primaryStage.setResizable(false);//禁止改变窗口大小
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                KeyThread.kill();
                System.out.println("线程已被强制杀死");
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
