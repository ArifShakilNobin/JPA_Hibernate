package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Employee;
import org.example.entities.Product;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String puName="puName";
        Map<?,?> props = new HashMap<>();
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), props);

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");

        EntityManager em = emf.createEntityManager();// represents the context

        try {

            em.getTransaction().begin();

//            Product p = new Product();
//            p.setId(2L);
//            p.setName("Toy");
//            Employee e=new Employee();
//            e.setId(2L);
//            e.setName("Arthi");
//            e.setAddress("Dhaka");
            Employee e = em.find(Employee.class,1);

//            em.persist();   -> Adding an entity in the context
//            em.find()       -> Finds by PK. Get from DB and add it to the context if it doesn't already exist
//            em.remove();    -> Marking entity for removal
//            em.merge();     -> Merges an entity from outside the context to the context.
//            em.refresh();   -> Mirror the context from the database
//            em.detach();    -> Taking the entity out of the context
//            em.getReference()

            System.out.println(e);
//            em.persist(); // add this to the context  -> NOT AN INSERT QUERY

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}