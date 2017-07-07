package com.xdc.pool;

import org.apache.commons.pool.impl.GenericObjectPool;

import java.sql.Connection;
import java.util.Properties;

/**
 * Created by Administrator on 2017/7/7.
 */
public class PGQueryPool {
    private static PGPoolableObjectFactory factory;
    private static GenericObjectPool pool;
    private PGQueryPool(){
        Properties pgProperties = new Properties();
        pgProperties.setProperty("driver","");
        pgProperties.setProperty("url","");
        pgProperties.setProperty("user","");
        pgProperties.setProperty("password","");
        this.factory = new PGPoolableObjectFactory(pgProperties);
        GenericObjectPool.Config config = new GenericObjectPool.Config();
        config.testWhileIdle = true;
        config.timeBetweenEvictionRunsMillis = 3000;
        config.numTestsPerEvictionRun = 16;
        config.maxActive = 12;
        config.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_BLOCK;
        config.maxWait = 100;
        config.testOnBorrow = true;
        config.testOnReturn = true;
        this.pool = new GenericObjectPool(factory,config);
    }
    private static PGQueryPool pgQueryPool;
    public static PGQueryPool getInstance(){
        if (pgQueryPool != null){

        }else {
            synchronized (PGQueryPool.class){
                if (pgQueryPool == null){
                    pgQueryPool = new PGQueryPool();
                }
            }
        }
        return pgQueryPool;
    }
    public Connection borrowConnect()throws Exception{
        return (Connection)pool.borrowObject();
    }
    public void returnConnect(Connection iConn)throws Exception{
        pool.returnObject(iConn);
    }
    public void closeConnect()throws Exception{
        factory.conn.close();
    }
}
