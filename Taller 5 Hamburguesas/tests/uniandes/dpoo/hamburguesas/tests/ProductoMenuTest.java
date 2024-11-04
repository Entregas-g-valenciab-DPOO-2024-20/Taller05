package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;


public class ProductoMenuTest {

	private ProductoMenu productoMenu;
	
	@BeforeEach
    void setUp( ) throws Exception
    {
        productoMenu = new ProductoMenu("papas",500 );
        
    }
	
	@AfterEach
    void tearDown( ) throws Exception
    {
    }
	
	 @Test
	    void testGetNombre( )
	    {
	        assertEquals( "papas", productoMenu.getNombre(), "El nombre del producto no es el esperado." );
	    }

	 @Test
	    void testGetPrecio( )
	    {
	        assertEquals( 500, productoMenu.getPrecio(), "El costo del producto no es el esperado." );
	    }
	
	 @Test
	    void testGenerarFactura() {
	        
	        String textoEsperado = "papas\n            500\n";
	        String textoGenerado = productoMenu.generarTextoFactura();
	        
	       
	        assertEquals(textoEsperado, textoGenerado, "El texto generado por la factura no es el esperado.");
	    }
	 
	 
	 
	 
	 
	 
	 
	 
}
