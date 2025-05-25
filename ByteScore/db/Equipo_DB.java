package db;

import clases.Equipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class Equipo_DB {
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

    public void inserta(Connection con, Equipo equipo) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into equipo" +
                    "(id_equipo,nombre_e) " +
                    "values (?,?)");
            stmt.setInt(1, equipo.getCod());
            stmt.setString(2, equipo.getNombre());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar equipo: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    private void obtenEquipoFila(ResultSet rs, Equipo equipo) throws SQLException {
        equipo.setCod(rs.getInt("id_equipo"));
        equipo.setNombre(rs.getString("nombre_e"));
    }

    public Equipo findById(Connection con, Equipo equipo) throws Exception {
        Equipo _equipo = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from equipo where id_equipo = ?");
            stmt.setString(1, equipo.getNombre());

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

    public List<Equipo> cargarEquipos(Connection con) throws Exception {
        List<Equipo> _listaEquipos = new ArrayList<Equipo>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from equipo");

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
