package com.cat.lab4back.models;

import com.google.gson.Gson;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Point implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private double x;
    private double y;
    private double r;
    private boolean insideArea;
    private Date timestamp;
    private long executionTime;

    public Point() { this(0, 0, 0); }

    public Point(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public void calc() {
        long now = System.nanoTime();

        if (x >= 0 && y >= 0 && x <= r/2.0 && y <= r) insideArea = true;
        else if (x <= 0 && y >= 0 && (y-2 * x <= r)) insideArea = true;
        else insideArea = x <= 0 && y <= 0 && x * x + y * y <= r*r/4;

        timestamp = new Date(System.currentTimeMillis());
        executionTime = System.nanoTime() - now;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getR() {
        return r;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setR(double r) {
        this.r = r;
    }

    public boolean isInsideArea() {
        return insideArea;
    }
    public void setInsideArea(boolean insideArea) {
        this.insideArea = insideArea;
    }

    public long getExecutionTime() {
        return executionTime;
    }
    public Date getTimestamp() {
        return timestamp;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
