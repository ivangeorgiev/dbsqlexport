/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bul7.app.dbsqlexport;

import java.io.PrintStream;
import java.sql.SQLException;

/**
 *
 * @author ivan
 */
public class PrintStreamSqlWriter extends SqlWriter {
    private PrintStream stream;

    public PrintStreamSqlWriter(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    protected void doWrite(String sql) throws SQLException {
        stream.println(sql);
    }
    
}
