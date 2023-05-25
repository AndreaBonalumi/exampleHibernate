package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Generated;

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

}
