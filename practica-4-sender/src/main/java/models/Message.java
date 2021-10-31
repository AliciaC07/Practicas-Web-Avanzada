package models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Message {

    private Integer idDevice;


    private Integer temperature;


    private Integer humidity;


    private LocalDateTime dateGeneration;


}
