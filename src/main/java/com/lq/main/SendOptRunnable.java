package com.lq.main;

import javax.websocket.Session;

public class SendOptRunnable implements Runnable{

    private  Session session;
    private volatile boolean running =true;
    SendOptRunnable(Session session){
        this.session = session;
    }

    public void terminate(){
        running = false;
    }

    @Override
    public void run() {
        while(running){
            try {
                String json = StaticValue.optQueue.take();
                session.getBasicRemote().sendText(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}