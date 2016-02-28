package procesos;

import DBConnection.DBConnect;
import DBOperaciones.DBCrud;
import Log.LogEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class File_Process {

    DBConnect db;
    Connection con;
    Statement q;
    ResultSet result;

    public void FileSearch(String file) {
        db = new DBConnect();
        con = db.Connect();
        try {
            q = con.createStatement();
            boolean get = new DBCrud().findFile(q, file);

            /*if (get) {
             System.out.println("Encontrado: " + file);
             } else {
             System.out.println("No Encontrado: " + file);
             new FindTxt().ReadFile(file);
             }*/
            
            
            if (!get) {
                //System.out.println("No Encontrado: " + file);
                System.out.println("Busqueda de datos en: "+file);
                new LogEvent().LogAlveo("Archivo a leer: "+file);
                new FindTxt().ReadFile(file);
            }

        } catch (SQLException ex) {
            System.err.println("Busqueda de archivo: " + ex.getMessage());
        }
        try {
            con.close();
            q.close();
        } catch (SQLException ex) {
            System.err.println("Error en buscar archivo");
        }
    }
}
