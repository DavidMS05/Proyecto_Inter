package db;

import clases.Equipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 * Clase que interactúa con la tabla de Equipo.
 * @author Denys (3D)
 * @see clases.Equipo
 */
public class Equipo_DB {
    /**
     * Actualiza una fila.
     * @param con conector
     * @param equipo fila actualizada
     * @throws Exception error de sql
     */
    public void actualiza(Connection con, Equipo equipo) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update equipo set nombre_e = ? where id_equipo = ?");
            stmt.setString(1, equipo.getNombre());
            stmt.setInt(2, equipo.getCod());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al actualizar equipo: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * Elimina una fila.
     * @param con conector
     * @param equipo fila a eliminar
     * @throws Exception error de sql
     */
    public void elimina(Connection con, Equipo equipo) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from equipo where id_equipo = ?");
            stmt.setInt(1, equipo.getCod());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al eliminar equipo: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * Inserta una fila.
     * @param con conector
     * @param equipo fila a insertar
     * @throws Exception error de sql
     */
    public void inserta(Connection con, Equipo equipo) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into equipo" +
                    "(id_equipo,nombre_e) " +
                    "values (null,?)");
            stmt.setString(1, equipo.getNombre());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar equipo: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * Carga datos de un ResultSet.
     * @param rs datos
     * @param equipo objeto a llenar
     * @throws SQLException error de sql
     * @see java.sql.ResultSet
     */
    private void obtenEquipoFila(ResultSet rs, Equipo equipo) throws SQLException {
        equipo.setCod(rs.getInt("id_equipo"));
        equipo.setNombre(rs.getString("nombre_e"));
    }

    /**
     * Buscar fila por id de equipo.
     * @param con conector
     * @param equipo objeto con el id a buscar
     * @return objeto encontrado
     * @throws Exception error de sql
     */
    public Equipo findById(Connection con, Equipo equipo) throws Exception {
        Equipo _equipo = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from equipo where id_equipo = ?");
            stmt.setInt(1, equipo.getCod());

            rs = stmt.executeQuery();
            while (rs.next()) {
                _equipo = new Equipo();
                obtenEquipoFila(rs, _equipo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar equipo por id: " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _equipo;
    }

    /**
     * Buscar fila por nombre de equipo.
     * @param con conector
     * @param nom nombre de equipo
     * @return objeto encontrado
     * @throws Exception error de sql
     */
    public Equipo findByNom(Connection con, String nom) throws Exception {
        Equipo _equipo = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from equipo where nombre_e like ?");
            stmt.setString(1, nom);

            rs = stmt.executeQuery();
            while (rs.next()) {
                _equipo = new Equipo();
                obtenEquipoFila(rs, _equipo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar equipo por id: " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _equipo;
    }

    /**
     * Carga todas las filas.
     * @param con conector
     * @return lista de Equipo
     * @throws Exception error de sql
     */
    public List<Equipo> cargarEquipos(Connection con) throws Exception {
        List<Equipo> _listaEquipos = new ArrayList<Equipo>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from equipo order by nombre_e");

            rs = stmt.executeQuery();
            Equipo _liga = null;
            while (rs.next()) {
                _liga = new Equipo();
                obtenEquipoFila(rs, _liga);
                _listaEquipos.add(_liga);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al cargar equipos: " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _listaEquipos;
    }
}
