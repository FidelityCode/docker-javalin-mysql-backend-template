package de.niklasenglmeier.backend.controllers;

import com.google.gson.Gson;
import de.niklasenglmeier.backend.models.User;
import io.javalin.Javalin;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserController extends Controller {

    public UserController(Javalin app, SessionFactory dbSessionFactory, Gson gson) {
        super(app, dbSessionFactory, gson);

        app.get("/user/all", ctx -> {
            try (Session session = dbSessionFactory.openSession()) {
                session.beginTransaction();

                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<User> criteria = builder.createQuery(User.class);
                criteria.from(User.class);
                List<User> data = session.createQuery(criteria).getResultList();

                session.getTransaction().commit();

                ctx.result(gson.toJson(data));
            }
        });

        app.get("/user/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            try (Session session = dbSessionFactory.openSession()) {
                session.beginTransaction();

                User user = session.get(User.class, id);

                session.getTransaction().commit();

                ctx.result(gson.toJson(user));
            }
        });

        app.post("/user", ctx -> {
            User userFromBody = gson.fromJson(ctx.body(), User.class);
            try (Session session = dbSessionFactory.openSession()) {
                session.beginTransaction();

                session.persist(userFromBody);

                session.getTransaction().commit();

                ctx.result(gson.toJson(userFromBody));
            }
        });

        app.put("/user/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            User user = gson.fromJson(ctx.body(), User.class);

            if(id != user.getId()) {
                ctx.status(400).result("ID path param does not match the user object's id");
            }

            try (Session session = dbSessionFactory.openSession()) {
                session.beginTransaction();

                User userToUpdate = session.get(User.class, id);

                userToUpdate.setFirstName(user.getFirstName());
                userToUpdate.setLastName(user.getLastName());
                userToUpdate.setBirthDate(user.getBirthDate());

                session.getTransaction().commit();

                session.beginTransaction();

                User fromDB = session.get(User.class, id);

                session.getTransaction().commit();

                ctx.result(gson.toJson(fromDB));
            }
        });

        app.delete("/user/{id}", ctx -> {
            User user = gson.fromJson(ctx.body(), User.class);
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                session.remove(user);

                session.getTransaction().commit();

                ctx.result(gson.toJson(user));
            }
        });
    }

}
