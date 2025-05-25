/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

/**
 *
 * @author Emiliano
 */

import java.sql.Connection;
import java.sql.SQLException;

public class Conexion_DB {

    public Connection AbrirConexion() throws Exception {
        Connection con = null; // instacia una conexión
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
            String urlOdbc = "jdbc:mysql://localhost:3306/esports_completa";
            con = (java.sql.DriverManager.getConnection(urlOdbc, "root", "2309D2309d")); // crea conexión
            return con;
        } catch (Exception e) {// SQLException y ClassNotFoundException
            e.printStackTrace();
            throw new Exception("Ha sido imposible establecer la conexion" + e.getMessage());
        }
    }

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
