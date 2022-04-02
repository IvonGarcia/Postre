/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Postre {

    public int id;
    public String nombre;
    public int cantidadIngredientes;
    public int piezas;
    public String imagen;

    private static Conexion cn = new Conexion();
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public Postre(int id, String nombre, int cantidadIngredientes, int piezas, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadIngredientes = cantidadIngredientes;
        this.piezas = piezas;
        this.imagen = imagen;
    }
    
    
    public Postre( String nombre, int cantidadIngredientes, int piezas, String imagen) {
      
        this.nombre = nombre;
        this.cantidadIngredientes = cantidadIngredientes;
        this.piezas = piezas;
        this.imagen = imagen;
    }

    public int lastId() {
        int id = -1;

        String sql = "select id_postre from postres order by id_postre desc";

        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error en el query: " + e.getMessage());
        }

        return id;
    }

    public int registrar(ArrayList<Ingrediente> ingredientes) {
        int r = 0;
        String sql = "insert into postres"
                + "(nombre, cantidad_ingredientes, piezas, imagen)"
                + " values(?,?,?,?)";
        try {

            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, this.nombre);
            ps.setObject(2, this.cantidadIngredientes);
            ps.setObject(3, this.piezas);
            ps.setObject(4, this.imagen);
            r = ps.executeUpdate();

            int id_postre = this.lastId();

            for (Ingrediente item : ingredientes) {
                sql = "insert into ingredientes_postres"
                        + "(id_postre, id_ingrediente)"
                        + " values(?,?)";
                ps = con.prepareStatement(sql);
                ps.setObject(1, id_postre);
                ps.setObject(2, item.id);
                r = ps.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("Error en: " + sql);
            System.out.println(e.getMessage());
        }
        return r;
    }
}
