package com.xdc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2017/7/7.
 */
public class SqlQuery {
    private Connection mConn;
    private Statement mStmt;
    public SqlQuery(Connection iConnect)throws SQLException{
        mConn = iConnect;
        mStmt = mConn.createStatement();
    }
    public boolean insertRows(String sql)throws Exception{
        return mStmt.execute(sql);
    }
}
