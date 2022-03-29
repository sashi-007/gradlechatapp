package com.github.shashi.chatapp.client;

import com.github.shashi.chatapp.client.chatTask;
import com.proto.chat.ChatServiceGrpc;
import com.proto.chat.messageRequest;
import com.proto.chat.messageResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;


public class Controller {
    @FXML
    private TextField textField;

    @FXML
    private TextField name;

    @FXML
    private ListView<String> listView;
    @FXML
    private Button sendButton;

    //private Service<ObservableList<String>> service;

    public String message;
    public String senderName;

    ObservableList<String> messages = FXCollections.observableArrayList();

    private StreamObserver<messageRequest> requestMessage;

    FileInputStream fis = null;

    public void initialize(){
        System.out.println("intialize() called");
//        service = new chatTask(this);
//        listView.itemsProperty().bind(service.valueProperty());

        URI configFileUri = null;
        try {
            configFileUri = getClass().getClassLoader().getResource("server.properties").toURI();

        } catch (URISyntaxException e) {
            System.out.println("ERROR in getting server.properties");
            e.printStackTrace();
        }
        System.out.println("server.properties URI found " +configFileUri.toString());
        //File configFile = new File(configFileUri);

        Properties properties = new Properties();
        try{
            //fis = new FileInputStream(configFile);

            //properties.load(fis);
            properties.load(getClass().getClassLoader().getResourceAsStream("server.properties"));
        }catch (Exception e) {
            System.out.println("error loading server.properties");
            e.printStackTrace();

        }
        finally {
            if(fis != null){
                try{
                    fis.close();
                }catch (IOException e){}
            }
        }
        System.out.println("server.properties loaded");

        String serverName = properties.getProperty("server");
        int port = 0;
        try {
            port = Integer.parseInt(properties.getProperty("port"));
        }catch (Exception e){
            System.out.println("Error loading port value from server.properties");
            e.printStackTrace();
        }


        System.out.println("server = " + serverName);



//        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",50051)
//                .usePlaintext()
//                .build();
        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverName, port)
                .usePlaintext()
                .build();

        ChatServiceGrpc.ChatServiceStub asyncClient = ChatServiceGrpc.newStub(channel);
        requestMessage  = asyncClient.chat(new StreamObserver<messageResponse>() {
            @Override
            public void onNext(messageResponse value) {
                System.out.println("previous messages in observable list are :" + messages.toString());

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        messages.add(value.getRequest().getFrom() + ": " +
                                value.getRequest().getMessage());
                        listView.setItems(messages);
                    }
                });
            }

            @Override
            public void onError(Throwable t) {
                //do nothing
            }

            @Override
            public void onCompleted() {
                // do nothing
            }
        });

        System.out.println("end of initialise method");
    }

    @FXML
    public void onButtonClicked(ActionEvent e){

        System.out.println("Button clicked");
        message = textField.getText();
        senderName = name.getText();
        requestMessage.onNext(messageRequest
                .newBuilder()
                .setFrom(senderName)
                .setMessage(message)
                .build());
//        if(service.getState() == Worker.State.SUCCEEDED){
//            System.out.println("worker state is SUCCEEDED");
//            service.reset();
//
//            service.start();
//        }else if(service.getState() == Worker.State.READY){
//            System.out.println("worker state is READY");
//            service.start();
//        }
        textField.clear();

    }
}
