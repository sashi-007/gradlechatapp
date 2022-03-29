package com.github.shashi.chatapp.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class Main extends Application {

    Parent root = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("Starting chat app");
        //URL url = new File("src/main/java/com/github/shashi/chatapp/client/chat.fxml").toURI().toURL();
        //URL url = getClass().getClassLoader().getResource("chat.fxml");
        //System.out.println("chat.fxml URL is " + url.toString());
        try {
             //root = FXMLLoader.load(url);


             //the following code is for loading fxml in a jar
            FXMLLoader fxmlLoader = new FXMLLoader();

            //fxmlLoader.setLocation(this.getClass().getResource("/build/resources/main/chat.fxml"));
            //root = fxmlLoader.load();
            System.out.println("getting inputstream object for chat.fxml ");
            InputStream  is = this.getClass().getClassLoader().getResourceAsStream("chat.fxml");
            if ( is != null) {
                System.out.println("Input stream found from chat.fxml");
            }

             root = fxmlLoader.load(is);
            //root = FXMLLoader.load(getClass().getClassLoader().getResource("../../chat.fxml"));
        }catch (Exception e){
            System.out.println("Error loading chat.fxml");
            e.printStackTrace();
        }
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
