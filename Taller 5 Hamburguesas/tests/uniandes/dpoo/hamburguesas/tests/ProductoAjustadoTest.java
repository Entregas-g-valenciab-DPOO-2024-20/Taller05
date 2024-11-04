package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;


public class ProductoAjustadoTest {

	private ProductoMenu productoBase;
    private Ingrediente ingredienteExtra;
    private Ingrediente ingredienteEliminado;
    private ProductoAjustado productoAjustado;

    @BeforeEach
    void setUp() {
        
        productoBase = new ProductoMenu("Hamburguesa", 10000);
        
        
        ingredienteExtra = new Ingrediente("Queso", 2000);
        ingredienteEliminado = new Ingrediente("Tomate", 500);
        
        
        productoAjustado = new ProductoAjustado(productoBase);
    }

    @Test
    void testGetNombre() {
        assertEquals("Hamburguesa", productoAjustado.getNombre(), "El nombre del producto ajustado debe ser igual al del producto base");
    }

    @Test
    void testGetPrecio() {
        assertEquals(10000, productoAjustado.getPrecio(), "El precio debe ser igual al del producto base sin modificaciones");
    }

    @Test
    void testGetPrecio2() {
        
        productoAjustado.agregados.add(ingredienteExtra);
        
        assertEquals(12000, productoAjustado.getPrecio(), "El precio debe ser igual al del producto base más el costo del ingrediente agregado");
    }


    @Test
    void testGenerarFactura() {
        
        productoAjustado.agregados.add(ingredienteExtra);

        String expected = "Hamburguesa\n    +Queso                2000\n            12000\n";
        assertEquals(expected, productoAjustado.generarTextoFactura(), "La factura debe reflejar el precio con el ingrediente agregado");
    }

	
}
