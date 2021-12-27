package ar.edu.utn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParserCSV
{
    private String comentario;
    private List<String> lineasErroneas;
    private String separador;
    private List<String> columns;


    public ParserCSV(List<String> columns) {
        this.comentario = "#";
        this.separador = ";";
        this.lineasErroneas = null;
        this.columns = columns;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getSeparador() {
        return separador;
    }

    public void setSeparador(String separador) {
        this.separador = separador;
    }

    public ArrayList<Libro> leerArchivoLibro(String pathArchivoLibros){
        ArrayList<Libro> libros = new ArrayList<>();
        Path pathLibros = Paths.get(pathArchivoLibros);
        try (BufferedReader bufferLectura = Files.newBufferedReader(pathLibros)) {
            String linea = bufferLectura.readLine(); //Lee una linea
            while (linea != null) {     //La condicion hace que salga cuando ya no hay lineas que leer
                String[] registro = null;
                try {
                    registro = this.parseLine(linea);
                } catch (LineaInvalidaException e) {
                    this.lineasErroneas.add(linea);
                }
                if (registro != null) {
                    Libro libro = new Libro(Integer.parseInt(registro[0]), registro[1], registro[2], registro[3], Float.parseFloat(registro[4])); //Instancia un libro
                    libros.add(libro); // Agrega el libro a la coleccion.
                    linea = bufferLectura.readLine(); // Volver a leer otra línea
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return libros;
    }

    public String[] parseLine(String linea) throws LineaInvalidaException {
        String[] lineaArr = null;
        if (!linea.trim().startsWith(this.comentario)) {         // no comentario
            lineaArr = linea.split(this.separador);
            if (!(lineaArr.length == this.columns.size())) {
                throw new LineaInvalidaException(linea);
            }
        }
        return lineaArr;
    }

    public ArrayList<Lector> leerArchivoLectores(ArrayList<Libro> libros, String pathArchivoLectores) {
        ArrayList<Lector> lectores = new ArrayList<>();
        Path pathLectores = Paths.get(pathArchivoLectores);
        try (BufferedReader bufferLectura = Files.newBufferedReader(pathLectores)){
            String linea = bufferLectura.readLine();        //Lee una linea
            while (linea != null) {                         //La condicion hace que salga cuando ya no hay lineas que leer
                String[] registro = null;
                try {
                    registro = this.parseLine(linea);
                } catch (LineaInvalidaException e) {
                    this.lineasErroneas.add(linea);
                }
                if (registro != null) {
                    ArrayList<Libro> leidos = new ArrayList<>();
                    String[] codLibrosLeidos = registro[3].split("-");
                    for (int i = 0; i < codLibrosLeidos.length; i++) {   //Se inicia en 3 por que los id de los libros estan desde el campo 3 en adelante.
                        for(Libro libro: libros){               //Comparamos los codigos con codigos de libro para agregarlos a los leidos.
                            if(libro.getCod() == Integer.parseInt(codLibrosLeidos[i])){
                                leidos.add(libro);
                            }
                        }
                    }
                    Lector lector = new Lector(Integer.parseInt(registro[0]), registro[1], registro[2], leidos); // Instancia un lector
                    lectores.add(lector);                                                                  // Agrega el lector a la coleccion
                    linea = bufferLectura.readLine(); // Volver a leer otra línea
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lectores;
    }
}
