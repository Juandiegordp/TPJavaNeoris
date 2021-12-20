package ar.edu.utn;

public class Libro {
    private Integer id;
    private String nombre;
    private String autor;
    private String genero;
    private int stock;
    private float precio;

    public Libro(int id, String nombre, String autor, String genero, int stock, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.genero = genero;
        this.stock = stock;
        this.precio = precio;
    }

    public Libro() {
        this.id = null;
        this.nombre = "";
        this.autor = "";
        this.genero = "";
        this.stock = 0;
        this.precio = 0;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", autor='" + autor + '\'' +
                ", genero='" + genero + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                '}';
    }
}
