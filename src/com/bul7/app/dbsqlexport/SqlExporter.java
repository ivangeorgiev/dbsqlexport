/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bul7.app.dbsqlexport;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author ivan
 */
public class SqlExporter extends ResultSetExporter {
    private String tableName = "<table-name>";
    private SqlWriter writer;
    
    public SqlExporter(String tableName, SqlWriter writer) {
        this.tableName = tableName;
        this.writer = writer;
    }

    @Override
    public void export(ResultSet rs) throws SQLException {
        beginWrite(rs);
        while (rs.next()) {
            writeRow(rs);
        }
        endWrite(rs);
    }
    
    private void beginWrite(ResultSet rs) {
        writer.beginWrite();
    }
    
    private void endWrite(ResultSet rs) {
        writer.endWrite();
    }
    
    private void writeRow(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();

        StringBuilder sql = new StringBuilder(1024);
        sql.append("INSERT INTO ").append(tableName).append(" (");
        
        for(int i = 1; i <= md.getColumnCount(); i++) {
            if (i > 1) {
                sql.append(", ");
            }
            sql.append(md.getColumnName(i));
        }
        sql.append(") VALUES (");
        for(int i = 1; i <= md.getColumnCount(); i++) {
            if (i > 1) {
                sql.append(", ");
            }
            sql.append("\"").append(rs.getString(i)).append("\"");
        }
        sql.append(");");
        writer.write(sql.toString());
    }
}
