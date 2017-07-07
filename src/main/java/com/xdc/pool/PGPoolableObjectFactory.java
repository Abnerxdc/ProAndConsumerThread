package com.xdc.pool;

import org.apache.commons.pool.PoolableObjectFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Administrator on 2017/7/7.
 */
public class PGPoolableObjectFactory implements PoolableObjectFactory {
    private Properties mProps = new Properties();
    public PGPoolableObjectFactory(Properties mProps){
        this.mProps = mProps;
    }
    public Connection conn;
    @Override
    public Object makeObject()throws Exception{
        String driver = mProps.getProperty("driver");
        String url = mProps.getProperty("url");
        String user = mProps.getProperty("user");
        String password = mProps.getProperty("password");
        String options = mProps.getProperty("options","useUnicode=true&characterEncoding=UTF8");
        String fullUrl = url+"?"+"user="+user+"&password="+password+"&"+options;
        try{
            Class.forName(driver).newInstance();
        }catch (Exception e){
            throw new SQLException("unknown database type"+driver);
        }
        conn = DriverManager.getConnection(fullUrl);
        return conn;
    }
    @Override
    public void destroyObject(Object obj)throws Exception{
        Connection conn = (Connection)obj;
        conn.close();
    }
    @Override
    public boolean validateObject(Object obj){
        Connection conn = (Connection)obj;
        try{
            return !conn.isClosed();
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public void activateObject(Object obj)throws Exception{
        Connection conn = (Connection)obj;
    }
    @Override
    public void passivateObject(Object obj)throws Exception{
        Connection conn = (Connection)obj;
    }
}
