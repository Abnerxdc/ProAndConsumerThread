package com.xdc.core;

import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2017/7/7.
 */
public class TaskProducerThread implements Runnable{
    private static Logger logger = Logger.getLogger(TaskProducerThread.class);

    String egg =" ";

    public TaskProducerThread(String egg){
        this.egg = egg;
    }

    @Override
    public void run(){
        try{
            logger.info(" 我生产 : "+egg+" current: "+Thread.currentThread().getName());
            StoreData.getInstance().getTask().put(egg);
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
