package ru.job4j.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public static Driver of(String name) {
        Driver driver = new Driver();
        driver.name = name;
        return driver;
    }
}
