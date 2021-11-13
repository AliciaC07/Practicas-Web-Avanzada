package practica.mocky.practica2pwa.security.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practica.mocky.practica2pwa.models.Role;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {

    private Integer id;

    private String token;

    private String username;

    private String name;

    private String lastName;

    private String email;

    private Role role;

    private String type = "Bearer";
}
