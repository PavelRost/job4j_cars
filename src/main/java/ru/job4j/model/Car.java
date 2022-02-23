package ru.job4j.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Driver> drivers = new ArrayList<>();

    public void addDriver(Driver driver) {
        this.drivers.add(driver);
    }

    public static Car of(String name, Engine engine) {
        Car car = new Car();
        car.name = name;
        car.engine = engine;
        return  car;
    }
}
