package db;

import clases.Jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class Jugador_DB {
    public void actualiza(Connection con, Jugador jugador) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update jugador set nombre = ?, fecha_n = ?, email = ?, psswrd = ? where dni like ?");
            stmt.setString(1, jugador.getNombre());
            stmt.setDate(2, jugador.getFechaNacimiento());
            stmt.setString(3, jugador.getEmail());
            stmt.setString(4, jugador.getPassword());
            stmt.setString(5, jugador.getDni());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al actualizar jugador: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    public void elimina(Connection con, Jugador jugador) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from jugador where dni like ?");
            stmt.setString(1, jugador.getDni());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al eliminar jugador: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    public void inserta(Connection con, Jugador jugador) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into jugador" +
                    "(dni,nombre,fecha_n,email,psswrd) " +
                    "values (?,?,?,?,?)");
            stmt.setString(1, jugador.getDni());
            stmt.setString(2, jugador.getNombre());
            stmt.setDate(3, jugador.getFechaNacimiento());
            stmt.setString(4, jugador.getEmail());
            stmt.setString(5, jugador.getPassword());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar jugador: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    private void obtenJugadorFila(ResultSet rs, Jugador jugador) throws SQLException {
        jugador.setDni(rs.getString("dni"));
        jugador.setNombre(rs.getString("nombre"));
        jugador.setFechaNacimiento(rs.getDate("fecha_n"));
        jugador.setEmail(rs.getString("email"));
        jugador.setPassword(rs.getString("psswrd"));
    }
    
    public Jugador findByDni(Connection con, Jugador jugador) throws Exception {
        Jugador _jugador = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from jugador where dni like ?");
            stmt.setString(1, jugador.getDni());
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                _jugador = new Jugador();
                obtenJugadorFila(rs, _jugador);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar jugador por dni: " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _jugador;
    }

    public List<Jugador> cargarJugadores(Connection con) throws Exception {
        List<Jugador> _listaJugadores = new ArrayList<Jugador>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from jugador");

            rs = stmt.executeQuery();
            Jugador _jugador = null;
            while (rs.next()) {
                _jugador = new Jugador();
                obtenJugadorFila(rs, _jugador);
                _listaJugadores.add(_jugador);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al cargar jugadores: " +
                    ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _listaJugadores;
    }
}
