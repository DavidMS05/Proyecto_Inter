package db;

import clases.Liga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Liga_DB {
    public void inserta(Connection con, Liga liga) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into liga" +
                    "(nom_comp) values (?)");
            stmt.setString(1, liga.getNombre());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar liga: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
}
