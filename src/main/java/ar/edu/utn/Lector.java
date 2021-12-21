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

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Libro> getLibrosLeidos() {
        return librosLeidos;
    }

    public void setLibrosLeidos(ArrayList<Libro> librosLeidos) {
        this.librosLeidos = librosLeidos;
    }

    @Override
    public String toString() {
        return "Lector{" +
                "cod=" + cod +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", librosLeidos=" + librosLeidos +
                '}';
    }
}
