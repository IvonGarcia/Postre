package postres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {

    Connection con;
    
   
    
    String host = "127.0.0.1";
    String port = "3306";
    String database = "postres_db?characterEncoding=latin1&autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC";
    String user = "root";
    String pass = "";

    public Connection Conectar() {
        
         String url = "jdbc:mysql://"
                 + host
                 + ":"
                 + port
                 + "/"
                 + database
            + "?useTimezone=true&serverTimezone=UTC";
        
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fallo al conectar: "+e.getMessage());
        }
        return con;
    }
    
    public Connection Desconectar(){
        try {
            con.close();
        } catch (SQLException ex) {
            System.err.print(ex);
        }
        return null;
    }
    
}
