package db;

import clases.Placa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Clase que interactúa con la tabla Placa.
 * @author Denys (3D)
 * @see clases.Placa
 * @deprecated
 */
@Deprecated
public class Placa_DB {
    /**
     * Actualiza una fila.
     * @param con conector
     * @param placa objeto actualizado
     * @throws Exception error de sql
     */
    public void actualiza(Connection con, Placa placa) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update placa set texto = ? where id = ?");
            stmt.setString(1, placa.getTexto());
            stmt.setInt(2, placa.getId());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al actualizar placa: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    /**
     * Elimina una fila.
     * @param con conector
     * @param placa objeto a eliminar
     * @throws Exception error de sql
     */
    public void elimina(Connection con, Placa placa) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from placa where id = ?");
            stmt.setInt(1, placa.getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al eliminar placa: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    /**
     * Inserta una fila.
     * @param con conector
     * @param placa objeto a insertar
     * @throws Exception error de sql
     */
    public void inserta(Connection con, Placa placa) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into placa" +
                    "(id,texto) " +
                    "values (?,?)");
            stmt.setInt(1, placa.getId());
            stmt.setString(2, placa.getTexto());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar placa: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    /**
     * Carga datos de un ResultSet.
     * @param rs datos
     * @param placa objeto a llenar
     * @throws SQLException error de sql
     * @see java.sql.ResultSet
     */
    private void obtenPlacaFila(ResultSet rs, Placa placa) throws SQLException {
        placa.setId(rs.getInt("id"));
        placa.setTexto(rs.getString("texto"));
    }
    
    /**
     * Busca por id.
     * @param con conector
     * @param placa objeto con id
     * @return objeto encontrado
     * @throws Exception error de sql
     */
    public Placa findById(Connection con, Placa placa) throws Exception {
        Placa _placa = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from placa where id = ?");
            stmt.setInt(1, placa.getId());
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                _placa = new Placa();
                obtenPlacaFila(rs, _placa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar placa por id: " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _placa;
    }
}
