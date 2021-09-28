package practica.mocky.practica2pwa.security;


import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import practica.mocky.practica2pwa.config.ApiResponse;
import practica.mocky.practica2pwa.models.Role;
import practica.mocky.practica2pwa.models.User;
import practica.mocky.practica2pwa.models.dtos.LoginRequest;
import practica.mocky.practica2pwa.models.dtos.SignUpRequest;
import practica.mocky.practica2pwa.models.dtos.UserLoginDto;
import practica.mocky.practica2pwa.security.jwt.JwtUtil;
import practica.mocky.practica2pwa.services.UserService;

import javax.validation.Valid;

@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;



    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          JwtUtil jwtUtil,
                          ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;

    }

    @PostMapping("/auth/signup")
    public ApiResponse registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
        User user = modelMapper.map(signUpRequest, User.class);
        Role role = new Role();
        role.setName(signUpRequest.getRole());
        user.setRole(role);
        userService.save(user);
        return new ApiResponse(HttpStatus.OK, "User registered sucessfully.");
    }

    @PostMapping("/auth/login")
    public UserLoginDto doLogin(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtUtil.generateJwtToken(auth);
        MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
        UserLoginDto login = modelMapper.map(userDetails.getUser(), UserLoginDto.class);
        login.setLastname(userDetails.getUser().getLastName());
        login.setToken(jwt);
        return login;
    }

}
