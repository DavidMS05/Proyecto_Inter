package db;

import clases.Compite_I;
import clases.Individual;
import clases.Jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 * Clase que interactúa con la tabla Compite_I.
 * @author Denys (3D)
 * @see clases.Compite_I
 */
public class Compite_I_DB {
    /**
     * Actualiza la indormación de una fila.
     * @param con conector
     * @param compite_i la fila actualizada
     * @throws Exception error de sql
     */
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
    
    /**
     * Elimina una fila.
     * @param con conector
     * @param compite_i la fila a eliminar
     * @throws Exception error de sql
     */
    public void elimina(Connection con, Compite_I compite_i) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from compite_i where nom_comp like ? and dni like ?");
            stmt.setString(1, compite_i.getCompeticion().getNombre());
            stmt.setString(2, compite_i.getJugador().getDni());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al eliminar compite_i: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    /**
     * Inserta una fila.
     * @param con conector
     * @param compite_i la fila a insertar
     * @throws Exception error de sql
     */
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
    
    /**
     * Carga la información de en ResultSet.
     * @param con conector para búsquedas internas
     * @param rs datos
     * @param compite_i objeto cargado
     * @throws Exception error de sql
     * @see java.sql.ResultSet
     */
    private void obtenCompiteFila(Connection con, ResultSet rs, Compite_I compite_i) throws Exception {
        Jugador jug = new Jugador();
        jug.setDni(rs.getString("dni"));
        compite_i.setCompeticion(
            (Individual) new Competicion_DB().findByNom(con, rs.getString("nom_comp"), new Individual()));
        compite_i.setJugador(new Jugador_DB().findByDni(con, jug));
        compite_i.setNumRonda(rs.getInt("ronda"));
    }
    
    /**
     * Busca fila por nombre de competición y DNI de jugador.
     * @param con conector
     * @param nom_comp nombre de competición
     * @param dni dni del jugador
     * @return objeto encontrado
     * @throws Exception error de sql
     * @see clases.Competicion
     * @see clases.Jugador
     */
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

    /**
     * Carga todas las filas.
     * @param con conector
     * @return lista de Compite_I
     * @throws Exception error de sql
     */
    public List<Compite_I> cargarCompite(Connection con) throws Exception {
        List<Compite_I> _listaComp = new ArrayList<Compite_I>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from compite_i order by ronda desc");

            rs = stmt.executeQuery();
            Compite_I _comp = null;
            while (rs.next()) {
                _comp = new Compite_I();
                obtenCompiteFila(con, rs, _comp);
                _listaComp.add(_comp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al cargar compite_i: " +
                    ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _listaComp;
    }
}
