package uniandes.dpoo.hamburguesas.tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class RestauranteTest {
	
	   private Restaurante restaurante;


	    @BeforeEach
	    public void setUp() {
	        restaurante = new Restaurante();
	    }

	    @AfterEach
	    public void tearDown() {
	        
	        File folder = new File(restaurante.getCarpetaFacturas());
	        for (File file : folder.listFiles()) {
	            if (file.isFile()) {
	                file.delete();
	            }
	        }
	    }

	    @Test
	    public void testIniciarPedido() throws YaHayUnPedidoEnCursoException {
	        restaurante.iniciarPedido("Juan", "Calle 123");
	        assertNotNull(restaurante.getPedidoEnCurso());
	        assertEquals("Juan", restaurante.getPedidoEnCurso().getNombreCliente());
	    }

	    @Test
	    public void testIniciarPedido2() {
	        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
	            restaurante.iniciarPedido("Juan", "Calle 123");
	            restaurante.iniciarPedido("Pedro", "Calle 456");
	        });
	    }

	    @Test
	    public void testCerrarYGuardar() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException {
	        restaurante.iniciarPedido("Juan", "Calle 123");
	        restaurante.cerrarYGuardarPedido();

	        assertNull(restaurante.getPedidoEnCurso());

	        File archivoFactura = new File(restaurante.getCarpetaFacturas() + "factura_0.txt");
	        assertTrue(archivoFactura.exists());

	        archivoFactura.delete();
	    }

	    @Test
	    public void testCerrarYGuardar2() {
	        assertThrows(NoHayPedidoEnCursoException.class, () -> {
	            restaurante.cerrarYGuardarPedido();
	        });
	    }
	    
	    
}
