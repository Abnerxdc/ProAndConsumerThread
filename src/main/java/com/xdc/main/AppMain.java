package com.xdc.main;

import com.xdc.core.TaskConsumerThread;
import com.xdc.core.TaskProducerThread;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/7/7.
 */
public class AppMain {
    private static Logger logger = Logger.getLogger(AppMain.class);
    public static void main(String[] args){
        try {
            //生产者 定义三条线程
            ExecutorService proExecutors = Executors.newFixedThreadPool(3);
            List<String> list = new ArrayList<String>();
            for(int i=0;i<10;i++){
                list.add("egg0"+i);
            }
            for (int i = 0; i < 10; i++) {
                //处理10条数据 每次只能拿到最多3条线程，其他的阻塞等待拿到线程再处理
                proExecutors.submit(new TaskProducerThread(list.get(i)));
            }

            //消费者 定义两条线程
            ExecutorService consumerExecutor = Executors.newFixedThreadPool(2);
            for (int i = 0; i < 10; i++) {
                TaskConsumerThread taskConsumerThread = new TaskConsumerThread();
                consumerExecutor.submit(taskConsumerThread);
            }
            while (!(consumerExecutor.isTerminated() || consumerExecutor.isShutdown())) {
                Thread.sleep(1000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
