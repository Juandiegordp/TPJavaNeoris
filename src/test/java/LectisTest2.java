import ar.edu.utn.Libro;
import ar.edu.utn.Recomendador;
import com.sun.org.apache.xpath.internal.operations.Equals;
import org.junit.Before;
import org.junit.Test;
import ar.edu.utn.Lector;
import java.util.ArrayList;
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
    }

    @Test
    public void testGeneroRecomendadoOk() {
        assertEquals("Recomendo mal","sd",recom.generoRecomendado());
    }

    @Test
    public void testRecomendacionPorGeneroOk() {
        ArrayList<Libro>recomEsperada = new ArrayList<>();
        recomEsperada.add(libro4);
        recom.recomendarGenero(libros);
        assertEquals("Fallo",recomEsperada,recom.getRecomendaciones());
    }
}

