package com.voidvvv.grpc_consumer.controller;

import com.example.grpc.GreeterGrpc;
import com.example.grpc.HelloReply;
import com.example.grpc.HelloRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grpc")
public class MyGrpcController {

    @GrpcClient(value = "grpc-server")
    GreeterGrpc.GreeterBlockingStub blockingStub;

    @GetMapping("/hello")
    public String hello (@RequestParam(name = "key") String key) {
        HelloRequest request = HelloRequest.newBuilder().setName(key).build();
        HelloReply reply = blockingStub.sayHello(request);
        String res =  reply.getMessage();
        return "Response from gRPC server: " + res;
    }
}
