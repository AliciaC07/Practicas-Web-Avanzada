package practica.mocky.practica2pwa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import practica.mocky.practica2pwa.config.JwGen;
import practica.mocky.practica2pwa.config.RamEncoder;
import practica.mocky.practica2pwa.models.Mock;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class Practica2PwaApplicationTests {

    @Test
    void contextLoads() {
    }

    private final RamEncoder ramEncoder = new RamEncoder();
    private final JwGen jwGen = new JwGen();

    @Test
    void testDecode(){
        String name = "Esto es una prueba";
        String encode  = ramEncoder.encode(name);
        System.out.println(encode);

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

}
