package com.lq.main;

import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingQueue;

public class StaticValue {
    public static LinkedBlockingQueue<ByteBuffer> jpgQueue = new LinkedBlockingQueue<>(100);
    public static LinkedBlockingQueue<String> optQueue = new LinkedBlockingQueue<>(100);
    public static LinkedBlockingQueue<String> msgQueue = new LinkedBlockingQueue<>(100);
}
