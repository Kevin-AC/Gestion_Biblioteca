package Service;

import Modelo.Biblioteca;
import Modelo.Usuario;

import java.util.Scanner;

public class UsuarioService {
    private Scanner scanner;
    private Biblioteca biblioteca;
    public UsuarioService(Scanner scanner, Biblioteca biblioteca) {
        this.scanner = scanner;
        this.biblioteca = biblioteca;
    }

    public void crearUsuario(){
        try {

            String nombre = validarNombre();

            String cedula;
            while (true) {
                System.out.println("Ingrese su Cedula");
                cedula = scanner.nextLine();
                if (!cedula.matches("[0-9]+")) {
                    System.out.println("Error: La cedula solo puede contener numeros. Intente de nuevo.");
                } else {
                    break;
                }
            }
            System.out.println("Ingrese el Correo");
            String correo = scanner.nextLine();
            String telefono;
            while (true) {
                System.out.println("Ingrese el Telefono");
                telefono = scanner.nextLine();
                if (!telefono.matches("[0-9]+")) {
                    System.out.println("Error: El telefono solo puede contener numeros. Intente de nuevo.");
                } else {
                    break;
                }
            }

            Usuario usuario = new Usuario(nombre,cedula,correo,telefono);
            biblioteca.agregarUsuario(usuario);
            System.out.println("Usuario creado con exito");

        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());

        }

    }
    public String validarNombre(){
        String nombre;
        while (true) {
            System.out.println("Ingrese el nombre de Usuario");
            nombre = scanner.nextLine();
            if (!nombre.matches("^[\\p{L} ]+$")) {
                System.out.println("Error: El nombre solo puede contener letras y espacios. Intente de nuevo.");
            } else {
                break;
            }
        }
        return nombre;
    }
}
