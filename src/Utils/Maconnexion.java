/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import java.sql.*;
import javax.swing.JOptionPane;





/**
 *
 * @author Nour
 */
public class Maconnexion {
    static private Connection conn;
    private static Maconnexion instance ;
        public static Maconnexion getInstance(){
     if (instance == null){
        instance = new Maconnexion();
    }
    return instance ;
    
    }
         public Connection getCnx(){
    return conn ;
    } 
    
        String url="jdbc:mysql://localhost:3306/pidev";
        String user="root";
        String pwd="";
        public Maconnexion(){
            try {
                conn = DriverManager.getConnection(url, user, pwd);
                System.out.println("Connected");
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
        }
        public static Connection ConnectDb(){
        try {
            
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev","root","");
           // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public static Connection getConnection(){
        return conn;
    }
       
   
    
}
