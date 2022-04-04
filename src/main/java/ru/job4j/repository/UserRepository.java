package ru.job4j.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.User;

import java.util.function.Function;

public class UserRepository {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public UserRepository() {
    }

    private static final class LazyUser {
        private static final UserRepository INST = new UserRepository();
    }

    public static UserRepository instOf() {
        return UserRepository.LazyUser.INST;
    }

    public User addUser(User user) {
        this.tx(session -> session.save(user));
        return user;
    }

    public User findUserByEmail(String email) {
        return this.tx(
                session -> (User) session.createQuery("from User u where u.email = :paramEmail").setParameter("paramEmail", email).uniqueResult());
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
