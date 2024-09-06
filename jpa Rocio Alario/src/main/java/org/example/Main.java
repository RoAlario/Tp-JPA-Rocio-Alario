package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("En marcha Ro");

        try {

            // Persistir una nueva entidad Person
            entityManager.getTransaction().begin();

            //Creo las personas
           Cliente cliente1 = Cliente.builder()
                   .nombre("Rocio")
                   .apellido("Alario")
                   .dni(44907399)
                   .build();


            Cliente cliente2 = Cliente.builder()
                    .nombre("Facundo")
                    .apellido("Alario")
                    .dni(39829110)
                    .build();

            Cliente cliente3 = Cliente.builder()
                    .nombre("Juan")
                    .apellido("Sanchez")
                    .dni(4560392)
                    .build();

            Articulo art1 = Articulo.builder()
                    .cantidad(3)
                    .denominacion("Gomitas")
                    .precio(200)
                    .build();

            Articulo art2 = Articulo.builder()
                    .cantidad(15)
                    .denominacion("Chupetines")
                    .precio(300)
                    .build();

            Articulo art3 = Articulo.builder()
                    .cantidad(50)
                    .denominacion("Tortitas")
                    .precio(250)
                    .build();

            Domicilio dom1 = Domicilio.builder()
                    .calle("Juan G Molina")
                    .numcalle(1020)
                    .build();


            Domicilio dom2 = Domicilio.builder()
                    .calle("Juan Rivadavia")
                    .numcalle(3000)
                    .build();


            Domicilio dom3 = Domicilio.builder()
                    .calle("Colon")
                    .numcalle(300)
                    .build();

            Categoria lib = Categoria.builder()
                    .denominacionCat("Libreria")
                    .build();

            Categoria com = Categoria.builder()
                    .denominacionCat("Comestibles")
                    .build();

            Factura f1 = Factura.builder()
                    .fecha("3/12/23")
                    .num(19)
                    .total(5000)
                    .build();

            Factura f2 = Factura.builder()
                    .fecha("25/11/23")
                    .num(15)
                    .total(10000)
                    .build();

            Factura f3 = Factura.builder()
                    .fecha("1/2/24")
                    .num(25)
                    .total(15000)
                    .build();

            Factura f4 = Factura.builder()
                    .fecha("3/2/24")
                    .num(28)
                    .total(20000)
                    .build();

            DetalleFactura d1 = DetalleFactura.builder()
                    .subtotal(2000)
                    .cant(5)
                    .build();

            DetalleFactura d2 = DetalleFactura.builder()
                    .subtotal(3000)
                    .cant(4)
                    .build();

            //Hago las relaciones
            cliente1.setDomicilio(dom1);
            cliente2.setDomicilio(dom2);
            cliente3.setDomicilio(dom3);

            dom1.setCliente(cliente1);
            dom2.setCliente(cliente2);
            dom3.setCliente(cliente3);

            f1.setCliente(cliente1);
            f2.setCliente(cliente1);
            f3.setCliente(cliente2);
            f4.setCliente(cliente3);

            f1.getDetalles().add(d1);
            f3.getDetalles().add(d2);

            d1.setFactura(f1);
            d2.setFactura(f3);



            art3.getCategorias().add(com);
            art2.getCategorias().add(lib);
            art1.getCategorias().add(lib);

            lib.getArticulos().add(art1);
            lib.getArticulos().add(art2);
            com.getArticulos().add(art3);


            d1.setArticulo(art1);
            d2.setArticulo(art2);

            art1.getDetalle().add(d1);
            art2.getDetalle().add(d2);



            //Hago las persistencias
            //Clientes
            entityManager.persist(cliente1);
            entityManager.persist(cliente2);
            entityManager.persist(cliente3);

            //Articulos
            entityManager.persist(art1);
            entityManager.persist(art2);
            entityManager.persist(art3);

            //Domicilio
            entityManager.persist(dom1);
            entityManager.persist(dom2);
            entityManager.persist(dom3);

            //Caegoria
            entityManager.persist(lib);
            entityManager.persist(com);

            //Factura
            entityManager.persist(f1);
            entityManager.persist(f2);
            entityManager.persist(f3);
            entityManager.persist(f4);

            //Detalle Factura
            entityManager.persist(d1);
            entityManager.persist(d2);



            System.out.println();
            System.out.println("=================================================");
            System.out.println("Cliente de domicilio: " + dom1.getCliente().getDni());
            System.out.println("Domicillio del cliente: " + cliente1.getDomicilio().getCalle());
            System.out.println("==================================================");
            System.out.println();




            entityManager.getTransaction().commit();


        }catch (Exception e){

            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar la clase Persona");}

        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
