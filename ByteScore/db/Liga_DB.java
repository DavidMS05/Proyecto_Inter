package db;

import clases.Liga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que interactúa con la tabla Liga.
 * @author Denys (3D)
 * @see clases.Liga
 */
public class Liga_DB {
    /**
     * Inserta una fila.
     * @param con conector
     * @param liga objeto a insertar
     * @throws Exception error de sql
     */
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

    /**
     * Carga todas las filas.
     * @param con conector
     * @return lista de String
     * @throws Exception error de sql
     */
    public List<String> cargarNombres(Connection con) throws Exception {
        List<String> nombres = new ArrayList<String>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(
                    "select * from liga");
            rs = stmt.executeQuery();
            while (rs.next()) {
                String s = rs.getString("nom_comp");
                nombres.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al cargar ligas: " +
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
