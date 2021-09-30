package practica.mocky.practica2pwa.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import practica.mocky.practica2pwa.models.HttpStatusCode;
import practica.mocky.practica2pwa.services.HttpStatusCodeService;

@RestController
public class HttpStatusCodeController {
    private final HttpStatusCodeService httpStatusCodeService;

    public HttpStatusCodeController(HttpStatusCodeService httpStatusCodeService) {
        this.httpStatusCodeService = httpStatusCodeService;
    }

    @GetMapping("/httpstatuscode")
    @PreAuthorize("isAuthenticated()")
    public Iterable<HttpStatusCode> findAll(){
        return httpStatusCodeService.findAll();
    }

    @GetMapping("/httpstatuscode/{type}")
    @PreAuthorize("isAuthenticated()")
    public Iterable<HttpStatusCode> findByType(@PathVariable String type){
        return httpStatusCodeService.findAllByType(type);
    }

    @GetMapping("/httpstatuscode-code/{code}")
    @PreAuthorize("isAuthenticated()")
    public HttpStatusCode findByCode(@PathVariable Integer code){
        return httpStatusCodeService.findByCode(code);
    }

    @GetMapping("/httpstatuscode-name/{name}")
    @PreAuthorize("isAuthenticated()")
    public HttpStatusCode findByName(@PathVariable String name){
        return httpStatusCodeService.findByName(name);
    }
}
