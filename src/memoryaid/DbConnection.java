/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryaid;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author madhaviunnam
 */
public class DbConnection {
    private Connection DBConnection;
    public Connection connect() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection Success");
        }
        catch (ClassNotFoundException cnfe){
            System.out.println("Connection Fail" +cnfe);
        }
        String url = "jdbc:mysql://localhost:8889/MemoryAid";
        try{
            DBConnection = (Connection) DriverManager.getConnection(url, "root", "root");
            System.out.println("Database Connected");
        }
        catch(SQLException se){
            System.out.println("No Database" +se);
            
        } 
        return DBConnection;
        }
    }
    
