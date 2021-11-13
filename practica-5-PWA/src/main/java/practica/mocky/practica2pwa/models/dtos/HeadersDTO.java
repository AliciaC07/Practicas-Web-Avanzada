package practica.mocky.practica2pwa.models.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class HeadersDTO {

    private String keyHeader;

    private String value;
}
