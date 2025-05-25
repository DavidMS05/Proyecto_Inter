package db;

import clases.Eliminatoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Eliminatoria_DB {
    public void inserta(Connection con, Eliminatoria eliminatoria) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into eliminatoria" +
                    "(nom_comp) values (?)");
            stmt.setString(1, eliminatoria.getNombre());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar eliminatoria: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
}
