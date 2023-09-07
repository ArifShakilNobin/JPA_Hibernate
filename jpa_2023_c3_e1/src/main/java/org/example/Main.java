package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Employee;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String puName="puName";
        Map<String,String> props = new HashMap<>();

        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), props);

        EntityManager em = emf.createEntityManager();// represents the context

        try {

            em.getTransaction().begin();

            var e1 = new Employee();
//            e1.setId(1L);
//            e1.setName("Arthi");
//            e1.setAddress("Dhaka");
//
//            em.persist(e1);

            /**
             * find vs getReference
             * when we need 1 object from 100 object that time we can use getReference otherwise use find
             */
//            var e1 = em.find(Employee.class, 1); // execute select query
//            var e1 = em.getReference(Employee.class, 1);

            e1.setName("Nazifa");
            e1.setAddress("Dhaka");

            em.persist(e1); // No change previous name


//            System.out.println(e1);

//            em.persist();   -> Adding an entity in the context
//            em.find()       -> Finds by PK. Get from DB and add it to the context if it doesn't already exist
//            em.remove();    -> Marking entity for removal
//            em.merge();     -> Merges an entity from outside the context to the context.
//            em.refresh();   -> Mirror the context from the database
//            em.detach();    -> Taking the entity out of the context
//            em.getReference()

//            System.out.println(e);
//            em.persist(); // add this to the context  -> NOT AN INSERT QUERY

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}