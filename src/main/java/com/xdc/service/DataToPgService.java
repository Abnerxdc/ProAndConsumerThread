package com.xdc.service;

import com.xdc.dao.SqlQuery;
import com.xdc.pool.PGQueryPool;

import java.sql.Connection;

/**
 * Created by Administrator on 2017/7/7.
 */
public class DataToPgService {
    public static boolean insertData()throws Exception{
        Connection conn = PGQueryPool.getInstance().borrowConnect();
        SqlQuery mSqlQuery = new SqlQuery(conn);
        try{
            mSqlQuery.insertRows("insert into .....");
        }catch (Exception e){
            e.printStackTrace();
        }
        PGQueryPool.getInstance().returnConnect(conn);
        return true;
    }
}
