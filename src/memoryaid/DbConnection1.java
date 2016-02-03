/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryaid;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author madhaviunnam
 */
public class DbConnection1 {

    private Connection DBConnection;
    
    // Change this parameters to your dataabse Connection Settings
    private final String server = "localhost";
    private final String database = "MemoryAid";
    private final String user = "root";
    private final String password = "";
    // - end
    
    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection Success");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Connection Fail" + cnfe);
        }
        String url = "jdbc:mysql://"+server+"/"+database;
        try {
            DBConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Database Connected");
        } catch (SQLException se) {
            System.out.println("No Database" + se);

        }
        return DBConnection;
    }

    // ResultSet to Hashtable of String
    public static HashMap<String, String[]> dbSelect(ResultSet rs) {

        HashMap<String, String[]> hMap = new HashMap<String, String[]>();

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                String[] data = new String[rsmd.getColumnCount()];
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    data[i] = rs.getString(i + 1);
                }
                hMap.put(data[rsmd.getColumnCount() - 1], data);
            }
            rs.close();
        } catch (Exception e) {
        }
        return hMap;
    }
}
