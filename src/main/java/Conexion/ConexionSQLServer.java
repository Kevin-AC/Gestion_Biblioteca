package Conexion; // o el paquete que hayas elegido

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLServer {
    public static Connection conectar() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Biblioteca;encrypt=true;trustServerCertificate=true";
        String usuario = "sa";
        String contrasena = "Sqldocker2022";  // Cambia por tu contraseña real
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return conn;
    }
}
