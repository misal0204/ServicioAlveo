/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBOperaciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBCrud {

    public void insertValues(Statement query, String[] values) {
        try {
            query.execute("INSERT INTO "
                    + "SM_ALVEO(IDPAIS,IDPLANTA,IDMUESTRA,FALVEO,P,L,G,W,P_L)"
                    + "VALUES('SV','M201','" + values[1] + "','" + values[0] + "','" + values[2] + "','" + values[3] + "'"
                    + ",'" + values[4] + "','" + values[5] + "','" + values[6] + "')");
        } catch (SQLException ex) {

            System.out.println("Error en insertar lote de inspección: " + ex.getMessage());
        }
    }

    public int findFile(Statement query,String file) {
        ResultSet result;
        String q = "SELECT * FROM SM_ALVEO WHERE NOT EXISTS FALVEO='"+file+"'";
        int ok=0;
        try {
            result = query.executeQuery(q);
            while (result.next()) {
                ok=1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }
}
