package com.zefir.websocket.handler;


import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class MyRawWebSocketHandler extends TextWebSocketHandler {
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        TextMessage msg = new TextMessage("Client connection success!");
//client will receive this frame as a callback to the success event
    session.sendMessage(msg);
}
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
// this is the message content, that can be any format (json, xml, plain text... who knows?)
        System.out.println(message.getPayload());
        TextMessage msg = new TextMessage("Message received. Thank you, client!");
        session.sendMessage(msg);
    }
}