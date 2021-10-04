package practica.mocky.practica2pwa.services;

import org.springframework.stereotype.Service;
import practica.mocky.practica2pwa.models.ContentType;
import practica.mocky.practica2pwa.repositories.ContentTypeRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class ContentTypeService {
    private final ContentTypeRepository contentTypeRepository;

    public ContentTypeService(ContentTypeRepository contentTypeRepository) {
        this.contentTypeRepository = contentTypeRepository;
    }



    public Iterable<ContentType> findAll(){
        return contentTypeRepository.findAll();
    }

    public ContentType findContentTypeByName(String name){
        return contentTypeRepository.findByName(name)
                .orElseThrow(()-> new EntityNotFoundException("Content Type not found"));
    }
}
