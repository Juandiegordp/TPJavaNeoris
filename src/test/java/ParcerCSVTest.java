import ar.edu.utn.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;


public class ParcerCSVTest {
    private ParserCSV parser;
    private ParserCSV lectores;

    @Before
    public void setUp(){
        this.parser = new ParserCSV(Arrays.asList(new String[]{"Cod", "Nombre del libro", "Autor", "Genero", "Precio"}));
        this.lectores = new ParserCSV(Arrays.asList(new String[]{"Codigo", "Apellido", "Nombre", "Libros Leidos"}));
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
}
