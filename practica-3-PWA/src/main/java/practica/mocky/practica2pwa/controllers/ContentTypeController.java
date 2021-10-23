package practica.mocky.practica2pwa.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import practica.mocky.practica2pwa.models.ContentType;
import practica.mocky.practica2pwa.services.ContentTypeService;

@RestController
public class ContentTypeController {
    private final ContentTypeService contentTypeService;


    public ContentTypeController(ContentTypeService contentTypeService) {
        this.contentTypeService = contentTypeService;
    }

    @GetMapping("/content-type")
    @PreAuthorize("isAuthenticated()")
    public Iterable<ContentType> findAll(){
        return contentTypeService.findAll();
    }

    @GetMapping("/content-type/{name}")
    @PreAuthorize("isAuthenticated()")
    public ContentType findByName(@PathVariable String name){
        return contentTypeService.findContentTypeByName(name);
    }

}
