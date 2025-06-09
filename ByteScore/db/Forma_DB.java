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

/**
 * Clase que interactúa con la tabla de Forma.
 * @author Denys (3D)
 * @see clases.Forma
 */
public class Forma_DB {
    /**
     * Actualiza una fila.
     * @param con conector
     * @param forma objeto actualizado
     * @throws Exception error de sql
     */
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
    
    /**
     * Elimina una fila.
     * @param con conector
     * @param forma objeto a eliminar
     * @return confirmación de éxito
     * @throws Exception error de sql
     */
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
    
    /**
     * Inserta una fila.
     * @param con conector
     * @param forma objeto a insertar
     * @throws Exception error de sql
     */
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
    
    /**
     * Carga los datos de un ResultSet.
     * @param con conector para búsquedas internas
     * @param rs datos
     * @param forma objeto a llenar
     * @throws Exception error de sql
     * @see java.sql.ResultSet
     */
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
    
    /**
     * Buscar fila por id de equipo y DNI de jugador.
     * @param con conector
     * @param forma objeto con los datos
     * @return objeto encontrado
     * @throws Exception error de sql
     */
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

    /**
     * Buscar por equipo.
     * @param con conector
     * @param equipo equipo a buscar
     * @return lista de jugadores
     * @throws Exception error de sql
     */
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
