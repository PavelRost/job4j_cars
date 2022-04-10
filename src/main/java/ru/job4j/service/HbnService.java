package ru.job4j.service;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbnService {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public HbnService() {
    }

    private static final class Lazy {
        private static final HbnService INST = new HbnService();
    }

    public static HbnService instOf() {
        return Lazy.INST;
    }

    public SessionFactory getSessionFactory() {
        return sf;
    }
}
