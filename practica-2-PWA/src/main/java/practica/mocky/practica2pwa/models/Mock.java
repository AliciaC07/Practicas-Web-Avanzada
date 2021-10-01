package practica.mocky.practica2pwa.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Mock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nameMock;

    @Column(unique = true)
    private String mockId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private String bodyMessage;

    @Column(nullable = false)
    private Integer delayResponse;

    @Column(nullable = false)
    private Integer expiration;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @Column
    private String jwtValidation;

    @OneToMany
    private List<Headers> headers = new ArrayList<>();

    @ManyToOne
    private User user;
}
