/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author roscr
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class ConexionBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/Tienda_Pequenia";
    private static final String USER = "postgres"; // Cambia por tu usuario
    private static final String PASSWORD = "ATLAS2010"; // Cambia por tu contraseña

    public static Connection getConnection() {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL,USER,PASSWORD);

            if(conn!=null){
                System.out.println("Conexion exitosa");
            }else{
                System.out.println("Conexion fallida");
            }

        }catch (Exception e){
            System.out.println("Error en la conexion");
        }
        return conn;
    }
}
