package hibernate.repository;

import entity.Car;

import java.util.List;

public interface CarDao {

    List<Car> getAll();
    Car getById(int id);
    void insert(Car car);
    void delete(Car car);
    void edit(Car car);
}
