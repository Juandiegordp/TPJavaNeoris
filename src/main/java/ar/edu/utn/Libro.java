package ar.edu.utn;

public class Libro {
    private Integer cod;
    private String nombre;
    private String autor;
    private String genero;
    private int stock;
    private float precio;

    public Libro(int cod, String nombre, String autor, String genero, int stock, float precio) {
        this.cod = cod;
        this.nombre = nombre;
        this.autor = autor;
        this.genero = genero;
        this.stock = stock;
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

    @Override
    public String toString() {
        return "Libro{" +
                "cod=" + cod +
                ", nombre='" + nombre + '\'' +
                ", autor='" + autor + '\'' +
                ", genero='" + genero + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                '}';
    }
}
