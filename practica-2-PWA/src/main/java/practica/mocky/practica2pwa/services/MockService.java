package practica.mocky.practica2pwa.services;

import org.springframework.stereotype.Service;
import practica.mocky.practica2pwa.models.Mock;
import practica.mocky.practica2pwa.repositories.MockRepository;

import javax.transaction.Transactional;

@Service
public class MockService {

    private final MockRepository mockRepository;

    public MockService(MockRepository mockRepository) {
        this.mockRepository = mockRepository;
    }

    @Transactional
    public Mock create(Mock mock){
        return mockRepository.save(mock);
    }

//    @Transactional
//    public Mock update(Mock old, Mock mock){
//
//    }

}
