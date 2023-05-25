package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Car {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private int year;
    @NotNull
    private String color;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date created;
    private String link;
    private String description;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "idCar", orphanRemoval = true)
    private Set<Booking> bookingSet = new HashSet<>();
}
