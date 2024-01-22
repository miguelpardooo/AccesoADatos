package hibernate.hibernate;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.Scanner;

public class UseHibernateEntrerprise 
{
    public static void main( String[] args )
    {
    	/*LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE)
    	;
    	HibernateEntrerprise h = new HibernateEntrerprise();
    	// System.out.println("");
    	//h.addProduct("monitor",170);
    	// System.out.println("");
    	// h.showProducts();
    	// System.out.println("");
    	// h.findProductById(3);
    	// System.out.println("");
    	// h.deleteProductById(7);
    	h.showProducts();
    	// System.out.println("");
    	// h.updateProductById(5,"ssd",105);
    	// h.updateProductById(8,"ssd",155);
    	// h.close();*/
    	
    	
    	LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);

        HibernateEntrerprise h = new HibernateEntrerprise();
        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
        	System.out.println("");
            System.out.println("==== Menú ====");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Mostrar Productos");
            System.out.println("3. Buscar Producto por ID");
            System.out.println("4. Eliminar Producto por ID");
            System.out.println("5. Modificar Producto por ID");
            System.out.println("6. Salir");
            System.out.print("Ingrese su opción: ");
            System.out.println("");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Agregar Producto
                    System.out.print("Ingrese el nombre del producto: ");
                    String name = scanner.next();
                    System.out.print("Ingrese el precio del producto: ");
                    double price = scanner.nextDouble();
                    h.addProduct(name, price);
                    break;

                case 2:
                    // Mostrar Productos
                    h.showProducts();
                    break;

                case 3:
                    // Buscar Producto por ID
                    System.out.print("Ingrese el ID del producto: ");
                    int idToFind = scanner.nextInt();
                    h.findProductById(idToFind);
                    break;

                case 4:
                    // Eliminar Producto por ID
                    System.out.print("Ingrese el ID del producto a eliminar: ");
                    int idToDelete = scanner.nextInt();
                    h.deleteProductById(idToDelete);
                    break;

                case 5:
                    // Modificar Producto por ID
                    System.out.print("Ingrese el ID del producto a modificar: ");
                    int idToUpdate = scanner.nextInt();
                    System.out.print("Ingrese el nuevo nombre del producto: ");
                    String newName = scanner.next();
                    System.out.print("Ingrese el nuevo precio del producto: ");
                    double newPrice = scanner.nextDouble();
                    h.updateProductById(idToUpdate, newName, newPrice);
                    break;

                case 6:
                    // Salir
                    System.out.println("Saliendo del programa.");
                    h.close();
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                    break;
            }

        } while (choice != 6);

        scanner.close();
    	
    }
}
