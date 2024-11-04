package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {
	
	private Pedido pedido;
    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    void setUp() {
    	Pedido.resetNumeroPedidos();
        
        pedido = new Pedido("Giovani Valencia", "Calle 123");
        
        
        producto1 = new ProductoMenu("Hamburguesa", 10000);
        producto2 = new ProductoMenu("Papas Fritas", 5000);
        
        // Agregar productos al pedido
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);
    }
    
    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    @Test
    void testGetIdPedido() {
        assertEquals(0, pedido.getIdPedido()); // Si es el primer pedido creado
    }

    @Test
    void testGetNombre() {
        assertEquals("Giovani Valencia", pedido.getNombreCliente());
    }

    @Test
    void testAgregarProducto() {
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(producto1);
        productos.add(producto2);
        
        assertEquals(2, productos.size());
    }

    @Test
    void testGetPrecioNeto() {
        int precioEsperado = 10000 + 5000;
        assertEquals(precioEsperado, pedido.getPrecioTotalPedido() - pedido.getPrecioIVAPedido());
    }

    @Test
    void testGetPrecioIVA() {
        int precioNeto = 10000 + 5000;
        int ivaEsperado = (int) (precioNeto * 0.19);
        assertEquals(ivaEsperado, pedido.getPrecioTotalPedido() - pedido.getPrecioNetoPedido());
    }

    @Test
    void testGetPrecioTotal() {
        int precioNeto = 10000 + 5000;
        int precioEsperado = precioNeto + (int) (precioNeto * 0.19);
        assertEquals(precioEsperado, pedido.getPrecioTotalPedido());
    }

    @Test
    void testGenerarFactura() {
        String facturaEsperada = "Cliente: Giovani Valencia\n" +
                                 "Dirección: Calle 123\n" +
                                 "----------------\n" +
                                 producto1.generarTextoFactura() +
                                 producto2.generarTextoFactura() +
                                 "----------------\n" +
                                 "Precio Neto:  " + pedido.getPrecioNetoPedido() + "\n" +
                                 "IVA:          " + pedido.getPrecioIVAPedido() + "\n" +
                                 "Precio Total: " + pedido.getPrecioTotalPedido() + "\n";
        assertEquals(facturaEsperada, pedido.generarTextoFactura());
    }

    @Test
    void testGuardarFactura() {
        File archivo = new File("factura_test.txt");
        
        try {
            pedido.guardarFactura(archivo);
            assertTrue(archivo.exists());
        } catch (FileNotFoundException e) {
            fail("No se pudo crear el archivo para la factura");
        } finally {
            archivo.delete();
        }
    }

}
