import ar.edu.utn.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class LectisTest {
    private ArrayList<Libro> leidos;
    private Libro libro1;
    private Libro libro2;
    private Libro libro3;
    private Lector lector;
    private Recomendador recom;
    private ArrayList<Libro> libros; //todos los libros
    private Libro libro4;
    private ParserCSV parser;
    private ParserCSV lectores;
    private Recomendador noRecom;
    private Lector lector2;

    @Before
    public void setUp(){
        this.libro1 = new Libro(11,"TE REGALO LO QUE SE TE ANTOJE","MENDEZ,CONNY","ESOTERISMO",458);
        this.libro2 = new Libro(13,"INTELIGENCIA PLANETARIA","CARUTTI,EUGENIO","ESOTERISMO",125);
        this.libro3 = new Libro(5,"EL ARTE DE MANTENER LA CALMA","SENECA,LUICIUS","MOTIVACION PERSONAL",45);
        this.leidos = new ArrayList<>();
        this.leidos.add(libro1);
        this.leidos.add(libro2);
        this.leidos.add(libro3);
        this.lector = new Lector(120,"JUAREZ","MARIA FLORENCIA",leidos);
        this.libro4 = new Libro(10,"MUCHAS VIDAS,MUCHOS MAESTROS","WIESS, BRIAN","ESOTERISMO",214);
        this.libros = new ArrayList<>();
        this.libros.add(libro1);
        this.libros.add(libro2);
        this.libros.add(libro3);
        this.libros.add(libro4);
        this.recom = new Recomendador(this.lector,this.libros);
        this.parser = new ParserCSV(Arrays.asList(new String[]{"Cod", "Nombre del libro", "Autor", "Genero", "Precio"}));
        this.lectores = new ParserCSV(Arrays.asList(new String[]{"Codigo", "Apellido", "Nombre", "Libros Leidos"}));
        this.lector2 = new Lector(125,"Ruiz","Francisco",libros);
        this.noRecom = new Recomendador(this.lector2, this.libros);



    }

    @Test
    public void testGeneroRecomendadoOk() {
        assertEquals("Recomendo mal","ESOTERISMO",recom.generoRecomendado());
    }

    @Test
    public void testRecomendacionPorGeneroOk() throws NoHayRecomendaciones {
        ArrayList<Libro>recomEsperada = new ArrayList<>();
        recomEsperada.add(libro4);
        recom.recomendarGenero(libros);
        assertEquals("Fallo",recomEsperada,recom.getRecomendaciones());
    }

    @Test
    public void testLineaLibroOk() throws Exception {
        String linea = "11;TE REGALO LO QUE SE TE ANTOJE;MENDEZ,CONNY;ESOTERISMO;458";
        String[] registro = parser.parseLine(linea);
        Assert.assertArrayEquals(
                new Object[]{"11", "TE REGALO LO QUE SE TE ANTOJE", "MENDEZ,CONNY","ESOTERISMO","458"},
                registro);
    }

    @Test
    public void testLineaLectorOk() throws Exception {
        String linea = "20;Rodriguez;Jose;0-4-8-9-13";
        String[] registro = lectores.parseLine(linea);
        Assert.assertArrayEquals(
                new Object[]{"20", "Rodriguez", "Jose","0-4-8-9-13"},
                registro);
    }

    @Test
    public void testLineaComentario() throws Exception {
        String linea = "#;11;TE REGALO LO QUE SE TE ANTOJE;MENDEZ,CONNY;ESOTERISMO;458";
        String []registro = parser.parseLine(linea);
        Assert.assertNull("Si arranca con un comentario no deberia generar un registro", registro);
    }

    @Test(expected = LineaInvalidaException.class)
    public void testLineaColumnasInsuficientes() throws Exception {
        String linea = "11;TE REGALO LO QUE SE TE ANTOJE;MENDEZ,CONNY;ESOTERISMO;458;20";
        parser.parseLine(linea);
    }

    @Test(expected = NoHayRecomendaciones.class)
    public void testNoHayRecomendaciones() throws Exception {
        ArrayList<Libro> librosleidos = new ArrayList<>();
        librosleidos.add(this.libro1);
        librosleidos.add(this.libro2);
        librosleidos.add(this.libro3);
        librosleidos.add(this.libro4);
        noRecom.recomendarGenero(libros);
    }

    /*@Test
    public void NohHayrecomendacionesfallo() throws Exception {
        ArrayList<Libro> librosleidos = new ArrayList<>();
        librosleidos.add(this.libro1);
        librosleidos.add(this.libro2);
        librosleidos.add(this.libro3);
        librosleidos.add(this.libro4);
        noRecom.recomendarGenero(libros);

    }*/

}

