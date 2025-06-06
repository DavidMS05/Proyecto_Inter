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

public class Competicion_DB {
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

    private void obtenCompeticionFila(Connection con, ResultSet rs, Competicion competicion) throws Exception {
        competicion.setNombre(rs.getString("nom_comp"));
        competicion.setfRealizacion(rs.getDate("f_realizacion"));
        competicion.setJuego(new Juego_DB().findByCod(con, rs.getInt("cod_juego")));
        competicion.setPremio(null);
    }

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

    public List<Liga> cargarLigas(Connection con) throws Exception {
        List<Liga> _listaLigas = new ArrayList<Liga>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from competicion where nom_comp like any(select nom_comp from liga)");
            rs = stmt.executeQuery();
            Liga _liga = null;
            while (rs.next()) {
                _liga = new Liga();
                obtenCompeticionFila(con, rs, _liga);
                _listaLigas.add(_liga);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al cargar ligas: " +
                    ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _listaLigas;
    }

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

    public List<Eliminatoria> cargarEliminatorias(Connection con) throws Exception {
        List<Eliminatoria> _listaEliminatorias = new ArrayList<Eliminatoria>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(
                    "select * from competicion where nom_comp like any(select nom_comp from eliminatoria)");
            rs = stmt.executeQuery();
            Eliminatoria _elim = null;
            while (rs.next()) {
                _elim = new Eliminatoria();
                obtenCompeticionFila(con, rs, _elim);
                _listaEliminatorias.add(_elim);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al cargar eliminatorias: " +
                    ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _listaEliminatorias;
    }

    public List<Individual> cargarIndividuales(Connection con) throws Exception {
        List<Individual> _listaIndividuales = new ArrayList<Individual>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(
                    "select * from competicion c where c.nom_comp in (select i.nom_comp from individual i)");
            rs = stmt.executeQuery();
            Individual _indiv = null;
            while (rs.next()) {
                _indiv = new Individual();
                obtenCompeticionFila(con, rs, _indiv);
                _listaIndividuales.add(_indiv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al cargar individuales: " +
                    ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return _listaIndividuales;
    }
}
