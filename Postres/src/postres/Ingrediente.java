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

public class Ingrediente {

    public int id;
    public String nombre;
    public String cantidad;
    public String fechaCaducidad;

    private static Connection con;
    private static Conexion cn = new Conexion();
    private static PreparedStatement ps;
    private static ResultSet rs;

    public Ingrediente(String nombre, String cantidad, String fechaCaducidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fechaCaducidad = fechaCaducidad;
    }

    public Ingrediente(int id, String nombre, String cantidad, String fechaCaducidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fechaCaducidad = fechaCaducidad;
    }

    public int registrar() {
        int r = 0;
        String sql = "insert into ingredientes"
                + "(nombre, cantidad, fecha_caducidad)"
                + " values(?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, this.nombre);
            ps.setObject(2, this.cantidad);
            ps.setObject(3, this.fechaCaducidad);
            r = ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error en: " + sql);
            System.out.println(e.getMessage());
        }
        return r;
    }

    public static ArrayList<Ingrediente> getIngredientes() {

        ArrayList<Ingrediente> lista = new ArrayList<>();

        String sql = "select id_ingrediente,nombre,cantidad,fecha_caducidad from ingredientes";

        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ingrediente item = new Ingrediente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                lista.add(item);
            }
        } catch (Exception e) {
            System.out.println("Error en el query: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public String toString() {
        return "Ingrediente: " + nombre + ", cantidad: " + cantidad;
    }

}
