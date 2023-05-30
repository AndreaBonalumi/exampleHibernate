package hibernate.repository;


import entity.Booking;

import java.util.List;

public interface BookingDao {
    List<Booking> getAll();
    List<Booking> getByIdUser(int id);
    void insert(Booking booking);
    void delete(Booking booking);
    void edit(Booking booking);
}
