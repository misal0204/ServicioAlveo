package procesos;

import DBConnection.DBConnect;
import DBOperaciones.DBCrud;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class File_Process {

    DBConnect db;
    Connection con;
    Statement q;
    ResultSet result;

    public void FileProcessRead() {
        db = new DBConnect();
        con = db.Connect();
        try {
            q = con.createStatement();
            new DBCrud().insertValues(q, new FindTxt().ReadFile());
        } catch (SQLException ex) {
            //Logger.getLogger(File_Process.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Insert file: "+ex.getMessage());   
        }
    }
}
