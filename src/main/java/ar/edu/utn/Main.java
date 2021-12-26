package ar.edu.utn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Main {

    public static ArrayList<Libro> archivoLibros(String pathArchivoLibros){
        String SEPARADOR = ";"; // Indica el simbolo utilizado como separador en el archivo CSV
        BufferedReader bufferLectura;
        ArrayList<Libro> libros = new ArrayList<>();
        try {
            bufferLectura = new BufferedReader(new FileReader(pathArchivoLibros)); //Carga en el buffer el .csv
            String linea = bufferLectura.readLine(); //Lee una linea
            while (linea != null) {     //La condicion hace que salga cuando ya no hay lineas que leer
                String[] campos = linea.split(SEPARADOR); // Separa la linea leída con el separador definido previamente
                Libro libro = new Libro(Integer.parseInt(campos[0]), campos[1], campos[2], campos[3], Float.parseFloat(campos[4])); //Instancia un libro
                libros.add(libro); // Agrega el libro a la coleccion.
                linea = bufferLectura.readLine(); // Volver a leer otra línea

            }
            bufferLectura.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return libros;
    }

    public static ArrayList<Lector> archivoLectores(ArrayList<Libro>  libros, String pathArchivoLectores) {
        String SEPARADOR = ";";          // Indica el simbolo utilizado como separador en el archivo CSV
        BufferedReader bufferLectura;
        ArrayList<Lector> lectores = new ArrayList<>();
        try {
            bufferLectura = new BufferedReader(new FileReader(pathArchivoLectores)); //Carga en el buffer el .csv
            String linea = bufferLectura.readLine();        //Lee una linea
            while (linea != null) {                         //La condicion hace que salga cuando ya no hay lineas que leer
                String[] campos = linea.split(SEPARADOR);   // Separa la linea leída con el separador definido previamente
                ArrayList<Libro> leidos = new ArrayList<>();
                for (int i = 3; i < campos.length; i++) {   //Se inicia en 3 por que los id de los libros estan desde el campo 3 en adelante.
                    for(Libro libro: libros){               //Comparamos los codigos con codigos de libro para agregarlos a los leidos.
                        if(libro.getCod() == Integer.parseInt(campos[i])){
                            leidos.add(libro);
                        }
                    }
                }
                Lector lector = new Lector(Integer.parseInt(campos[0]), campos[1], campos[2], leidos); // Instancia un lector
                lectores.add(lector);                                                                  // Agrega el lector a la coleccion
                linea = bufferLectura.readLine(); // Volver a leer otra línea

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lectores;
    }

    public static void imprimirRecomendaciones(ArrayList<Recomendador> recomendaciones, String pathArchivoRecomendaciones) throws IOException{
        FileWriter csvWriter = new FileWriter(pathArchivoRecomendaciones);
        for(Recomendador recomendacion: recomendaciones){
            csvWriter.append("Lector: " + recomendacion.getLector().getApellido() + ", "+ recomendacion.getLector().getNombre() + "\n");        //Vuelca NyA del lector
            csvWriter.append("Recomendaciones: \n");
            for(Libro librosRecomendados: recomendacion.getRecomendaciones()){
                csvWriter.append("      " + librosRecomendados.getNombre() + "   y su precio es: " + librosRecomendados.getPrecio() +"\n" );    //Vuelca las recomendaciones
            }
            csvWriter.append("\n");
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Libro>  libros = archivoLibros(args[0]);                     //Carga los libros.
        ArrayList<Lector> lectores = archivoLectores(libros,args[1]);           //Carga los lectores.
        ArrayList<Recomendador> recomendaciones = new ArrayList<>();    //Coleccion de recomendaciones.

        for (Lector lector : lectores) {                                  //Recorre los lectores para hacer la recomendacion a cada uno.
            Recomendador recomienda = new Recomendador(lector, libros); //Genera la recomendacion.
            if (args[3].equals("Genero")) {
                recomienda.recomendarGenero(libros);
            }else if(args[3].equals("Aleatorio")){
                recomienda.recomendarAleatorio(libros);
            }else {
                System.out.println("Se ingreso el tipo de recomendacion de forma erronea");
            }
            recomendaciones.add(recomienda);                            //Agrega la recomendacion a la coleccion.
        }
        imprimirRecomendaciones(recomendaciones, args[2]);                       //Vuelca las recomendaciones a un archivo.*/
    }
}
