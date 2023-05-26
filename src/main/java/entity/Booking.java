package entity;


import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Booking {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateBookingStart;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date getDateBookingEnd;

    @ManyToOne
    private User idUser;

    @ManyToOne
    private Car idCar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateBookingStart() {
        return dateBookingStart;
    }

    public void setDateBookingStart(Date dateBookingStart) {
        this.dateBookingStart = dateBookingStart;
    }

    public Date getGetDateBookingEnd() {
        return getDateBookingEnd;
    }

    public void setGetDateBookingEnd(Date getDateBookingEnd) {
        this.getDateBookingEnd = getDateBookingEnd;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Car getIdCar() {
        return idCar;
    }

    public void setIdCar(Car idCar) {
        this.idCar = idCar;
    }
}
