package com.lq.main;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/controllerPc")
public class WsControllerPc {

    private Thread msgThread = null;
    private SendMsgRunnable sendMsgRunnable = null;

    @OnOpen
    public void onOpen(Session session){
        sendMsgRunnable = new SendMsgRunnable(session);
        msgThread = new Thread(sendMsgRunnable);
        msgThread.start();
        System.out.println("pc controller is connected");
    }

    @OnMessage
    public void onMessage(String message){
        System.out.println("received msg from pc controller" + message);
        StaticValue.optQueue.offer(message);
    }

    @OnClose
    public void onClose(CloseReason reason){
        System.out.print(reason.getReasonPhrase());
        sendMsgRunnable.terminate();
        try {
            msgThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}