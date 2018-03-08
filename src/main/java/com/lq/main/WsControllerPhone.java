package com.lq.main;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/controllerPhone")
public class WsControllerPhone {

    private Thread optThread = null;
    private SendOptRunnable sendOptRunnable = null;

    @OnOpen
    public void onOpen(Session session){
        System.out.println("phone controller is connected");
        sendOptRunnable = new SendOptRunnable(session);
        optThread = new Thread(sendOptRunnable);
        optThread.start();
    }

    @OnMessage
    public void onMessage(String message){
        System.out.println("received msg from phone controller" + message);
        StaticValue.msgQueue.offer(message);
    }

    @OnClose
    public void onClose(CloseReason reason){
        System.out.print(reason.getReasonPhrase());
        sendOptRunnable.terminate();
        try {
            optThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}