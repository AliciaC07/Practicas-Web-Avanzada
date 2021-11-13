package practica.mocky.practica2pwa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column
    @ColumnDefault("true")
    @JsonIgnore
    private Boolean active = true;

    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updateTime;

    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Mock> mocks = new ArrayList<>();

}
