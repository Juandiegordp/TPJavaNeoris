package ar.edu.utn;
import java.util.*;

public class Recomendador {
    private Lector lector;
    private Set<Libro> recomendaciones;

    public Recomendador(Lector lector, ArrayList<Libro> libros) {
        this.lector = lector;
        this.recomendaciones = recomendar(libros); //Coleccion de libros recomendados para el lector.
    }

    public Lector getLector() {
        return lector;
    }

    public Set<Libro> getRecomendaciones() {
        return recomendaciones;
    }

    public Set<Libro> recomendar(ArrayList<Libro> libros){
        String genFavorito = generoRecomendado();           //Obtiene el genero recomendado
        Set<Libro> librosRecomendados = new HashSet<>();    //Coleccion para las recomendaciones.
        for(Libro libro: libros){                           //Revisa todos los libros que coincida genero y los guarda.
            if (genFavorito.equals(libro.getGenero())){
                librosRecomendados.add(libro);
            }
        }
        for(Libro libEliminar: this.lector.getLibrosLeidos()){  //Elimina los libros que ya fueron leidos del genero
            librosRecomendados.remove(libEliminar);
        }
        return librosRecomendados;
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
}
