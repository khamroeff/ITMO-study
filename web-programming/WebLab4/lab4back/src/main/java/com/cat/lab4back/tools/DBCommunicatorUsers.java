package com.cat.lab4back.tools;

import com.cat.lab4back.models.UserEntry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.Serializable;

public class DBCommunicatorUsers implements Serializable {

    private EntityManagerFactory managerFactory;
    private EntityManager manager;
    private EntityTransaction transaction;

    public DBCommunicatorUsers() {
        managerFactory = Persistence.createEntityManagerFactory("default");
        manager = managerFactory.createEntityManager();
        transaction = manager.getTransaction();
    }

    public void sendOne(UserEntry user) {
        try {
            transaction.begin();
            manager.persist(user);
            transaction.commit();
        }
        catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public UserEntry findByLogin(String login) {
        try {
            transaction.begin();

            UserEntry user = manager.createQuery("select e from UserEntry e where e.login = :login", UserEntry.class)
                    .setParameter("login", login)
                    .setMaxResults(1)
                    .getSingleResult();

            transaction.commit();
            return user;
        }
        catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

}
