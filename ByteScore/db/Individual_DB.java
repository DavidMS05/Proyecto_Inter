package db;

import clases.Individual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> cargarNombres(Connection con) throws Exception {
        List<String> nombres = new ArrayList<String>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(
                    "select * from individual");
            rs = stmt.executeQuery();
            while (rs.next()) {
                String s = rs.getString("nom_comp");
                nombres.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al cargar individuales: " +
                    ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return nombres;
    }
}
