/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBOperaciones;

import DBConnection.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBCrud {

    DBConnect db;
    Connection con;
    Statement q;
    ResultSet result;

    public void insertValues(List values) {
        db = new DBConnect();
        con = db.Connect();

        try {
            q = con.createStatement();
            q.execute("INSERT INTO "
                    + "SM_ALVEO(IDPAIS,IDPLANTA,IDMUESTRA,FALVEO,P,L,G,W,P_L)"
                    + "VALUES('SV','M201','" + values.get(1) + "','" + values.get(0) + "',"
                    + "'" + values.get(2) + "','" + values.get(3) + "'"
                    + ",'" + values.get(4) + "','" + values.get(5) + "','" + values.get(6) + "')");
        } catch (SQLException ex) {
            System.out.println("Error en insertar Alveo: " + ex.getMessage());
        }
        
        try {
            con.close();
            q.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int findFile(Statement query, String file) {
        ResultSet result;
        String q = "SELECT * FROM SM_ALVEO WHERE FALVEO='" + file + "'";
        int ok = 0;
        try {
            result = query.executeQuery(q);
            while (result.next()) {
                ok = 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }
}
