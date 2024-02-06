package hibernate.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.query.Query;
import java.util.Scanner;

import java.util.Iterator;
import java.util.List;

public class HibernateEntrerprise {

    private static SessionFactory sf; // this SessionFactory will be created once and used for all the connections
    private static Productos p;

    HibernateEntrerprise() {// constructor
        // sf = HibernateUtil.getSessionFactory();
        sf = new Configuration().configure().buildSessionFactory(); // also works
    }

    public void close() {
        sf.close();
    }

    //========================================
    //TABLA PRODUCTOS
    
    //SQL
    public void addProduct(String name, double price) {
        Session session = sf.openSession();// session es la variable que tiene el método
        Transaction tx = null;

        // create the product with the parameters in the method
        Productos p = new Productos();
        p.setNombre(name);
        p.setPrecio(price);

        // keep it in the database=session.save(p)
        try {
            System.out.println("======================================");
            System.out.printf("Insertando la Fila en la Base de Datos: %s, %s\n", name, price);
            System.out.println("======================================");

            tx = session.beginTransaction();
            session.save(p);// we INSERT p into the table PRODUCTS
            tx.commit();// if session.save doesn't produce an exception, we commit; the transaction
        } catch (Exception e) {// if there is any exception, we "rollback" and close safely
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void showProducts() {
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List allproducts = session.createQuery("From Productos").list();
            Iterator it = allproducts.iterator();
            System.out.println("======================================");
            System.out.println("Buscando Productos...");
            System.out.println("======================================");

            while (it.hasNext()) {
                // for (Iterator iterator = allproducts.iterator(); iterator.hasNext();){
                Productos p = (Productos) it.next();
                System.out.println("======================================");
                System.out.println("Id: " + p.getId());
                System.out.println("Nombre: " + p.getNombre());
                System.out.println("Precio: " + p.getPrecio());
                System.out.println("======================================");
            }

            tx.commit();
            System.out.println("======================================");
            System.out.println("Finalizada la Busqueda...");
            System.out.println("======================================");
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Productos findProductById(int id) {
        Session session = sf.openSession();
        Transaction tx = null;
        Productos p = new Productos();

        try {
            System.out.println("======================================");
            System.out.println("Cargando Producto de la Base de Datos...");
            System.out.println("======================================");

            tx = session.beginTransaction();
            p = (Productos) session.load(Productos.class, id);
            tx.commit();

            System.out.println("======================================");
            System.out.println("Producto con ID -> " + id);
            System.out.println("Su Nombre es -> " + p.getNombre());
            System.out.println("======================================");
        } catch (ObjectNotFoundException e) {
            if (tx != null) {
                System.out.println(e);
                System.out.println("Product not found");
            }
        } catch (Exception e) {
            if (tx != null) {
                System.out.println(e);
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return p;
    }

    public void deleteProductById(int id) {
        Productos p = new Productos();
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            System.out.println("======================================");
            System.out.println("Buscando Producto con ID -> " + id);
            System.out.println("======================================");

            tx = session.beginTransaction();
            p = (Productos) session.get(Productos.class, id);

            if (p != null) {
                System.out.println("======================================");
                System.out.println("Borrando Producto de la Base de Datos...");
                System.out.println("======================================");

                session.delete(p);
                tx.commit();

                System.out.println("======================================");
                System.out.printf("Producto Borrado de la Base de Datos..." + "\n ID -> %s\n Nombre -> %s\n Precio -> %s",
                        p.getId(), p.getNombre(), p.getPrecio());
                System.out.println("\n======================================");
            } else {
                System.out.println("======================================");
                System.out.println("No Se Encontro Ningun Producto con ID -> " + id);
                System.out.println("======================================");
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void updateProductById(int id, String newName, double newPrice) {
        Productos p = new Productos();
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            System.out.println("======================================");
            System.out.println("Modificando el Producto de la Base de Datos...");
            System.out.println("Con los Siguientes Datos...");
            System.out.println("ID -> " + id);
            System.out.println("Nombre -> " + newName);
            System.out.println("Precio -> " + newPrice);
            System.out.println("======================================");

            tx = session.beginTransaction();
            p = (Productos) session.load(Productos.class, id);// we load the product

            System.out.println("======================================");
            System.out.println("Datos del Producto en la Base de Datos...");
            System.out.println("======================================");
            System.out.printf(" ID -> %s\n Nombre -> %s\n Precio -> %s",
                    p.getId(), p.getNombre(), p.getPrecio());
            System.out.println("\n======================================");

            p.setPrecio(newPrice);// we change the properties
            p.setNombre(newName);
            session.update(p);// we update the values in the database
            tx.commit();

            System.out.println("======================================");
            System.out.println("Producto Modificado");
            System.out.println("======================================");
            System.out.printf("Datos del Producto Modificado..." + "\n ID -> %s\n Nombre -> %s\n Precio -> %s",
                    p.getId(), p.getNombre(), p.getPrecio());
            System.out.println("\n======================================");
        } catch (Exception e) {
            System.out.println("======================================");
            System.out.println("No Se Encontro el Producto con ID -> " + id);
            System.out.println("======================================");

            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
    
    
    //HQL
    public void mostrarProductos() {
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<Productos> allProducts = session.createQuery("FROM Productos").list();

            for (Productos p : allProducts) {
                System.out.print("Id: " + p.getId());
                System.out.print(", Name: " + p.getNombre());
                System.out.println(", Price: " + p.getPrecio());
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void mostrarPorNombre(String texto) {
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Utilizar HQL para obtener los productos que contienen el texto en el nombre
            Query<Productos> query = session.createQuery("FROM Productos WHERE nombre LIKE :texto", Productos.class);
            query.setParameter("texto", "%" + texto + "%");

            List<Productos> productos = query.list();

            if (productos.isEmpty()) {
                System.out.println("======================================");
                System.out.println("No se encontraron productos con el texto: " + texto);
                System.out.println("======================================");
            } else {
                System.out.println("======================================");
                System.out.println("Productos que contienen el texto: " + texto);
                System.out.println("======================================");

                for (Productos producto : productos) {
                    System.out.println("Id: " + producto.getId());
                    System.out.println("Nombre: " + producto.getNombre());
                    System.out.println("Precio: " + producto.getPrecio());
                    System.out.println("======================================");
                }
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void productosOrdenadosPorPrecio() {
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Utilizar HQL para obtener los productos ordenados por precio
            Query<Productos> query = session.createQuery("FROM Productos ORDER BY precio", Productos.class);

            List<Productos> productosOrdenados = query.list();

            System.out.println("======================================");
            System.out.println("Productos Ordenados por Precio (Ascendente):");
            System.out.println("======================================");

            for (Productos p : productosOrdenados) {
                System.out.print("Id: " + p.getId());
                System.out.print(", Nombre: " + p.getNombre());
                System.out.println(", Precio: " + p.getPrecio());
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void precioDe(String nombre) {
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Utilizar HQL para obtener el precio de los productos con el nombre dado
            Query<Double> query = session.createQuery("SELECT precio FROM Productos WHERE nombre = :nombre", Double.class);
            query.setParameter("nombre", nombre);

            List<Double> precios = query.list();

            if (precios.isEmpty()) {
                System.out.println("======================================");
                System.out.println("No se encontraron productos con el nombre: " + nombre);
                System.out.println("======================================");
            } else {
                System.out.println("======================================");
                System.out.println("Precios de los productos con el nombre: " + nombre);
                System.out.println("======================================");

                for (Double precio : precios) {
                    System.out.println("Precio: " + precio);
                }
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void buscaProducto(int id) {
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Utilizar HQL para obtener un producto por ID
            Query<Productos> query = session.createQuery("FROM Productos WHERE id = :id", Productos.class);
            query.setParameter("id", id);

            Productos producto = query.uniqueResult();

            if (producto != null) {
                System.out.println("======================================");
                System.out.println("Información del Producto con ID: " + id);
                System.out.println("======================================");
                System.out.println("Id: " + producto.getId());
                System.out.println("Nombre: " + producto.getNombre());
                System.out.println("Precio: " + producto.getPrecio());
                System.out.println("======================================");
            } else {
                System.out.println("======================================");
                System.out.println("No se encontró ningún producto con ID: " + id);
                System.out.println("======================================");
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    
    //========================================
    //TABLA CLIENTES
    //
    public void mostrarClientes() {
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Utilizar HQL para obtener todos los clientes
            Query<Clientes> query = session.createQuery("FROM Clientes", Clientes.class);
            List<Clientes> clientesList = query.list();

            if (!clientesList.isEmpty()) {
                System.out.println("======================================");
                System.out.println("Lista de Clientes:");
                System.out.println("======================================");

                for (Clientes cliente : clientesList) {
                    System.out.println("Id: " + cliente.getId());
                    System.out.println("Nombre: " + cliente.getNombre());
                    System.out.println("Pais: " + cliente.getPais());
                    System.out.println("======================================");
                }
            } else {
                System.out.println("======================================");
                System.out.println("No hay clientes en la tabla.");
                System.out.println("======================================");
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void agregarCliente(String nombre, String pais) {
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            // Crear un nuevo cliente con la información proporcionada
            Clientes nuevoCliente = new Clientes(nombre, pais);

            System.out.println("======================================");
            System.out.println("Insertando nuevo cliente en la Base de Datos...");
            System.out.println("======================================");
            System.out.println("Nombre: " + nombre);
            System.out.println("Pais: " + pais);
            System.out.println("======================================");

            tx = session.beginTransaction();
            session.save(nuevoCliente); // Guardar el nuevo cliente en la base de datos
            tx.commit();

            System.out.println("======================================");
            System.out.println("Cliente añadido correctamente.");
            System.out.println("======================================");
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void borrarClientePorId(int id) {
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Clientes cliente = (Clientes) session.get(Clientes.class, id);

            if (cliente != null) {
                System.out.println("======================================");
                System.out.println("Borrando Cliente de la Base de Datos...");
                System.out.println("======================================");

                session.delete(cliente);
                tx.commit();

                System.out.println("======================================");
                System.out.printf("Cliente Borrado de la Base de Datos..." + "\n ID -> %s\n Nombre -> %s\n País -> %s",
                        cliente.getId(), cliente.getNombre(), cliente.getPais());
                System.out.println("\n======================================");
            } else {
                System.out.println("======================================");
                System.out.println("No Se Encontró Ningún Cliente con ID -> " + id);
                System.out.println("======================================");
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void actualizarCliente(int id) {
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Clientes cliente = (Clientes) session.get(Clientes.class, id);

            if (cliente != null) {
                System.out.println("======================================");
                System.out.println("Actualizando Cliente en la Base de Datos...");
                System.out.println("======================================");

                System.out.println("Valores actuales del Cliente:");
                System.out.println("ID: " + cliente.getId());
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("País: " + cliente.getPais());

                Scanner scanner = new Scanner(System.in);

                System.out.println("¿Desea ingresar un nuevo nombre? (s/n): ");
                String respuestaNombre = scanner.nextLine();

                if ("s".equalsIgnoreCase(respuestaNombre)) {
                    System.out.println("Ingrese el nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    cliente.setNombre(nuevoNombre);
                }

                System.out.println("¿Desea ingresar un nuevo país? (s/n): ");
                String respuestaPais = scanner.nextLine();

                if ("s".equalsIgnoreCase(respuestaPais)) {
                    System.out.println("Ingrese el nuevo país: ");
                    String nuevoPais = scanner.nextLine();
                    cliente.setPais(nuevoPais);
                }

                session.update(cliente);
                tx.commit();

                System.out.println("======================================");
                System.out.println("Cliente Actualizado en la Base de Datos...");
                System.out.println("Valores Actualizados del Cliente:");
                System.out.println("ID: " + cliente.getId());
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("País: " + cliente.getPais());
                System.out.println("======================================");
            } else {
                System.out.println("======================================");
                System.out.println("No Se Encontró Ningún Cliente con ID -> " + id);
                System.out.println("======================================");
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void borrarCliente(String nombre) {
    	Session session = sf.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Utiliza HQL para cargar el cliente por nombre
            String hql = "FROM Clientes WHERE nombre = :nombre";
            Clientes cliente = (Clientes) session.createQuery(hql)
                    .setParameter("nombre", nombre)
                    .uniqueResult();

            if (cliente != null) {
                System.out.println("======================================");
                System.out.println("Borrando Cliente de la Base de Datos...");
                System.out.println("======================================");

                session.delete(cliente);
                tx.commit();

                System.out.println("======================================");
                System.out.printf("Cliente Borrado de la Base de Datos..." + "\n ID -> %s\n Nombre -> %s\n País -> %s",
                        cliente.getId(), cliente.getNombre(), cliente.getPais());
                System.out.println("\n======================================");
            } else {
                System.out.println("======================================");
                System.out.println("No Se Encontró Ningún Cliente con Nombre -> " + nombre);
                System.out.println("======================================");
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void mostrarPorPais(String pais) {
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Utiliza HQL para obtener la lista de clientes en el país especificado
            String hql = "FROM Clientes WHERE pais = :pais";
            List<Clientes> clientesEnPais = session.createQuery(hql).setParameter("pais", pais).list();

            int totalClientes = clientesEnPais.size();

            System.out.println("======================================");
            System.out.printf("Número de Clientes en %s: %d\n", pais, totalClientes);
            System.out.println("======================================");

            for (Clientes cliente : clientesEnPais) {
                System.out.println("======================================");
                System.out.println("ID: " + cliente.getId());
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("País: " + cliente.getPais());
                System.out.println("======================================");
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void buscarPaisDe(String nombre) {
        Session session = sf.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Utiliza HQL para obtener el país del cliente con el nombre especificado
            String hql = "SELECT pais FROM Clientes WHERE nombre = :nombre";
            String pais = (String) session.createQuery(hql)
                    .setParameter("nombre", nombre)
                    .uniqueResult();

            if (pais != null) {
                System.out.println("======================================");
                System.out.printf("País del Cliente %s: %s\n", nombre, pais);
                System.out.println("======================================");
            } else {
                System.out.println("======================================");
                System.out.printf("No se encontró ningún cliente con el nombre %s\n", nombre);
                System.out.println("======================================");
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }



}

