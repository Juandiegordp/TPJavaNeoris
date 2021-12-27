package ar.edu.utn;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Main {

    final static Logger log = LoggerFactory.getLogger(Main.class);

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
            log.error("Algun/os de los argumentos son erroneos",e);
            System.exit(1);
        }

        log.debug("Se inicia lectura del archivo de libros...");
        ParserCSV archivoLibro = new ParserCSV(Arrays.asList(new String[]{"Cod", "Nombre del libro", "Autor", "Genero", "Precio"}));
        ArrayList<Libro>  libros = archivoLibro.leerArchivoLibro(res.getString("pathLibros"));
        log.info("Lectura exitosa");
        log.debug("Se inicia lectura del archivo lector...");
        ParserCSV archivoLectores = new ParserCSV(Arrays.asList(new String[]{"Codigo", "Apellido", "Nombre", "Libros Leidos"}));
        ArrayList<Lector>  lectores = archivoLectores.leerArchivoLectores(libros, res.getString("pathLectores"));
        log.info("Lectura exitosa");
        ArrayList<Recomendador> recomendaciones = new ArrayList<>();    //Coleccion de recomendaciones.
        log.debug("Inicia creacion de recomendaciones..");
        for (Lector lector : lectores) {                                  //Recorre los lectores para hacer la recomendacion a cada uno.
            Recomendador recomienda = new Recomendador(lector, libros); //Genera la recomendacion.
            if (res.getString("tipoRecomendador").equals("Genero")) {
                try{
                    recomienda.recomendarGenero(libros);
                }catch (NoHayRecomendaciones e){
                    log.error("Fallo en la recomendacion");
                    System.out.println("No hay recomendaciones");
                }

            }else if(res.getString("tipoRecomendador").equals("Aleatorio")){
                recomienda.recomendarAleatorio(libros);
            }
            recomendaciones.add(recomienda);                            //Agrega la recomendacion a la coleccion.
        }
        imprimirRecomendaciones(recomendaciones, res.getString("pathRecomendaciones"));                       //Vuelca las recomendaciones a un archivo.*/
        log.info("El archivo recomendaciones fue creado con exito");
    }
}
