/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase que conecta con la base de datos.
 * @author Emiliano
 */
public class Conexion_DB {
    /**
     * Abre conexión con la base de datos.
     * @return conector
     * @throws Exception error
     */
    public Connection AbrirConexion() throws Exception {
        Connection con = null; // instacia una conexión
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
            String urlOdbc = "jdbc:mysql://localhost:3306/bytescore";
            con = (java.sql.DriverManager.getConnection(urlOdbc, "root", "2309D2309d")); // crea conexión
            return con;
        } catch (Exception e) {// SQLException y ClassNotFoundException
            e.printStackTrace();
            throw new Exception("Ha sido imposible establecer la conexion" + e.getMessage());
        }
    }

    /**
     * Cierra conexión con la base de datos.
     * @param con conector
     * @throws Exception error
     */
    public void CerrarConexion(Connection con) throws Exception {

        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Ha sido imposible cerrar la conexion" + e.getMessage());
        }
    }
}
