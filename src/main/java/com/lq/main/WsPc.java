package com.lq.main;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/pcScreen")
public class WsPc {
    private Thread thread = null;
    private SendJpgRunnable runnable = null;

    @OnOpen
    public void onOpen(Session session){
        System.out.println("pc is connected");
        runnable = new SendJpgRunnable(session);
        thread = new Thread(runnable);
        thread.start();
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("received msg from pc" + message);
    }

    @OnClose
    public void onClose(CloseReason reason){
        System.out.print(reason.getReasonPhrase());
        runnable.terminate();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}