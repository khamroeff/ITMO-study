package com.cat.lab4back.tools;

import com.cat.lab4back.models.Point;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.ArrayList;

public class DBCommunicatorPoints implements Serializable {

    private EntityManagerFactory managerFactory;
    private EntityManager manager;
    private EntityTransaction transaction;

    public DBCommunicatorPoints() {
        managerFactory = Persistence.createEntityManagerFactory("default");
        manager = managerFactory.createEntityManager();
        transaction = manager.getTransaction();
    }

    public void sendOne(Point point) {
        try {
            transaction.begin();
            manager.persist(point);
            transaction.commit();
        }
        catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public ArrayList<Point> getAll() {
        try {
            transaction.begin();

            ArrayList<Point> res = new ArrayList<>(
                manager.createQuery("select e from Point e", Point.class).getResultList()
            );

            transaction.commit();
            return res;
        }
        catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
            return new ArrayList<Point>();
        }
    }

    public void clearAll() {
        try {
            transaction.begin();
            manager.createQuery("delete from Point").executeUpdate();
            transaction.commit();
        }
        catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

}
