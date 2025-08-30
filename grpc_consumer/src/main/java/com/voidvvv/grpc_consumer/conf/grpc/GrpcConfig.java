package com.voidvvv.grpc_consumer.conf.grpc;

import net.devh.boot.grpc.client.inject.GrpcClientBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.grpc.client.ImportGrpcClients;

@Configuration
//@GrpcClientBean(
//        clazz = TestServiceGrpc.TestServiceBlockingStub.class,
//        beanName = "blockingStub",
//        client = @GrpcClient("test")
//)
public class GrpcConfig {

//    @GrpcC("grpc-server")
//    UserInfoServiceGrpc.UserInfoServiceFutureStub userInfoServiceStub;

    // GrpcClientBeanPostProcessor
//    @Bean
//    public GrpcClientBeanPostProcessor grpcClientBeanPostProcessor(ApplicationContext ware) {
//        return new GrpcClientBeanPostProcessor(ware);
//    }

}
