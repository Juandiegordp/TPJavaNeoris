package ar.edu.utn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Main {

    public static ArrayList<Libro> archivoLibros(){
        String SEPARADOR = ";"; // EL separador del archivo CSV
        BufferedReader bufferLectura = null;
        ArrayList<Libro> libros = new ArrayList<Libro>();
        try {
            bufferLectura = new BufferedReader(new FileReader("src/main/resources/libros.csv")); //Carga en el buffer el .csv
            String linea = bufferLectura.readLine(); //Lee una linea

            while (linea != null) {     //Sale cuando ya no hay lineas que leer
                String[] campos = linea.split(SEPARADOR); // Sepapar la linea leída con el separador definido previamente
                Libro libro = new Libro(Integer.parseInt(campos[0]), campos[1], campos[2], campos[3], Integer.parseInt(campos[4]), Float.parseFloat(campos[5]));
                libros.add(Integer.parseInt(campos[0]),libro);
                linea = bufferLectura.readLine(); // Volver a leer otra línea

            }
            System.out.println("Los libros en la coleccion son: "+libros); //Muestra los libros con su id
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return libros;
    }

    public static ArrayList<Lector> archivoLectores() {
        String SEPARADOR = ";"; // EL separador del archivo CSV
        BufferedReader bufferLectura = null;
        ArrayList<Lector> lectores = new ArrayList<Lector>();
        try {
            bufferLectura = new BufferedReader(new FileReader("src/main/resources/lectores.csv")); //Carga en el buffer el .csv
            String linea = bufferLectura.readLine(); //Lee una linea
            while (linea != null) {     //Sale cuando ya no hay lineas que leer
                String[] campos = linea.split(SEPARADOR); // Sepapar la linea leída con el separador definido previamente
                ArrayList<Integer> leidos = new ArrayList<Integer>();
                for (int i = 3; i < campos.length; i++) {
                    leidos.add(Integer.parseInt(campos[i]));
                }
                Lector lector = new Lector(Integer.parseInt(campos[0]), campos[1], campos[2], leidos);
                lectores.add(Integer.parseInt(campos[0]),lector);
                linea = bufferLectura.readLine(); // Volver a leer otra línea

            }
            System.out.println("Los lectores son: " + lectores); //Muestra los lectores con su id
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lectores;
    }

    public static Map<Integer, ArrayList<Libro>> relacionArchivos(ArrayList<Lector> lectores, ArrayList<Libro>libros){
        Map<Integer, ArrayList<Libro>> lectorGenero = new HashMap<Integer, ArrayList<Libro>>(); //La key es el id del lector, Value es el conjunto de generos de los libros leidos
        for(Lector lector: lectores){  //Recorre los lectores
            ArrayList<Libro> generos = new ArrayList<Libro>(); //Guarda los generos asociados al lector
            for(Integer leido: lector.getLibrosLeidos()){  //Recorre los libros leidos
                Libro l = libros.get(leido);  //Guarda el libro correspondiente al id
                generos.add(l); //Agrega el genero del libro leido
                //lectorGenero.put(lector.getId(), generos); //Inserta en el map el id junto con los generos leidos
            }
            lectorGenero.put(lector.getId(), generos); //Inserta en el map el id junto con los generos leidos ESTO LO MOVI AFUERA
        }
        return lectorGenero;
    }

    public static int buscarId(String NyA, ArrayList<Lector> lectores){
        int id=50;
        for(Lector lector: lectores){
            if (NyA.equals(lector.getApellido() + " " +lector.getNombre())){
                id = lector.getId();
                break;
            }
        }
        return id;
    }

    public static String generoRecomendado(int id_lector, Map<Integer, ArrayList<Libro>> lectorGenero){
        String genFav = "";
        ArrayList<Libro> libros = new ArrayList<Libro>();
        ArrayList<String> generos = new ArrayList<String>();
        libros = lectorGenero.get(id_lector);
        for (Libro libro: libros){
            generos.add(libro.getGenero());
        }
        int [] frecuencia = new int [4];
        frecuencia[0] = Collections.frequency(generos, "FICCION Y LITERATURA");
        System.out.println(Collections.frequency(generos, "FICCION Y LITERATURA"));
        frecuencia[1] = Collections.frequency(generos, "MOTIVACION PERSONAL");
        frecuencia[2] = Collections.frequency(generos, "ESOTERISMO");
        frecuencia[3] = Collections.frequency(generos, "HUMANISMO");
        // aca va la carga de numeros con las frequencias
        int mayorFrec = frecuencia[0];
        int indice = 0;
        for (int x = 1; x < frecuencia.length; x++) {
            if (frecuencia[x] > mayorFrec) {
                mayorFrec = frecuencia[x];
                indice = x;
            }
        }
        //indice = 2 ;
        switch (indice){
            case 0: genFav = "FICCION Y LITERATURA";
            break;
            case 1: genFav = "MOTIVACION PERSONAL";
            break;
            case 2: genFav = "ESOTERISMO";
            break;
            case 3: genFav = "HUMANISMO";
            break;
        }
        return genFav;
    }

    public static Set<Libro> recomendar(int id_lector, ArrayList<Libro> libros, String genFavorito, Map<Integer, ArrayList<Libro>> lectorGenero){
        Set<Libro> librosRecomendados = new HashSet<Libro>();
        ArrayList<Libro> leidos = new ArrayList<Libro>();
        for(Libro libro: libros){
            if (genFavorito.equals(libro.getGenero())){
                librosRecomendados.add(libro);
            }
        }
        leidos = lectorGenero.get(id_lector);
        for(Libro libEliminar: leidos){
            librosRecomendados.remove(libEliminar);
        }
        return librosRecomendados;
    }


    public static void main(String[] args) throws IOException {
        String NyA = "Ruiz Juan";
        ArrayList<Libro> libros = new ArrayList<Libro>();
        libros=archivoLibros();                                 //ArrayList del archivo Libros.
        ArrayList<Lector> lectores = new ArrayList<Lector>();
        lectores=archivoLectores();                             //ArrayList del archivo lectores.
        Map<Integer, ArrayList<Libro>> lectorGenero = new HashMap<Integer, ArrayList<Libro>>(); //La key es el id del lector, Value es el conjunto de generos de los libros leidos
        lectorGenero=relacionArchivos(lectores,libros);         //Enlaza genero de libros a los lectores
        int id_lector = buscarId(NyA,lectores);                 //Busca el id del lector elegido.
        //System.out.println("El id es: " + id_lector);
        //System.out.println(lectorGenero);
        String generoFavorito = generoRecomendado(id_lector, lectorGenero);   //Busca el genero favorito
        System.out.println(generoFavorito);
        Set<Libro> recomendaciones = new HashSet<Libro>();
        recomendaciones = recomendar(id_lector,libros, generoFavorito, lectorGenero); //Obtiene los libros para recomendar
        System.out.println("Los libros recomendados son: ");
        for(Libro lib: recomendaciones){
            System.out.println("      "+lib.getNombre());
        }
        
    }
}
