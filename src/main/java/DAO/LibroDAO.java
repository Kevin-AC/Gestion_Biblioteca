package DAO;

import Conexion.ConexionSQLServer;
import Modelo.Libro;
import Modelo.LibroElectronico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LibroDAO {
    private Connection connection;
    public LibroDAO() {
        this.connection = ConexionSQLServer.conectar();
        if(this.connection == null){
            throw new RuntimeException("No se pudo conectar a la BD");
        }
    }

    public void agregarLibro(Libro libro) throws SQLException {
        String sql = "INSERT INTO Libros (titulo, autor, isbn, editorial, anioPublicacion, genero, formato, tamanoMB, urlDescarga) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getIsbn());
            stmt.setString(4, libro.getEditorial());
            stmt.setString(5, libro.getAnioPublicacion());
            stmt.setString(6, libro.getGenero());

            if (libro instanceof LibroElectronico libroElectronico) {
                stmt.setString(7, libroElectronico.getFormatoLibro());  // campo formatoLibro
                stmt.setDouble(8, libroElectronico.getTamanio());       // campo tamanio
                stmt.setString(9, libroElectronico.getUrl());           // campo urlDescarga
            } else {
                stmt.setNull(7, java.sql.Types.NVARCHAR);
                stmt.setNull(8, java.sql.Types.FLOAT);
                stmt.setNull(9, java.sql.Types.NVARCHAR);
            }

            stmt.executeUpdate();
        }
    }


}
