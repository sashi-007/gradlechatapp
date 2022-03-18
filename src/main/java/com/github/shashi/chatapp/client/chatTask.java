package com.github.shashi.chatapp.client;

import com.proto.chat.ChatServiceGrpc;
import com.proto.chat.messageRequest;
import com.proto.chat.messageResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class chatTask<Controller> extends Service<ObservableList<String>> {

    private String message;
    private String name;
    private final com.github.shashi.chatapp.client.Controller controller;
    ObservableList<String> messages = FXCollections.observableArrayList();

    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",50051)
            .usePlaintext()
            .build();

    ChatServiceGrpc.ChatServiceStub asyncClient = ChatServiceGrpc.newStub(channel);

    public chatTask(com.github.shashi.chatapp.client.Controller controller) {
        this.controller = controller;

    }




    @Override
    protected Task<ObservableList<String>> createTask() {
        return new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws Exception {
                //System.out.println("inside background task");

                name = controller.senderName;
                message = controller.message;
                StreamObserver<messageRequest> request  = asyncClient.chat(new StreamObserver<messageResponse>() {
                    @Override
                    public void onNext(messageResponse value) {
                        System.out.println("previous messages in observable list are :" + messages.toString());
                        messages.add(value.getRequest().getFrom() + ": " +
                                value.getRequest().getMessage());
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
                //System.out.println("background task being executed : "+ message);
                //ObservableList<String> messages = FXCollections.observableArrayList();
                //messages.add(message);
                request.onNext(messageRequest
                        .newBuilder()
                        .setFrom(name)
                        .setMessage(message)
                        .build());
                //request.onCompleted();
                return  messages;
            }
        };


    }


}
