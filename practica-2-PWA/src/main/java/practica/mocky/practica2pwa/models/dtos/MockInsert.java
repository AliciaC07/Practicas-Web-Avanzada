package practica.mocky.practica2pwa.models.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MockInsert {

    private Integer id;

    private String nameMock;


    private String mockId;


    private String endpoint;


    private String description;


    private String method;


    private String contentType;


    private String charset;


    private String httpStatus;


    private String bodyMessage;


    private Integer delayResponse;


    private Integer expiration;


    private Date expirationDate;


    private String jwtValidation;


    private Boolean jwtValidationActive = false;


    private List<HeadersDTO> headers = new ArrayList<>();


    private UserDTO user;

}
