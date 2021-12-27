package ar.edu.utn;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Main {

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

    public static void main(String[] args) throws IOException, NoHayRecomendaciones {
        ArgumentParser parser = ArgumentParsers.newFor("csvParser").build()
                .description("Ruta de archivos y tipo de recomendacion esperada");
        parser.addArgument("pathLibros")
                .type(String.class).required(true)
                .help("Ruta del archivo libros");
        parser.addArgument("pathLectores")
                .type(String.class).required(true)
                .help("Ruta del archivo lectores");
        parser.addArgument("pathRecomendaciones")
                .type(String.class).required(true)
                .help("Ruta del archivo recomendaciones");
        parser.addArgument("tipoRecomendador")
                .type(String.class).required(true).choices("Aleatorio", "Genero")
                .help("Tipo de recomendacion");

        Namespace res = null;
        try {
            res = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.printHelp();
            //log.error("error ejecutando el comando",e);
            System.exit(1);
        }


        ParserCSV archivoLibro = new ParserCSV(Arrays.asList(new String[]{"Cod", "Nombre del libro", "Autor", "Genero", "Precio"}));
        ArrayList<Libro>  libros = archivoLibro.leerArchivoLibro(res.getString("pathLibros"));
        ParserCSV archivoLectores = new ParserCSV(Arrays.asList(new String[]{"Codigo", "Apellido", "Nombre", "Libros Leidos"}));
        ArrayList<Lector>  lectores = archivoLectores.leerArchivoLectores(libros, res.getString("pathLectores"));
        ArrayList<Recomendador> recomendaciones = new ArrayList<>();    //Coleccion de recomendaciones.

        for (Lector lector : lectores) {                                  //Recorre los lectores para hacer la recomendacion a cada uno.
            Recomendador recomienda = new Recomendador(lector, libros); //Genera la recomendacion.
            if (res.getString("tipoRecomendador").equals("Genero")) {
                try{
                    recomienda.recomendarGenero(libros);
                }catch (NoHayRecomendaciones e){
                    System.out.println("No hay recomendaciones");
                }

            }else if(res.getString("tipoRecomendador").equals("Aleatorio")){
                recomienda.recomendarAleatorio(libros);
            }else {
                System.out.println("Se ingreso el tipo de recomendacion de forma erronea");
            }
            recomendaciones.add(recomienda);                            //Agrega la recomendacion a la coleccion.
        }
        imprimirRecomendaciones(recomendaciones, res.getString("pathRecomendaciones"));                       //Vuelca las recomendaciones a un archivo.*/
    }
}
