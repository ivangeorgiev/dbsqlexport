/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bul7.app.dbsqlexport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ivan
 */
public class SqlExportAction {
    private String url;
    private String sql;
    
    public SqlExportAction(String url, String sql) throws SQLException {
        this.url = url;
        this.sql = sql;
    }
    
    public SqlExportAction(String className, String url, String sql) throws SQLException, ClassNotFoundException {
        this(url, sql);
        Class.forName(className);
    }
    
    public void export(ResultSetExporter writer) throws SQLException {
        ResultSet resultSet = getResultSet();
        writer.export(resultSet);
    }
    
    private ResultSet getResultSet() throws SQLException {
        return getResultSet(url, sql);
    }
    
    private ResultSet getResultSet(String url, String sql) throws SQLException {
        return getResultSet(DriverManager.getConnection(url), sql);
    }
    
    private ResultSet getResultSet(Connection conn, String sql) throws SQLException {
        return conn.createStatement().executeQuery(sql);
    }
    
}
