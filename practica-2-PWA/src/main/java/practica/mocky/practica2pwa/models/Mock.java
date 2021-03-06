package practica.mocky.practica2pwa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    private String endpoint;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private String charset;

    @Column(nullable = false)
    private String httpStatus;

    @Column(nullable = false)
    private String bodyMessage;

    @Column(nullable = false)
    private Integer delayResponse;

    @Column(nullable = false)
    private Integer expiration;

    @Column(nullable = false)
    private Date expirationDate;

    @Column
    private String jwtValidation;

    @Column
    private Boolean jwtValidationActive = false;

    @Column
    private String headers;


    @ManyToOne
    @JsonIgnore
    private User user;

    @Column
    @JsonIgnore
    private Boolean active = true;

    public MultiValueMap<String, String> headersList(){
        Gson gson = new Gson();
        MultiValueMap<String, String> headerList = new HttpHeaders();
        headerList.add("content-type", this.contentType+";  charset="+this.charset);
        MultiValueMap<String, String> p= gson.fromJson(this.headers, HttpHeaders.class);
        headerList.addAll(p);
        return headerList;
    }

}
