package com.xdc.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2017/7/7.
 */
public class StoreData {
    private static StoreData instance = new StoreData();
    private BlockingQueue<String> task = new LinkedBlockingQueue<String>();
    public static StoreData getInstance(){
        return instance;
    }
    public BlockingQueue<String> getTask(){
        return task;
    }
}
