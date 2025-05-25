package db;

import clases.Individual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Individual_DB {
    public void inserta(Connection con, Individual individual) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into individual" +
                    "(nom_comp) values (?)");
            stmt.setString(1, individual.getNombre());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar individual: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
}
