package db;

import clases.Juego;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 * Clase que interactúa con la tabla Juego.
 * @author Denys (3D)
 * @see clases.Juego
 */
public class Juego_DB {
    /**
     * Actualiza una fila.
     * @param con conector
     * @param juego objeto actualizado
     * @throws Exception error de sql
     */
    public void actualiza(Connection con, Juego juego) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update juego set nom_juego = ? where cod_juego = ?");
            stmt.setString(1, juego.getNombre());
            stmt.setInt(2, juego.getCod());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al actualizar juego: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    /**
     * Elimina una fila.
     * @param con conector
     * @param juego objeto a eliminar
     * @throws Exception error de sql
     */
    public void elimina(Connection con, Juego juego) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from juego where cod_juego = ?");
            stmt.setInt(1, juego.getCod());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al eliminar juego: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    /**
     * Inserta una fila.
     * @param con conector
     * @param juego objeto a insertar
     * @throws Exception error de sql
     */
    public void inserta(Connection con, Juego juego) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into juego" +
                    "(cod_juego,nom_juego) " +
                    "values (null,?)");
            stmt.setString(1, juego.getNombre());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar juego: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    /**
     * Carga datos de un ResultSet.
     * @param rs datos
     * @param juego objeto a llenar
     * @throws Exception error de sql
     * @see java.sql.ResultSet
     */
    private void obtenJuegoFila(ResultSet rs, Juego juego) throws Exception {
        juego.setCod(rs.getInt("cod_juego"));
        juego.setNombre(rs.getString("nom_juego"));
    }
    
    /**
     * Buscar fila por código.
     * @param con conector
     * @param cod código
     * @return objeto encontrado
     * @throws Exception error de sql
     */
    public Juego findByCod(Connection con, int cod) throws Exception {
        Juego _juego = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from juego where cod_juego = ?");
            stmt.setInt(1, cod);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                _juego = new Juego();
                obtenJuegoFila(rs, _juego);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar juego por cod: " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _juego;
    }

    /**
     * Buscar fila por nombre.
     * @param con conector
     * @param nom nombre del juego
     * @return objeto encontrado
     * @throws Exception error de sql
     */
    public Juego findByNom(Connection con, String nom) throws Exception {
        Juego _juego = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from juego where nom_juego like ?");
            stmt.setString(1, nom);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                _juego = new Juego();
                obtenJuegoFila(rs, _juego);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar juego por cod: " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _juego;
    }

    /**
     * Cargar todas las filas.
     * @param con conector
     * @return lista de juegos
     * @throws Exception error de sql
     */
    public List<Juego> cargar(Connection con) throws Exception {
        List<Juego> _listaJuegos = new ArrayList<Juego>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from juego order by nom_juego");

            rs = stmt.executeQuery();
            Juego _juego = null;
            while (rs.next()) {
                _juego = new Juego();
                obtenJuegoFila(rs, _juego);
                _listaJuegos.add(_juego);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al cargar juegos: " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _listaJuegos;
    }
}
