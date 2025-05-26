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

public class Compite_E_DB {
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

    private void obtenCompiteFila(Connection con, ResultSet rs, Compite_E compite_e) throws Exception {
        Eliminatoria eli = new Eliminatoria();
        Equipo eq = new Equipo();
        eli.setNombre(rs.getString("nom_comp"));
        eq.setCod(rs.getInt("id_equipo"));
        compite_e.setCompeticion(
                (Eliminatoria) new Competicion_DB().findByNom(con, eli, new Eliminatoria()));
        compite_e.setEquipo(new Equipo_DB().findById(con, eq));
        compite_e.setNumRonda(rs.getInt("ronda"));
    }

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

    public List<Compite_E> cargarCompite(Connection con) throws Exception {
        List<Compite_E> _listaComp = new ArrayList<Compite_E>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from compite_e");

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
