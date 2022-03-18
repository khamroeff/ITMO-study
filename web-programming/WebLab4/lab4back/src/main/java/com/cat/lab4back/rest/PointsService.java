package com.cat.lab4back.rest;

import com.cat.lab4back.models.Point;
import com.cat.lab4back.tools.DBCommunicatorPoints;
import com.google.gson.Gson;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.util.ArrayList;

import static com.cat.lab4back.rest.UserService.COOKIE_NAME;

@Singleton
@Lock(LockType.WRITE)
@Path("/points")
public class PointsService {

    private DBCommunicatorPoints dbCommunicator;

    @EJB
    private UserService userService;

    @Context
    private HttpServletResponse httpServletResponse;

    @Context
    private HttpServletRequest httpServletRequest;

    @PostConstruct
    public void init() {
        dbCommunicator = new DBCommunicatorPoints();
    }

    private boolean isLoggedIn() {
        if (httpServletRequest.getCookies() == null) {
            return false;
        }

        for (Cookie i : httpServletRequest.getCookies()) {
            if (i.getName().equals(COOKIE_NAME) && userService.getCookies().contains(i.getValue())) {
                return true;
            }
        }
        return false;
    }

    @POST
    @Produces("application/json")
    public String sendPoint(
            @FormParam("x") @DefaultValue("0") String x,
            @FormParam("y") @DefaultValue("0") String y,
            @FormParam("r") @DefaultValue("0") String r) {

        if (!isLoggedIn()) {
            try {
                httpServletResponse.sendError(401, "Not authorized");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new Gson().toJson(null);
        }

        double dx, dy, dr;

        try {
            dx = Double.parseDouble(x);
            dy = Double.parseDouble(y);
            dr = Double.parseDouble(r);
        }
        catch (NumberFormatException e) {
            return new Gson().toJson(null);
        }

        Point point = new Point(dx, dy, dr);
        point.calc();
        dbCommunicator.sendOne(point);

        return new Gson().toJson(point);
    }

    @DELETE
    public void delete() {
        if (!isLoggedIn()) {
            try {
                httpServletResponse.sendError(401, "Not authorized");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        dbCommunicator.clearAll();
    }

    @GET
    @Produces("application/json")
    public String fetchAll() {
        if (!isLoggedIn()) {
            try {
                httpServletResponse.sendError(401, "Not authorized");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new Gson().toJson(null);
        }
        ArrayList<Point> list = dbCommunicator.getAll();
        return new Gson().toJson(list);
    }
}
