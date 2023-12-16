package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JpaApp {

    private static final Logger LOGGER = Logger.getLogger(org.example.JpaApp.class.getName());

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("default"); // Ensure persistence.xml is configured
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Drop and create the table
            entityManager.createNativeQuery("DROP TABLE IF EXISTS CD").executeUpdate();
            entityManager.createNativeQuery("CREATE TABLE CD (ID INT AUTO_INCREMENT, CD_NAME VARCHAR(40), BAND_NAME VARCHAR(40), PRIMARY KEY (ID))").executeUpdate();

            // Populate the table
            org.example.CD cd1 = new org.example.CD();
            cd1.setCdName("5150_1");
            cd1.setBandName("Van Halen");

            org.example.CD cd2 = new org.example.CD();
            cd2.setCdName("1984_1");
            cd2.setBandName("Van Halen");

            org.example.CD cd3 = new org.example.CD();
            cd3.setCdName("Back in Black_1");
            cd3.setBandName("AC/DC");

            org.example.CD cd4 = new org.example.CD();
            cd4.setCdName("Full Bluntal Nugity_1");
            cd4.setBandName("Ted Nugent");

            entityManager.persist(cd1);
            entityManager.persist(cd2);
            entityManager.persist(cd3);
            entityManager.persist(cd4);

            transaction.commit();

            // View the table
            List<org.example.CD> cds = entityManager.createQuery("SELECT c FROM CD c", org.example.CD.class).getResultList();
            for (org.example.CD cd : cds) {
                System.out.println(cd.getCdName() + "(" + cd.getId() + "): " + cd.getCdName() + cd.getBandName());
            }

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            LOGGER.log(Level.SEVERE, "Error in JPAApp", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }
}
