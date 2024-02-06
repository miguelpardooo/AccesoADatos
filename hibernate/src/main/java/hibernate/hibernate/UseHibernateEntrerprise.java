package hibernate.hibernate;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.Scanner;

public class UseHibernateEntrerprise 
{
    public static void main( String[] args )
    {
    	LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE)
    	;
    	HibernateEntrerprise h = new HibernateEntrerprise();
    	
    	//TABLA PRODUCTOS
    	
    	//SQL
    	// System.out.println("");
    	//h.addProduct("monitor",170);
    	// System.out.println("");
    	// h.showProducts();
    	// System.out.println("");
    	// h.findProductById(3);
    	// System.out.println("");
    	// h.deleteProductById(7);
    	//h.showProducts();
    	// System.out.println("");
    	// h.updateProductById(5,"ssd",105);
    	// h.updateProductById(8,"ssd",155);
    	// h.close();
    	
    	//HQL
    	//h.mostrarProductos();
    	//h.mostrarPorNombre("monitor");
    	//h.productosOrdenadosPorPrecio();
    	//h.precioDe("monitor");
    	//h.buscaProducto(2);
    	
    	
    	//TABLA CLIENTES - HQL
    	
    	//h.mostrarClientes();
    	//h.agregarCliente("ahmed","pakistan");
    	//h.borrarClientePorId(4);
    	//h.actualizarCliente(3);
    	//h.borrarCliente("luisa");
    	//h.mostrarPorPais("espana");
    	h.buscarPaisDe("ana");
    }
}
