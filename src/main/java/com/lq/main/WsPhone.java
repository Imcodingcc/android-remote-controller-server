package com.lq.main;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;

@ServerEndpoint(value = "/phoneScreen")
public class WsPhone {

    @OnOpen
    public void onOpen(){
        System.out.println("phone is connected");
    }

    @OnMessage
    public void onMessage(byte[] message, Session session) throws IOException {
        StaticValue.jpgQueue.offer(ByteBuffer.wrap(message));
        session.getBasicRemote().sendText("ok");
    }

    @OnClose
    public void onClose(CloseReason reason){
        System.out.print(reason.getReasonPhrase());
    }
}