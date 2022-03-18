package com.github.shashi.chatapp.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class chatServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("GRPC Chat server");
        Server server = ServerBuilder.forPort(50051)
                .addService(new ChatServiceImpl())
                .build();
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            System.out.println("Received shutdown Request");
            server.shutdown();
            System.out.println("Succesfully stopped Chat server");
        }));
        server.awaitTermination();
    }
}
