package ar.edu.utn;

import java.util.ArrayList;

public class Lector {
    private int id;
    private String apellido;
    private String nombre;
    private ArrayList<Integer> librosLeidos;

    public Lector(int id, String apellido, String nombre, ArrayList<Integer> librosLeidos) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.librosLeidos = librosLeidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public ArrayList<Integer> getLibrosLeidos() {
        return librosLeidos;
    }

    public void setLibrosLeidos(ArrayList<Integer> librosLeidos) {
        this.librosLeidos = librosLeidos;
    }

    @Override
    public String toString() {
        return "Lector{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", librosLeidos=" + librosLeidos +
                '}';
    }
}
