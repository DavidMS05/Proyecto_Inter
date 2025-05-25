package db;

import clases.Compite_E;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class Compite_E_DB {
    public void actualiza(Connection con, Compite_E compite_e) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update compite_e set nombre = ?");
            stmt.setString(1, "");
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al actualizar compite_e " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    public void elimina(Connection con, Compite_E compite_e) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from compite_e where dni = ?");
            stmt.setInt(1, 0);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al eliminar compite_e " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    public void inserta(Connection con, Compite_E compite_e) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into compite_e" +
                    "(dni, nombre, ape1, ape2, nick, passwd, saldo, moroso) " +
                    "values (?,?,?,?,?,?,?,?)");
            stmt.setInt(1, 0);
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar compite_e " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    private void obtenClienteFila(ResultSet rs, Compite_E compite_e) throws SQLException {
        
    }
    
    public Compite_E findByDni(Connection con, Compite_E compite_e) throws Exception {
        Compite_E _cliente = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from compite_e where dni = ?");
            stmt.setInt(1, 0);
            rs = stmt.executeQuery();
            while (rs.next()) {
                _cliente = new Compite_E();
                obtenClienteFila(rs, _cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar el compite_e por DNI " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _cliente;
    }
    
    public Compite_E findByNick(Connection con, Compite_E compite_e) throws Exception {
        Compite_E _cliente = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from compite_e where nick = ?");
            stmt.setString(1, "");
            rs = stmt.executeQuery();
            while (rs.next()) {
                _cliente = new Compite_E();
                obtenClienteFila(rs, _cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar el compite_e por nick " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _cliente;
    }
    
    public List<Compite_E> findByNumberDNIStart(Connection con, int numero) throws Exception {
        List<Compite_E> _listaClientes = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from compite_e where dni like ?");
            stmt.setString(1, numero+"%");
            rs = stmt.executeQuery();
            Compite_E _cliente = null;
            while (rs.next()) {
                _cliente = new Compite_E();
                obtenClienteFila(rs, _cliente);
                _listaClientes.add(_cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar el compite_e por dni start " +
                    ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _listaClientes;
    }
    
    public Compite_E findByMayorGasto(Connection con) throws Exception {
        Compite_E _cliente = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select cliente_dni as dni, (sum(precio*numero)) as gasto " +
                    "from articulo_factura af, articulo a, factura f " +
                    "where af.articulo_idarticulo=a.idarticulo " +
                    "and af.factura_idfactura=f.idfactura " +
                    "group by 1");
            
            rs = stmt.executeQuery();
            float _gastoAnterior = 0;
            while (rs.next()) {
                float gasto = rs.getFloat("gasto");
                if (gasto>_gastoAnterior) {
                    _cliente = new Compite_E();
                    //_cliente.setDni(rs.getInt("dni"));
                    _gastoAnterior = gasto;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar el compite_e por mayor gasto " +
                    ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        if (_cliente != null)
            _cliente = findByDni(con, _cliente);
        return _cliente;
    }
}
