package de.niklasenglmeier.backend;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        String env = System.getenv("DATABASE_HOST");

        Javalin app = Javalin
                .create()
                .get("/", ctx -> ctx.result(env));

        app.start(9000);
    }
}