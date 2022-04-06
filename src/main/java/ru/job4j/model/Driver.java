package ru.job4j.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Car> cars = new HashSet<>();

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public static Driver of(String name) {
        Driver driver = new Driver();
        driver.name = name;
        return driver;
    }
}
