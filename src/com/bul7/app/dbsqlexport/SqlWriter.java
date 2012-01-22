/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bul7.app.dbsqlexport;

import java.sql.SQLException;

/**
 *
 * @author ivan
 */
public abstract class SqlWriter {
    public void write(String sql) throws SQLException {
        doWrite(sql);
    }
    
    public void beginWrite() {
        doBeginWrite();
    }
    
    public void endWrite() {
        doEndWrite();
    }
    
    protected void doBeginWrite() {
        
    }
    
    protected void doEndWrite() {
        
    }
    
    protected abstract void doWrite(String sql) throws SQLException;
}
