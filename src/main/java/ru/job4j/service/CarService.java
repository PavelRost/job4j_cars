package ru.job4j.service;

import ru.job4j.model.Car;
import ru.job4j.repository.CarRepository;

import java.util.List;

public class CarService {

    public CarService() {
    }

    private static final class LazyCar {
        private static final CarService INST = new CarService();
    }

    public static CarService instOf() {
        return LazyCar.INST;
    }

    public List<Car> findAllCars() {
        return CarRepository.instOf().findAllCars();
    }

    public Car findCarById(int id) {
        return CarRepository.instOf().findCarById(id);
    }
}
