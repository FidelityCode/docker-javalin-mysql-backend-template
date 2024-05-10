package de.niklasenglmeier.backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.niklasenglmeier.backend.controllers.UserController;
import io.javalin.Javalin;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

public class Main {

    private static SessionFactory buildSessionFactory(String dbHost, String dbName, String dbUser, String dbPassword) {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://"+dbHost+"/"+dbName);
            configuration.setProperty("hibernate.connection.username", dbUser);
            configuration.setProperty("hibernate.connection.password", dbPassword);

            System.out.println("CONNECTION STRING: " + configuration.getProperty("hibernate.connection.url"));

            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String envDbHost = System.getenv("DATABASE_HOST");
        System.out.println("DATABASE_HOST");
        System.out.println(envDbHost);
        String envDbUser = System.getenv("DATABASE_USER");
        System.out.println("DATABASE_USER");
        System.out.println(envDbUser);
        String envDbPass = System.getenv("DATABASE_PASSWORD");
        System.out.println("DATABASE_PASSWORD");
        System.out.println(envDbPass);
        String envDbName = System.getenv("DATABASE_NAME");
        System.out.println("DATABASE_NAME");
        System.out.println(envDbName);

        SessionFactory dbSessionFactory = buildSessionFactory(envDbHost, envDbName, envDbUser, envDbPass);
        Gson gson = new GsonBuilder().create();

        Javalin app = Javalin
                .create()
                .get("/", ctx -> ctx.result(envDbHost));

        UserController userController = new UserController(app, dbSessionFactory, gson);

        app.start(9000);
    }
}