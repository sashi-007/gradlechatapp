package com.github.shashi.chatapp.server;

import com.proto.chat.ChatServiceGrpc;
import com.proto.chat.messageRequest;
import com.proto.chat.messageResponse;
import io.grpc.stub.StreamObserver;

import java.util.LinkedHashSet;

public class ChatServiceImpl extends ChatServiceGrpc.ChatServiceImplBase {
    private  static LinkedHashSet<StreamObserver<messageResponse>> observers = new LinkedHashSet<>();
    @Override
    public StreamObserver<messageRequest> chat(StreamObserver<messageResponse> responseObserver) {
        observers.add(responseObserver);
        return new StreamObserver<messageRequest>() {
            @Override
            public void onNext(messageRequest value) {
                messageResponse response = messageResponse.newBuilder()
                            .setRequest(value)
                            .build();
                observers.stream().forEach(o -> o.onNext(response));
            }

            @Override
            public void onError(Throwable t) {
                observers.remove(responseObserver);
            }

            @Override
            public void onCompleted() {
                observers.remove(responseObserver);
                //responseObserver.onCompleted();

            }
        };

        // 1. implement the server
        // 2. register the response observer
        // 3. make sure messages are propagated to all observers
        // 4. ake sure observers are unregistered onError or onCompleted



    }
}
