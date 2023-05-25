package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date created;

    @NotNull
    private boolean admin;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column(name = "bd")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(unique = true)
    private String nPatente;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "idUser", orphanRemoval = true)
    private Set<Booking> bookingSet = new HashSet<>();
}
