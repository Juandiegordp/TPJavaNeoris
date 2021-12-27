package ar.edu.utn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Recomendador {

    final static Logger log = LoggerFactory.getLogger(Recomendador.class);

    private Lector lector;
    private ArrayList<Libro> recomendaciones;

    public Recomendador(Lector lector, ArrayList<Libro> libros) {
        this.lector = lector;
        this.recomendaciones = new ArrayList<Libro>(); //Coleccion de libros recomendados para el lector.
    }

    public Lector getLector() {
        return lector;
    }

    public ArrayList<Libro> getRecomendaciones() {
        return recomendaciones;
    }

    public void recomendarGenero(ArrayList<Libro> libros) throws NoHayRecomendaciones{
        log.debug("Inicia proceso de recomendacion");
        String genFavorito = generoRecomendado();           //Obtiene el genero recomendado
        ArrayList<Libro> librosRecomendados = new ArrayList<>();    //Coleccion para las recomendaciones.
        for(Libro libro: libros){                           //Revisa todos los libros que coincida genero y los guarda.
            if (genFavorito.equals(libro.getGenero())){
                librosRecomendados.add(libro);
            }
        }
        for(Libro libEliminar: this.lector.getLibrosLeidos()){  //Elimina los libros que ya fueron leidos del genero
            librosRecomendados.remove(libEliminar);
        }
        if (librosRecomendados.isEmpty()){
            String rta = ("El lector: " + this.lector.getApellido() + ", " + this.lector.getNombre() + " no posee recomendaciones");
            log.error("El lector " + this.lector.getApellido() + ", " + this.lector.getNombre() + " no posee recomendaciones.");
            throw new NoHayRecomendaciones(rta);
        }else{
            setRecomendaciones(librosRecomendados);
            log.info("Se obtuvieron las recomendaciones por genero para el lector " + this.lector.getApellido() + ", " + this.lector.getNombre() + " con exito.");
        }
    }

    public void recomendarAleatorio(ArrayList<Libro> libros){
        log.debug("Inicia proceso de recomendacion");
        ArrayList<Libro> librosRecomendados = new ArrayList<>();    //Coleccion para las recomendaciones.
        for(Libro libro: libros){                           //Revisa todos los libros que coincida genero y los guarda.
            librosRecomendados.add(libro);
        }
        for(Libro libEliminar: this.lector.getLibrosLeidos()){  //Elimina los libros que ya fueron leidos del genero
            librosRecomendados.remove(libEliminar);
        }
        int random = new Random().nextInt(librosRecomendados.size());
        Libro libRecomendado = librosRecomendados.get(random);
        librosRecomendados.clear();
        librosRecomendados.add(libRecomendado);
        setRecomendaciones(librosRecomendados);
        log.info("Se obtuvo la recomendacion para el lector " + this.lector.getApellido() + ", " + this.lector.getNombre() + " con exito.");
    }

    public String generoRecomendado(){
        String genFav = "";
        ArrayList<String> generos = new ArrayList<>();      //Coleccion para guardar los generos de cada libro que leyo el lector.
        for (Libro libro: this.lector.getLibrosLeidos()){
            generos.add(libro.getGenero());                 //Agrega el genero del libro.
        }
        int [] frecuencia = new int [4];                    //Vector que guarda la frecuencia de cada uno de los generos
        frecuencia[0] = Collections.frequency(generos, "FICCION Y LITERATURA"); //Se le da un arreglo y un string, y devuelve la cantidad de veces que se reptie
        frecuencia[1] = Collections.frequency(generos, "MOTIVACION PERSONAL");
        frecuencia[2] = Collections.frequency(generos, "ESOTERISMO");
        frecuencia[3] = Collections.frequency(generos, "HUMANISMO");
        // aca va la carga de numeros con las frequencias
        int mayorFrec = frecuencia[0];  //Variable auxiliar para obtener el genero de mayor frecuencia
        int indice = 0;                 //Variable auxiliar para obtener el indice del genero de mayor frecuencia
        for (int x = 1; x < frecuencia.length; x++) {   //Comparativa
            if (frecuencia[x] > mayorFrec) {
                mayorFrec = frecuencia[x];
                indice = x;
            }
        }
        switch (indice){                //Revisa el genero que mayor frecuencia tuvo y lo asigna como favorito
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

    public void setRecomendaciones(ArrayList<Libro> recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

}

