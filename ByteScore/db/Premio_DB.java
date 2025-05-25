package db;

import clases.Competicion;
import clases.Eliminatoria;
import clases.Liga;
import clases.Placa;
import clases.Individual;
import clases.Premio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Premio_DB {
    public void actualiza(Connection con, Premio premio) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update premio set p_metalico = ?, placa = ?, nom_comp = ? where nombre_p like ?");
            stmt.setFloat(1, premio.getP_metalico());
            stmt.setInt(2, premio.getPlaca().getId());
            stmt.setString(3, premio.getCompeticion().getNombre());
            stmt.setString(4, premio.getNombre_p());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al actualizar premio: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    public void elimina(Connection con, Premio premio) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from premio where nombre_p like ?");
            stmt.setString(1, premio.getNombre_p());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al eliminar premio: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    public void inserta(Connection con, Premio premio) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into premio" +
                    "(nombre_p,p_metalico,placa,nom_comp) " +
                    "values (?,?,?,?)");
            stmt.setString(1, premio.getNombre_p());
            stmt.setFloat(2, premio.getP_metalico());
            stmt.setInt(3, premio.getPlaca().getId());
            stmt.setString(4, premio.getCompeticion().getNombre());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar premio: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    private void obtenPlacaFila(Connection con, ResultSet rs, Premio premio) throws Exception {
        Competicion_DB com = new Competicion_DB();
        String nom = rs.getString("nom_comp");
        int tipo = com.findTipo(con, nom);
        Competicion c = switch(tipo) {
            case 1 -> new Eliminatoria();
            case 2 -> new Liga();
            case 3 -> new Individual();
            default -> null;
        };
        Placa p = new Placa();
        p.setId(rs.getInt("placa"));
        premio.setNombre_p(rs.getString("nombre_p"));
        premio.setP_metalico(rs.getFloat("p_metalico"));
        premio.setPlaca(new Placa_DB().findById(con, p));
        premio.setCompeticion(new Competicion_DB().findByNom(con, c, c));
    }
    
    public Premio findByNom(Connection con, Premio premio) throws Exception {
        Premio _placa = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from premio where nombre_p like ?");
            stmt.setString(1, premio.getNombre_p());
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                _placa = new Premio();
                obtenPlacaFila(con, rs, _placa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar premio por nom: " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _placa;
    }
}
