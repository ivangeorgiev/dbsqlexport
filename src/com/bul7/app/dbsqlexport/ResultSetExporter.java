/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bul7.app.dbsqlexport;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ivan
 */
public abstract class ResultSetExporter {
    public abstract void export(ResultSet rs) throws SQLException;
}
