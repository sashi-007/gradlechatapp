package com.github.shashi.chatapp.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //URL url = new File("src/main/java/com/github/shashi/chatapp/client/chat.fxml").toURI().toURL();
        URL url = getClass().getClassLoader().getResource("chat.fxml");
        Parent root = FXMLLoader.load(url);
        //Parent root = FXMLLoader.load(getClass().getResource("chat.fxml"));
        primaryStage.setTitle("GRPC Chat App");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        System.out.println("Start method  invoked");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
