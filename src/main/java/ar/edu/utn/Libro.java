package ar.edu.utn;

public class Libro {
    private Integer cod;
    private String nombre;
    private String autor;
    private String genero;
    private float precio;

    public Libro(int cod, String nombre, String autor, String genero, float precio) {
        this.cod = cod;
        this.nombre = nombre;
        this.autor = autor;
        this.genero = genero;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }


    public String getGenero() {
        return genero;
    }

    public float getPrecio() {
        return precio;
    }


    public int getCod() {
        return cod;
    }

}
