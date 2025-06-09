package db;

import clases.Compite_E;
import clases.Eliminatoria;
import clases.Equipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 * Clase que interactúa con la tabla Compite_E.
 * @author Denys (3D)
 * @see clases.Compite_E
 */
public class Compite_E_DB {
    /**
     * Actualiza la información de una fila.
     * @param con conector
     * @param compite_e los datos nuevos de la fila
     * @throws Exception error de sql
     */
    public void actualiza(Connection con, Compite_E compite_e) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update compite_e set ronda = ? where nom_comp like ? and id_equipo = ?");
            stmt.setInt(1, compite_e.getNumRonda());
            stmt.setString(2, compite_e.getCompeticion().getNombre());
            stmt.setInt(3, compite_e.getEquipo().getCod());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al actualizar compite_e: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * Elimina una fila
     * @param con conector
     * @param compite_e la fila a eliminar
     * @throws Exception error de sql
     */
    public void elimina(Connection con, Compite_E compite_e) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from compite_e where nom_comp like ? and id_equipo = ?");
            stmt.setString(1, compite_e.getCompeticion().getNombre());
            stmt.setInt(2, compite_e.getEquipo().getCod());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al eliminar compite_e: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * Inserta una fila.
     * @param con conector
     * @param compite_e la fila a insertar
     * @throws Exception error de sql
     */
    public void inserta(Connection con, Compite_E compite_e) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into compite_e" +
                    "(nom_comp,id_equipo,ronda) " +
                    "values (?,?,?)");
            stmt.setString(1, compite_e.getCompeticion().getNombre());
            stmt.setInt(2, compite_e.getEquipo().getCod());
            stmt.setInt(3, compite_e.getNumRonda());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar compite_e: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * Obtiene los datos de la fila del ResultSet
     * @param con conector
     * @param rs datos
     * @param compite_e clase a llenar de datos
     * @throws Exception error de sql
     * @see java.sql.ResultSet
     */
    private void obtenCompiteFila(Connection con, ResultSet rs, Compite_E compite_e) throws Exception {
        Equipo eq = new Equipo();
        eq.setCod(rs.getInt("id_equipo"));
        compite_e.setCompeticion(
                (Eliminatoria) new Competicion_DB().findByNom(con, rs.getString("nom_comp"), new Eliminatoria()));
        compite_e.setEquipo(new Equipo_DB().findById(con, eq));
        compite_e.setNumRonda(rs.getInt("ronda"));
    }

    /**
     * Busca la fila por nombre de competición e id de equipo.
     * @param con conector
     * @param nom_comp nombre de la competición
     * @param id_equipo id del equipo
     * @return objeto de Compite_E
     * @throws Exception error de sql
     * @see clases.Competicion
     * @see clases.Equipo
     */
    public Compite_E findByNomId(Connection con, String nom_comp, int id_equipo) throws Exception {
        Compite_E _compite = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from compite_e where nom_comp like ? and id_equipo = ?");
            stmt.setString(1, nom_comp);
            stmt.setInt(2, id_equipo);

            rs = stmt.executeQuery();
            while (rs.next()) {
                _compite = new Compite_E();
                obtenCompiteFila(con, rs, _compite);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar el compite_e por nom e id: " + ex.getMessage());
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
     * @return lista de Compite_E
     * @throws Exception error de sql
     */
    public List<Compite_E> cargarCompite(Connection con) throws Exception {
        List<Compite_E> _listaComp = new ArrayList<Compite_E>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from compite_e order by ronda desc");

            rs = stmt.executeQuery();
            Compite_E _comp = null;
            while (rs.next()) {
                _comp = new Compite_E();
                obtenCompiteFila(con, rs, _comp);
                _listaComp.add(_comp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al cargar compite_e: " +
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
