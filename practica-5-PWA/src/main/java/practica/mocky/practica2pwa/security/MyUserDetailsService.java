package practica.mocky.practica2pwa.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import practica.mocky.practica2pwa.models.User;
import practica.mocky.practica2pwa.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class MyUserDetailsService  implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsernameAndActiveTrue(s)
                .orElseThrow(() -> new EntityNotFoundException("Role was not found."));
        return new MyUserDetails(user);
    }
}
