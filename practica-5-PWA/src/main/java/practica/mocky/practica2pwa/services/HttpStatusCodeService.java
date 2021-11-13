package practica.mocky.practica2pwa.services;

import org.springframework.stereotype.Service;
import practica.mocky.practica2pwa.models.HttpStatusCode;
import practica.mocky.practica2pwa.repositories.HttpsStatusCodeRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class HttpStatusCodeService {

    private final HttpsStatusCodeRepository httpsStatusCodeRepository;

    public HttpStatusCodeService(HttpsStatusCodeRepository httpsStatusCodeRepository) {
        this.httpsStatusCodeRepository = httpsStatusCodeRepository;
    }

    public Iterable<HttpStatusCode> findAll(){
        return httpsStatusCodeRepository.findAll();
    }

    public Iterable<HttpStatusCode> findAllByType(String type){
        return httpsStatusCodeRepository.findHttpStatusCodeByType(type);
    }

    public HttpStatusCode findByCode(Integer code){
        return httpsStatusCodeRepository.findHttpStatusCodeByCode(code)
                .orElseThrow(()-> new EntityNotFoundException("Http Status Code not found"));

    }

    public HttpStatusCode findByName(String name){
        return httpsStatusCodeRepository.findHttpStatusCodeByName(name)
                .orElseThrow(()-> new EntityNotFoundException("Http Status Code not found "));
    }


}
