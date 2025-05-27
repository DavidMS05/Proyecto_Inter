package db;

import clases.Juego;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Juego_DB {
    public void actualiza(Connection con, Juego juego) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update juego set nom_juego = ? where cod_juego = ?");
            stmt.setString(1, juego.getNombre());
            stmt.setInt(2, juego.getCod());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al actualizar juego: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    public void elimina(Connection con, Juego juego) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from juego where cod_juego = ?");
            stmt.setInt(1, juego.getCod());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al eliminar juego: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    public void inserta(Connection con, Juego juego) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into juego" +
                    "(cod_juego,nom_juego) " +
                    "values (null,?)");
            stmt.setString(1, juego.getNombre());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar juego: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    private void obtenJuegoFila(Connection con, ResultSet rs, Juego juego) throws Exception {
        juego.setCod(rs.getInt("cod_juego"));
        juego.setNombre(rs.getString("nom_juego"));
    }
    
    public Juego findByCod(Connection con, int cod) throws Exception {
        Juego _juego = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from juego where cod_juego = ?");
            stmt.setInt(1, cod);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                _juego = new Juego();
                obtenJuegoFila(con, rs, _juego);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar juego por cod: " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _juego;
    }

    public Juego findByNom(Connection con, String nom) throws Exception {
        Juego _juego = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from juego where nom_juego like ?");
            stmt.setString(1, nom);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                _juego = new Juego();
                obtenJuegoFila(con, rs, _juego);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar juego por cod: " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _juego;
    }
}
