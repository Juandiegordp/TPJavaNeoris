package ar.edu.utn;

import java.util.ArrayList;

public class Lector {
    private int cod;
    private String apellido;
    private String nombre;
    private ArrayList<Libro> librosLeidos; //Coleccion de libros leidos por el lector

    public Lector(int cod, String apellido, String nombre, ArrayList<Libro> librosLeidos) {
        this.cod = cod;
        this.apellido = apellido;
        this.nombre = nombre;
        this.librosLeidos = librosLeidos;
    }


    public String getApellido() {
        return apellido;
    }


    public String getNombre() {
        return nombre;
    }


    public ArrayList<Libro> getLibrosLeidos() {
        return librosLeidos;
    }


}
