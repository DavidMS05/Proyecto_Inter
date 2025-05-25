package clases;

import java.sql.Connection;

import db.Conexion_DB;

public class TestConnector {
    public static void main(String[] args) throws Exception {
        Conexion_DB _conexion_DB = new Conexion_DB();
        Connection _con = null;
        try
        {
            _con = _conexion_DB.AbrirConexion();// Abrimos la conexión
            System.out.println("Conexion abierta");
            _con.setAutoCommit(false);
            _con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            //programa
            
            // fin programa
            _con.commit();
        } catch (Exception ex) {
            System.out.println("Exception -> " + ex.getMessage());
            if (_con != null)
                _con.rollback();
        } finally {
            if (_con != null)
                _conexion_DB.CerrarConexion(_con);
            System.out.println("Conexion cerrada");
        }
    }
}
