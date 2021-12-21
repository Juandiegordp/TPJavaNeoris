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

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public int getStock() {
        return stock;
    }

    public float getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int id) {
        this.cod = id;
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
