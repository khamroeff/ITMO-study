package com.cat.lab4back.rest;

import com.cat.lab4back.models.UserEntry;
import com.cat.lab4back.tools.DBCommunicatorUsers;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.TreeSet;

@Singleton
@Lock(LockType.WRITE)
@Path("/user")
public class UserService {

    @Context
    private HttpServletResponse httpServletResponse;

    @Context
    private HttpServletRequest httpServletRequest;

    private DBCommunicatorUsers communicator;

    private static TreeSet<String> cookies;

    public static final String COOKIE_NAME = "lab_access_token";

    @PostConstruct
    public void init() {
        communicator = new DBCommunicatorUsers();
        cookies = new TreeSet<>();
    }

    @POST
    public void login(
            @FormParam("login") @DefaultValue("") String login,
            @FormParam("pass") @DefaultValue("") String pass
    ) {
        try {
            UserEntry user = communicator.findByLogin(login);
            if (user == null) {
                httpServletResponse.sendError(403, "Wrong credentials");
                return;
            }
            String hash = new String(generateHash(pass + user.getPassSalt()));
            if (!hash.equals(user.getPassHash())) {
                httpServletResponse.sendError(403, "Wrong credentials");
                return;
            }

            String accessToken = generateRandomString(32);
            cookies.add(accessToken);
            httpServletResponse.setHeader("Set-Cookie",  String.format("%s=%s; HttpOnly; Secure; Path=/lab4back-1.0-SNAPSHOT/api/; SameSite=none", COOKIE_NAME, accessToken));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GET
    public void checkLogin() {
        try {
            if (httpServletRequest.getCookies() == null) {
                httpServletResponse.sendError(401, "Not authorized");
                return;
            }
            for (Cookie i : httpServletRequest.getCookies()) {
                if (i.getName().equals(COOKIE_NAME) && cookies.contains(i.getValue())) {
                    return;
                }
            }
            httpServletResponse.sendError(401, "Not authorized");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DELETE
    public void logout() {
        for (Cookie i : httpServletRequest.getCookies()) {
            if (i.getName().equals(COOKIE_NAME)) {
                cookies.remove(i.getValue());
                return;
            }
        }
    }

    @POST
    @Path("/reg")
    public void register(
            @FormParam("login") @DefaultValue("") String login,
            @FormParam("pass") @DefaultValue("") String pass
    ) {
        try {
            if (login.isBlank() || pass.isBlank()) {
                httpServletResponse.sendError(400, "Login or password can't be blank");
                return;
            }

            if (communicator.findByLogin(login) != null) {
                httpServletResponse.sendError(400, "Login already in use");
                return;
            }

            String salt = generateRandomString(10);
            String hash = new String(generateHash(pass + salt));

            UserEntry user = new UserEntry(login, hash, salt);
            communicator.sendOne(user);

            String accessToken = generateRandomString(32);
            cookies.add(accessToken);
            httpServletResponse.setHeader("Set-Cookie",  String.format("%s=%s; HttpOnly; Secure; Path=/lab4back-1_0-SNAPSHOT/api/; SameSite=none", COOKIE_NAME, accessToken));

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private String generateRandomString(int targetStringLength) {

        StringBuilder stringBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < targetStringLength; i++) {
            stringBuilder.append(ALPHABET.charAt(secureRandom.nextInt(ALPHABET.length())));
        }

        return stringBuilder.toString();
    }

    private byte[] generateHash(String string) {
        byte[] hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(string.getBytes(StandardCharsets.UTF_8));
        } catch (Exception ignore) {}
        return hash;
    }

    public static TreeSet<String> getCookies() {
        return cookies;
    }
}
