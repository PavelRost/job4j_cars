package ru.job4j.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Car;

import java.util.List;
import java.util.function.Function;

public class CarRepository {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public CarRepository() {

    }

    private static final class LazyCar {
        private static final CarRepository INST = new CarRepository();
    }

    public static CarRepository instOf() {
        return LazyCar.INST;
    }

    public List<Car> findAllCars() {
        return this.tx(session -> session.createQuery("from Car").list());
    }

    public Car findCarById(int id) {
        return this.tx(session -> (Car) session.createQuery("from Car c where c.id = :paramId").setParameter("paramId", id).uniqueResult());
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
