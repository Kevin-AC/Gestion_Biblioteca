package edu.iudigital;

import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int opcion =0;
        while (opcion != 9){
            mostarMenu();
            try {
                opcion= scanner.nextInt();
                scanner.nextLine();
                switch (opcion){
                    case 1:
                        System.out.println("biblioteca");
                        break;
                    case 2:
                        System.out.println("usuario");
                        break;
                    case 3:
                        System.out.println("libro");
                        break;
                    case 4:
                        System.out.println("lista libros");
                }

            }catch (InputMismatchException e){
                System.out.println("Error: Caracter no valido");
                scanner.nextLine();

            }
        }

    }
    public void mostarMenu(){
        System.out.println("Bienvenido a la biblioteca ----");
        System.out.println("1. biblioteca");
        System.out.println("2. usuarios");
        System.out.println("3.LIBROS");
        System.out.println("4.lista de  libros");
        System.out.println("9. salir");
    }
}