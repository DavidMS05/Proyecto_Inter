package db;

import clases.Equipo;
import clases.Jugador;
import clases.Forma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class Forma_DB {
    public void actualiza(Connection con, Forma forma) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update forma set capitan = ?, titular = ? where id_equipo = ? and dni like ?");
            stmt.setBoolean(1, forma.getCapitan());
            stmt.setBoolean(2, forma.getTitular());
            stmt.setInt(3, forma.getEquipo().getCod());
            stmt.setString(4, forma.getJugador().getDni());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al actualizar forma: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    public boolean elimina(Connection con, Forma forma) throws Exception {
        PreparedStatement stmt = null;
        boolean exito = false;
        try {
            stmt = con.prepareStatement("delete from forma where id_equipo = ? and dni like ?");
            stmt.setInt(1, forma.getEquipo().getCod());
            stmt.setString(2, forma.getJugador().getDni());

            if (stmt.executeUpdate() > 0)
                exito = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al eliminar forma: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
        return exito;
    }
    
    public void inserta(Connection con, Forma forma) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into forma" +
                    "(id_equipo,dni,capitan,titular) " +
                    "values (?,?,?,?)");
            stmt.setInt(1, forma.getEquipo().getCod());
            stmt.setString(2, forma.getJugador().getDni());
            stmt.setBoolean(3, forma.getCapitan());
            stmt.setBoolean(4, forma.getTitular());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar forma: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    private void obtenFormaFila(Connection con, ResultSet rs, Forma forma) throws Exception {
        Equipo eq = new Equipo();
        eq.setCod(rs.getInt("id_equipo"));
        Jugador jug = new Jugador();
        jug.setDni(rs.getString("dni"));
        forma.setEquipo(new Equipo_DB().findById(con, eq));
        forma.setJugador(new Jugador_DB().findByDni(con, jug));
        forma.setCapitan(rs.getBoolean("capitan"));
        forma.setTitular(rs.getBoolean("titular"));
    }
    
    public Forma findByIdDni(Connection con, Forma forma) throws Exception {
        Forma _forma = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from forma where id_equipo = ? and dni like ?");
            stmt.setInt(1, forma.getEquipo().getCod());
            stmt.setString(2, forma.getJugador().getDni());
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                _forma = new Forma();
                obtenFormaFila(con, rs, _forma);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar forma por id y dni: " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _forma;
    }

    public List<Jugador> findByEquipo(Connection con, Equipo equipo) throws Exception {
        List<Jugador> _listaJugadores = new ArrayList<Jugador>();
        Forma _forma = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from forma where id_equipo = ?");
            stmt.setInt(1, equipo.getCod());
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                _forma = new Forma();
                obtenFormaFila(con, rs, _forma);
                _listaJugadores.add(_forma.getJugador());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al cargar forma por equipo: " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _listaJugadores;
    }
}
