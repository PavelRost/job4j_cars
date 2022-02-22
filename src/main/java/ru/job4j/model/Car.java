package ru.job4j.model;

import javax.persistence.*;

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

    public static Car of(String name, Engine engine) {
        Car car = new Car();
        car.name = name;
        car.engine = engine;
        return  car;
    }
}
