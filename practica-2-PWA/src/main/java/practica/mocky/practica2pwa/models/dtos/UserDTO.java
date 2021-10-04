package practica.mocky.practica2pwa.models.dtos;

import lombok.Getter;
import lombok.Setter;
import practica.mocky.practica2pwa.models.Role;

@Getter
@Setter
public class UserDTO {

    private Integer id;


    private String username;


    private String password;


    private String name;


    private String lastName;


    private String email;


    private Role role;

    private Boolean active = true;

}
