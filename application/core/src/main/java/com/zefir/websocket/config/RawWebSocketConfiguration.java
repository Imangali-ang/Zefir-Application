//package com.zefir.websocket.config;
//
//import com.zefir.websocket.handler.MyRawWebSocketHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//@Configuration
//@EnableWebSocket
//public class RawWebSocketConfiguration implements WebSocketConfigurer {
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(myRawWebSocketHandler(),  "/rawwebsocket");
//    }
//
//    @Bean
//    public WebSocketHandler myRawWebSocketHandler() {
//        return new MyRawWebSocketHandler();
//    }
//}
