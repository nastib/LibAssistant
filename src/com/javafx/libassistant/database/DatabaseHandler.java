package com.javafx.libassistant.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
/**
 *
 * @author ADMIN
 */
public final class DatabaseHandler {
   private static final String DB_URL  = "jdbc:derby:database/library;create=true";
   private static Connection conn = null;
   private static Statement stmt = null;
   private static String TABLE_NAME;
   private static DatabaseHandler handler;
   
           
   private DatabaseHandler(){
       createConnection();
       setupBookTable();
       setupMemberTable();
   }
   
   //Singleton Pattern
   public static DatabaseHandler getInstance(){
       
       if(handler == null ){
           handler = new DatabaseHandler();
       }
       return handler; 
   }
   
   private void createConnection(){
       try {
          Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance(); 
          conn = DriverManager.getConnection(DB_URL);
       } catch(Exception e){
           e.printStackTrace();
       }
   }
   
   private void setupBookTable(){
       //CREATE BOOK TABLE
       TABLE_NAME = "BOOK";
       try {
           stmt = conn.createStatement();
           DatabaseMetaData dbm = conn.getMetaData();
           ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(),null);
           if(tables.next()){
               System.out.println("Table ".concat(TABLE_NAME).concat(" existe déjà. ça roule !"));
           } else {
             stmt.execute("CREATE TABLE "+TABLE_NAME+ "("
                     +"    id varchar(200) primary key, \n"
                     +"    title varchar(200),\n"
                     +"    author varchar(200),\n"
                     +"    publisher varchar(100),\n"
                     +"    isAvail boolean default true"
                     +")")  ;
           }
       } catch (SQLException e){
           System.err.println(e.getMessage().concat("...setupDatabase"));
       } finally {}
   }
    
    private void setupMemberTable(){
    //CREATE MEMBER TABLE
    TABLE_NAME = "MEMBER";
    try {
        stmt = conn.createStatement();
        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(),null);
        if(tables.next()){
            System.out.println("Table ".concat(TABLE_NAME).concat(" existe déjà. ça roule !"));
        } else {
          stmt.execute("CREATE TABLE "+TABLE_NAME+ "("
                  +"    id varchar(200) primary key, \n"
                  +"    name varchar(200),\n"
                  +"    mobile varchar(200),\n"
                  +"    email varchar(100)"
                  +")")  ;
        }
    } catch (SQLException e){
        System.err.println(e.getMessage().concat("...setupDatabase"));
    } finally {}
   }
   
   public static Statement getStatement(){
       return stmt;
   }
 
}
