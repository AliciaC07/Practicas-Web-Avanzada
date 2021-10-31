package jms.practica4pwa.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer idDevice;

    @Column
    private Integer temperature;

    @Column
    private Integer humidity;

    @Column
    private LocalDateTime dateGeneration;
}
