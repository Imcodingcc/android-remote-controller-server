package com.lq.main;

import javax.websocket.Session;
import java.nio.ByteBuffer;

public class SendJpgRunnable implements Runnable{

    private  Session session;
    private volatile boolean running =true;
    SendJpgRunnable(Session session){
        this.session = session;
    }

    public void terminate(){
        running = false;
    }
    @Override
    public void run() {
        while(running){
            try {
                ByteBuffer bb = StaticValue.jpgQueue.take();
                session.getBasicRemote().sendBinary(bb);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}