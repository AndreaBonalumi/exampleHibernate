package entity;


import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private LocalDate dateBookingStart;

    @NotNull
    private LocalDate getDateBookingEnd;

    @ManyToOne
    private User user;

    @ManyToOne
    private Car car;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateBookingStart() {
        return dateBookingStart;
    }

    public void setDateBookingStart(LocalDate dateBookingStart) {
        this.dateBookingStart = dateBookingStart;
    }

    public LocalDate getDateBookingEnd() {
        return getDateBookingEnd;
    }

    public void setDateBookingEnd(LocalDate getDateBookingEnd) {
        this.getDateBookingEnd = getDateBookingEnd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
