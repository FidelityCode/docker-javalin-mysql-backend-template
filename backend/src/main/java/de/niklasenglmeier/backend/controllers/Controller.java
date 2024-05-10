package de.niklasenglmeier.backend.controllers;

import com.google.gson.Gson;
import io.javalin.Javalin;
import org.hibernate.SessionFactory;

public class Controller {
    Javalin app;
    SessionFactory sessionFactory;
    Gson gson;

    public Controller(Javalin app, SessionFactory dbSessionFactory, Gson gson) {
        this.app = app;
        this.sessionFactory = dbSessionFactory;
        this.gson = gson;
    }
}
