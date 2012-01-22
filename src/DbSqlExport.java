/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import jargs.gnu.CmdLineParser;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bul7.app.dbsqlexport.*;

/**
 *
 * @author ivan
 */
public class DbSqlExport {

    private static void printUsage() {
        System.err.println(
"Usage: OptionTest [{-c,--driver-class} class-name] [{-u,--url} url]  \n" +
"                  [{-t,--target-table} table_name] [{-s,--select-sql} sql]");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String className = "org.relique.jdbc.csv.CsvDriver";
        String dirName = "F:/Data/Documents/NetBeansProjects/Sandbox/data/csv";
        String sql = "SELECT * FROM persons";
        String targetTableName = "<table-name>";
        String url = "jdbc:relique:csv:" + dirName;
        
        CmdLineParser parser = new CmdLineParser();
        CmdLineParser.Option classOption = parser.addStringOption('c', "driver-class");
        CmdLineParser.Option urlOption = parser.addStringOption('u', "url");
        CmdLineParser.Option tableOption = parser.addStringOption('t', "target-table");
        CmdLineParser.Option sqlOption = parser.addStringOption('s', "select-sql");
        
        try {
            parser.parse(args);
        }
        catch ( CmdLineParser.OptionException e ) {
            System.err.println(e.getMessage());
            printUsage();
            System.exit(2);
        }
        
        className = (String) parser.getOptionValue(classOption);
        url = (String) parser.getOptionValue(urlOption);
        targetTableName = (String) parser.getOptionValue(tableOption, targetTableName);
        sql = (String) parser.getOptionValue(sqlOption);
        
        List<String> errors = new ArrayList();
        if (url == null) {
            errors.add("Url not specified.");
        }
        if (sql == null) {
            errors.add("Sql not specified.");
        }

        if (errors.size() > 0) {
            for(String msg: errors) {
                System.err.println(msg);
                printUsage();
                System.exit(2);
            }
        }
        
        
        SqlWriter writer = new PrintStreamSqlWriter(System.out);
        ResultSetExporter exporter = new SqlExporter(targetTableName, writer);
        SqlExportAction action;
        if (className == null) {
            action = new SqlExportAction(url, sql);
        } else {
            action = new SqlExportAction(className, url, sql);
            
        }
        action.export(exporter);
    }
}
