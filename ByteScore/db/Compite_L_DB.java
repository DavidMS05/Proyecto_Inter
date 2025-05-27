package db;

import clases.Compite_L;
import clases.Liga;
import clases.Equipo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class Compite_L_DB {
    public void actualiza(Connection con, Compite_L compite_l) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update compite_l set f_fin = ?, posicion = ? where nom_comp like ? and id_equipo = ?");
            stmt.setDate(1, new Date(compite_l.getfFin().getTime()));
            stmt.setInt(2, compite_l.getPosicion());
            stmt.setString(2, compite_l.getCompeticion().getNombre());
            stmt.setInt(3, compite_l.getEquipo().getCod());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al actualizar compite_l: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    public void elimina(Connection con, Compite_L compite_l) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from compite_l where nom_comp like ? and id_equipo = ?");
            stmt.setString(1, compite_l.getCompeticion().getNombre());
            stmt.setInt(2, compite_l.getEquipo().getCod());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al eliminar compite_l: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    public void inserta(Connection con, Compite_L compite_l) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into compite_l" +
                    "(nom_comp,id_equipo,f_fin,posicion) " +
                    "values (?,?,?,?)");
            stmt.setString(1, compite_l.getCompeticion().getNombre());
            stmt.setInt(2, compite_l.getEquipo().getCod());
            stmt.setDate(3, new Date(compite_l.getfFin().getTime()));
            stmt.setInt(4, compite_l.getPosicion());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar compite_l: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    private void obtenCompiteFila(Connection con, ResultSet rs, Compite_L compite_l) throws Exception {
        Equipo eq = new Equipo();
        eq.setCod(rs.getInt("id_equipo"));
        compite_l.setCompeticion(
            (Liga) new Competicion_DB().findByNom(con, rs.getString("nom_comp"), new Liga()));
        compite_l.setEquipo(new Equipo_DB().findById(con, eq));
        compite_l.setfFin(rs.getDate("f_fin"));
        compite_l.setPosicion(rs.getInt("posicion"));
    }
    
    public Compite_L findByNomId(Connection con, String nom_comp, int id_equipo) throws Exception {
        Compite_L _compite = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from compite_l where nom_comp like ? and id_equipo = ?");
            stmt.setString(1, nom_comp);
            stmt.setInt(2, id_equipo);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                _compite = new Compite_L();
                obtenCompiteFila(con, rs, _compite);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar el compite_l por nom e id: " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _compite;
    }

    public List<Compite_L> cargarCompite(Connection con) throws Exception {
        List<Compite_L> _listaComp = new ArrayList<Compite_L>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from compite_l");

            rs = stmt.executeQuery();
            Compite_L _comp = null;
            while (rs.next()) {
                _comp = new Compite_L();
                obtenCompiteFila(con, rs, _comp);
                _listaComp.add(_comp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al cargar compite_l: " +
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
