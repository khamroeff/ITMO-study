package entities;

import java.util.List;
import model.Model;

public class Point {
    private final double x;
    private final double y;
    private final double r;
    private final boolean result;
    private final String timeOfSending;
    private final String timeOfExecuting;

    public Point(double x, double y, double r, String timeOfSending, String timeOfExecuting) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.timeOfSending = timeOfSending;
        this.timeOfExecuting = timeOfExecuting;
        this.result = checkPoint(x,y,r);
    }

    private boolean checkPoint(double x, double y, double r) {
        return (x>=-r/2 && x<=0 && y>=0 && y<=r) ||
                (x*x +y*y <= r*r/4 && x>=0 && y<=0) ||
                (((x >= -r) && (x <= 0) && (y <= 0) && (y >= -r)) && (x+y>=-r));
    }

    public boolean isResult() {
        return result;
    }

    public String toJsonArray() {
        List<Point> points = Model.getInstance().getPoints();
        if (points.isEmpty())
            return "[]";
        String jsonArray = "[";
        for (Point point:points) {
            jsonArray += point.toJson();
            jsonArray += ",";
        }
        jsonArray = jsonArray.substring(0, jsonArray.length() - 1);
        jsonArray += "]";
        return jsonArray;
    }

    public String toJson() {

        return "{" +
                "\"xval\":" + "\"" + x + "\"" + "," +
                "\"yval\":" + "\"" + y + "\"" + "," +
                "\"rval\":" + "\"" + r + "\"" + "," +
                "\"out\":" + "\"" + result + "\"" + "," +
                "\"sendingTime\":" + "\"" + timeOfSending + "\"" + "," +
                "\"totalProcessingTime\":" + "\"" + timeOfExecuting + " sec" + "\"" +
                "}";
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

    public String getTimeOfSending() {
        return timeOfSending;
    }

    public String getTimeOfExecuting() {
        return timeOfExecuting;
    }
}
