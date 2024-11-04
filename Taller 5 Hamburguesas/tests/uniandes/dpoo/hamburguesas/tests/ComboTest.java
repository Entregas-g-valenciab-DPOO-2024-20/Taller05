package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	
	private ArrayList<ProductoMenu> itemsCombo;
    private Combo combo;

    @BeforeEach
    void setUp() {
        itemsCombo = new ArrayList<>();
        
        itemsCombo.add(new ProductoMenu("Hamburguesa", 10000));
        itemsCombo.add(new ProductoMenu("Papas Fritas", 5000));
        itemsCombo.add(new ProductoMenu("Refresco", 3000));
        
        
        combo = new Combo("Combo Familiar", 10, itemsCombo); 
    }

    @Test
    void testGetNombre() {
        assertEquals("Combo Familiar", combo.getNombre());
    }

    @Test
    void testGetPrecio() {
        assertEquals(16200, combo.getPrecio());
    }

    @Test
    void testGenerarFactura() {
        String expected = "Combo Combo Familiar\n" +
                          " Descuento: 10.0\n" +
                          "            16200\n";
        assertEquals(expected, combo.generarTextoFactura());
    }

    @Test
    void testDescuento() {
        
        Combo comboSinDescuento = new Combo("Combo Sin Descuento", 0, itemsCombo);
        assertEquals(18000, comboSinDescuento.getPrecio());
    }

    @Test
    void testDescuento2() {
        
        Combo comboGratis = new Combo("Combo Gratis", 100, itemsCombo);
        assertEquals(0, comboGratis.getPrecio());
    }

    
}


