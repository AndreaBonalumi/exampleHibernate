package hibernate.repository;


import entity.Booking;
import entity.Car;

import java.time.LocalDate;
import java.util.List;

public interface BookingDao {
    List<Booking> getAll();
    List<Booking> getAllByUserId(int id);
    Booking getById(int id);
    List<Car> getByDate(LocalDate start, LocalDate end);
    void insert(Booking booking);
    void delete(Booking booking);
    void edit(Booking booking);
}
