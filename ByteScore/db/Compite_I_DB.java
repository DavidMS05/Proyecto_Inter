package db;

import clases.Compite_I;
import clases.Individual;
import clases.Jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Compite_I_DB {
    public void actualiza(Connection con, Compite_I compite_i) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update compite_i set ronda = ? where nom_comp like ? and dni like ?");
            stmt.setInt(1, compite_i.getNumRonda());
            stmt.setString(2, compite_i.getCompeticion().getNombre());
            stmt.setString(3, compite_i.getJugador().getDni());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al actualizar compite_i: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    public void elimina(Connection con, Compite_I compite_i) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from compite_i where nom_comp like ? and dni like ?");
            stmt.setString(1, compite_i.getCompeticion().getNombre());
            stmt.setString(1, compite_i.getJugador().getDni());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al eliminar compite_i: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    public void inserta(Connection con, Compite_I compite_i) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into compite_i" +
                    "(nom_comp,dni,ronda) " +
                    "values (?,?,?)");
            stmt.setString(1, compite_i.getCompeticion().getNombre());
            stmt.setString(2, compite_i.getJugador().getDni());
            stmt.setInt(3, compite_i.getNumRonda());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar compite_i: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    private void obtenCompiteFila(Connection con, ResultSet rs, Compite_I compite_i) throws Exception {
        Individual indiv = new Individual();
        Jugador jug = new Jugador();
        indiv.setNombre(rs.getString("nom_comp"));
        jug.setDni(rs.getString("dni"));
        compite_i.setCompeticion(
            (Individual) new Competicion_DB().findByNom(con, indiv, new Individual()));
        compite_i.setJugador(new Jugador_DB().findByDni(con, jug));
        compite_i.setNumRonda(rs.getInt("ronda"));
    }
    
    public Compite_I findByNomDni(Connection con, String nom_comp, int dni) throws Exception {
        Compite_I _compite = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from compite_i where nom_comp like ? and dni like ?");
            stmt.setString(1, nom_comp);
            stmt.setInt(2, dni);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                _compite = new Compite_I();
                obtenCompiteFila(con, rs, _compite);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar el compite_i por nom y dni: " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _compite;
    }
}
