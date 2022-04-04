package ru.job4j.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Advertisement;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

public class AdsRepository {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public AdsRepository() {
    }

    private static final class LazyAd {
        private static final AdsRepository INST = new AdsRepository();
    }

    public static AdsRepository instOf() {
        return LazyAd.INST;
    }

    public Advertisement addAd(Advertisement advertisement) {
        this.tx(session -> session.save(advertisement));
        return advertisement;
    }

    public boolean updateStatusAd(int id) {
        return this.tx(
                session -> {
                    int rsl = session.createQuery("update Advertisement a set a.done = :newDone where a.id = :fId")
                            .setParameter("newDone", true)
                            .setParameter("fId", id)
                            .executeUpdate();
                    return rsl != 0;
                });
    }

    public boolean updatePhotoAd(int id) {
        return this.tx(
                session -> {
                    int rsl = session.createQuery("update Advertisement a set a.photo = :newPhoto where a.id = :fId")
                            .setParameter("newPhoto", true)
                            .setParameter("fId", id)
                            .executeUpdate();
                    return rsl != 0;
                });
    }

    public Advertisement findAdById(int id) {
        return this.tx(
                session -> (Advertisement) session.createQuery("from Advertisement a where a.id = :paramId").setParameter("paramId", id).uniqueResult()
        );
    }

    public List<Advertisement> findAllAds() {
        return this.tx(
                session -> session.createQuery("from Advertisement").list()
        );
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
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
