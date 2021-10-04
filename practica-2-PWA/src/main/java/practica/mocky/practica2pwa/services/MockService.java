package practica.mocky.practica2pwa.services;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import practica.mocky.practica2pwa.config.JwGen;
import practica.mocky.practica2pwa.config.RamEncoder;
import practica.mocky.practica2pwa.models.Mock;
import practica.mocky.practica2pwa.models.User;
import practica.mocky.practica2pwa.models.dtos.MockInsert;
import practica.mocky.practica2pwa.repositories.MockRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class MockService {

    private final MockRepository mockRepository;
    private final UserService userService;
    private final JwGen jwGen = new JwGen();
    private final RamEncoder ramEncoder = new RamEncoder();
    private String endpoint = "https://localhost:8080/";

    public MockService(MockRepository mockRepository, UserService userService) {
        this.mockRepository = mockRepository;
        this.userService = userService;
    }

    @Transactional
    public Mock create(MockInsert mocky){
        Mock mock = new ModelMapper().map(mocky, Mock.class);
        mock.setHeaders(convert(mocky));
        User user = userService.findById(mocky.getUser());
        mock.setUser(user);
        if (mock.getJwtValidationActive()){
            mock.setJwtValidation(jwGen.tokenCreated(mock));
        }
        String id = ramEncoder.encode(mock.getNameMock());
        String mockId = mock.getNameMock()+"-"+id;
        mock.setMockId(mockId);
        mock.setEndpoint(endpoint+"mock/"+mock.getMockId());
        long expSecs = System.currentTimeMillis() +TimeUnit.SECONDS.toMillis(mock.getExpiration());
        Date exp = new Date(expSecs);
        mock.setExpirationDate(exp);
        return mockRepository.save(mock);
    }

    @Transactional
    public Mock update(Mock old, Mock mock){
        old.setExpiration(mock.getExpiration());
        old.setBodyMessage(mock.getBodyMessage());
        old.setDelayResponse(mock.getDelayResponse());
        old.setContentType(mock.getContentType());
        old.setHeaders(mock.getHeaders());
        old.setDescription(mock.getDescription());
        long expSecs = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(mock.getExpiration());
        Date exp = new Date(expSecs);
        old.setExpirationDate(exp);
        old.setMethod(mock.getMethod());
        if (old.getJwtValidationActive()){
            old.setJwtValidation(jwGen.tokenCreated(mock));
        }
        return mockRepository.save(old);

    }

    public Mock findMockById(Integer id){
        return mockRepository.findMockByIdAndActiveTrue(id)
                .orElseThrow(()-> new EntityNotFoundException("Mock not Found"));
    }

    public Mock findMockByMockId(String mockId){
        return mockRepository.findMockByMockIdAndActiveTrue(mockId)
                .orElseThrow(()-> new EntityNotFoundException("Mock request not found"));
    }

    public String convert(MockInsert mocky){
        MultiValueMap<String, String> map = new HttpHeaders();
        for (var m: mocky.getHeaders()) {
            map.add(m.getKey(), m.getValue());
        }
        Gson gson = new Gson();
        return gson.toJson(map);
    }

}
