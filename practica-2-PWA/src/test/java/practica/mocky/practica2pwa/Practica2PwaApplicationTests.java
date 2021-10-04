package practica.mocky.practica2pwa;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import practica.mocky.practica2pwa.config.JwGen;
import practica.mocky.practica2pwa.config.RamEncoder;
import practica.mocky.practica2pwa.models.Mock;
import practica.mocky.practica2pwa.models.Role;
import practica.mocky.practica2pwa.models.User;
import practica.mocky.practica2pwa.models.dtos.HeadersDTO;
import practica.mocky.practica2pwa.repositories.MockRepository;
import practica.mocky.practica2pwa.services.MockService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class Practica2PwaApplicationTests {
    private MockRepository mockRepository = Mockito.mock(MockRepository.class);


    private  MockService mockService = new MockService(mockRepository);
    @Test
    void contextLoads() {
    }

    private final RamEncoder ramEncoder = new RamEncoder();
    private final JwGen jwGen = new JwGen();

    @Test
    void testDecode(){
//        String name = "Esto es una prueba";
//        String encode  = ramEncoder.encode(name);
//        System.out.println(encode);
        String hola = "";
        Gson gson = new Gson();
        MultiValueMap<String, String> he = new HttpHeaders();
        he.add("Primero", "Este");
        he.add("Segundo", "Este");
        String p = gson.toJson(he);
        System.out.println(p);
        HeadersDTO headersDTO = gson.fromJson(p, HeadersDTO.class);
        System.out.println(headersDTO);


    }

    @Test
    void testJwtForVerificationAccessPoint(){
        Mock mock = new Mock();
        mock.setBodyMessage("pruebas");
        mock.setNameMock("Esto es la prueba con validation");
        mock.setExpiration(60);
        System.out.println(TimeUnit.SECONDS.toMillis(60));
        String token = jwGen.tokenCreated(mock);
        System.out.println(token);
        Boolean tokendeco = jwGen.tokenVerify(mock.getNameMock(), token);
        System.out.println(tokendeco);


    }

    @Test
    void testMock(){
        Mock mock = new Mock();
        mock.setNameMock("Prueba");
        mock.setMethod("GET");
        mock.setDescription("Esto es un test");
        mock.setExpiration(60);
        mock.setContentType("application/json");
        mock.setDelayResponse(3);
        Gson gson = new Gson();
        Map<String, String> hola = new HashMap<>();
        hola.put("Saludo", "Hola mmg");
        mock.setBodyMessage(gson.toJson(hola));
        User user = new User();
        user.setEmail("mmg@gmail.com");
        user.setName("Lola");
        user.setPassword("123");
        user.setUsername("Lola123");
        Role role = new Role();
        role.setName("User");
        role.setId(1);
        user.setRole(role);
        mock.setUser(user);
        //mock.setJwtValidationActive(true);
        mockService.create(mock);
        System.out.println(mock.getBodyMessage());

    }

}
