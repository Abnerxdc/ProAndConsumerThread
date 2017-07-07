package com.xdc.core;

import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2017/7/7.
 */
public class TaskConsumerThread implements Runnable{
    private static Logger logger = Logger.getLogger(TaskConsumerThread.class);
    @Override
    public void run(){
        try{
            BlockingQueue<String> block = StoreData.getInstance().getTask();
            String egg = block.take();
            logger.info(" 我拿走 ： "+egg+"  "+Thread.currentThread().getName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
