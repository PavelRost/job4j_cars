package ru.job4j;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.model.Advertisement;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AdRepository {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public AdRepository() {
    }

    private static final class Lazy {
        private static final AdRepository INST = new AdRepository();
    }

    public static AdRepository instOf() {
        return Lazy.INST;
    }

    public List<Advertisement> findAdWithPhoto() {
        return this.tx(
                session -> {
                    List<Advertisement> rsl =
                            session.createQuery("from Advertisement a where a.photo = :paramPhoto")
                                    .setBoolean("paramPhoto", true).list();
                    return rsl;
                }
        );
    }

    public List<Advertisement> findByMarkCar(String mark) {
        return this.tx(
                session -> {
                    List<Advertisement> rsl =
                            session.createQuery("from Advertisement a where a.mark = :paramMark")
                                    .setParameter("paramMark", mark).list();
                    return rsl;
                }
        );
    }

    public List<Advertisement> findByCurrentDay() {
        return this.tx(
                session -> {
                    LocalDate currentDate = LocalDate.now();
                    List<Advertisement> rsl =
                            session.createQuery("from Advertisement a where a.created = :paramCreated")
                                    .setDate("paramCreated", Date.valueOf(currentDate)).list();
                    return rsl;
                }
        );
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e){
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }







}
