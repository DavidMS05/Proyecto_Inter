package db;

import clases.Competicion;
import clases.Liga;
import clases.Eliminatoria;
import clases.Individual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase que interactúa con las tablas Competicion, Eliminatoria, Liga e Individual.
 * @author Denys (3D)
 */
public class Competicion_DB {
    /**
     * Método que actualiza la información de una competición a la de la competición dada.
     * @param con conector
     * @param competicion la competición con la información actualizada
     * @throws Exception error de sql
     */
    public void actualiza(Connection con, Competicion competicion) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con
                    .prepareStatement("update competicion set f_realizacion = ?, cod_juego = ? where nom_comp like ?");
            stmt.setDate(1, new Date(competicion.getfRealizacion().getTime()));
            stmt.setInt(2, competicion.getJuego().getCod());
            stmt.setString(3, competicion.getNombre());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al actualizar competicion " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * Método que elimina una competición.
     * @param con conector
     * @param competicion la competición a eliminar (con el nombre igual)
     * @throws Exception error de sql
     */
    public void elimina(Connection con, Competicion competicion) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from competicion where nom_comp like ?");
            stmt.setString(1, competicion.getNombre());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al eliminar competicion " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * Método que inserta una competición nueva.
     * @param con conector
     * @param competicion la competición nueva
     * @throws Exception error de sql
     */
    public void inserta(Connection con, Competicion competicion) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into competicion" +
                    "(nom_comp,f_realizacion,cod_juego) " +
                    "values (?,?,?)");
            stmt.setString(1, competicion.getNombre());
            stmt.setDate(2, new Date(competicion.getfRealizacion().getTime()));
            stmt.setInt(3, competicion.getJuego().getCod());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar competicion: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * Método auxiliar para sacar los datos del ResultSet obtenido.
     * @param con conector, para hacer más busquedas dentro del método
     * @param rs la información obtenida
     * @param competicion la competición que se llenará con los datos
     * @throws Exception error de sql
     * @see java.sql.ResultSet
     */
    private void obtenCompeticionFila(Connection con, ResultSet rs, Competicion competicion) throws Exception {
        competicion.setNombre(rs.getString("nom_comp"));
        competicion.setfRealizacion(rs.getDate("f_realizacion"));
        competicion.setJuego(new Juego_DB().findByCod(con, rs.getInt("cod_juego")));
    }

    /**
     * Método que busca la competición por nombre (clave primaria).
     * @param con conector
     * @param nom nombre de la competición
     * @param resultado competición en la que se guardará el resultado
     * @return resultado no sé por qué pero lo retorno por si acaso
     * @throws Exception error de sql
     */
    public Competicion findByNom(Connection con, String nom, Competicion resultado) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from competicion where nom_comp like ?");
            stmt.setString(1, nom);

            rs = stmt.executeQuery();
            while (rs.next()) {
                obtenCompeticionFila(con, rs, resultado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar la competicion por nombre: " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return resultado;
    }

    /**
     * Método que confirma la existencia de una competición por su nombre (clave primaria).
     * @param con conector
     * @param nom nombre de la competición
     * @return booleano si lo ha encontrado
     * @throws Exception error de sql
     */
    public boolean findByNom(Connection con, String nom) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            stmt = con.prepareStatement("select * from competicion where nom_comp like ?");
            stmt.setString(1, nom);

            rs = stmt.executeQuery();
            while (rs.next()) {
                existe = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar la competicion por nombre: " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return existe;
    }

    /**
     * Devuelve el tipo de competición.
     * 1 = Eliminatoria
     * 2 = Liga
     * 3 = Individual
     * 0 = No encontrado
     * @param con conector
     * @param nombre nombre de la competición
     * @return byte con el resultado
     * @throws Exception error de sql
     */
    public byte findTipo(Connection con, String nombre) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        byte res = 0;
        try {
            stmt = con.prepareStatement("select * from eliminatoria where nom_comp like ?");
            stmt.setString(1, nombre);

            rs = stmt.executeQuery();
            while (rs.next()) {
                res = 1;
            }
            if (res == 0) {
                stmt.close();
                stmt = con.prepareStatement("select * from liga where nom_comp like ?");
                stmt.setString(1, nombre);

                rs = stmt.executeQuery();
                while (rs.next()) {
                    res = 2;
                }
                if (res == 0) {
                    stmt.close();
                    stmt = con.prepareStatement("select * from individual where nom_comp like ?");
                    stmt.setString(1, nombre);

                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        res = 3;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al buscar la competicion por nombre: " + ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return res;
    }

    /**
     * Carga la lista de competiciones.
     * @param con conector
     * @return lista de Competicion
     * @throws Exception error de sql
     * @see clases.Competicion
     */
    public List<Competicion> cargarTodos(Connection con) throws Exception {
        List<Competicion> _listaLigas = new ArrayList<Competicion>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from competicion");
            rs = stmt.executeQuery();
            Competicion _comp = null;
            List<String> e = new Eliminatoria_DB().cargarNombres(con);
            List<String> l = new Liga_DB().cargarNombres(con);
            List<String> i = new Individual_DB().cargarNombres(con);
            while (rs.next()) {
                String s = rs.getString("nom_comp");
                if (e.contains(s)) {
                    _comp = new Eliminatoria();
                } else if (l.contains(s)) {
                    _comp = new Liga();
                } else if (i.contains(s)) {
                    _comp = new Individual();
                } else {
                    throw new Exception("Competicion no registrada en ningun tipo");
                }
                obtenCompeticionFila(con, rs, _comp);
                _listaLigas.add(_comp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al cargar competiciones: " +
                    ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _listaLigas;
    }

}
