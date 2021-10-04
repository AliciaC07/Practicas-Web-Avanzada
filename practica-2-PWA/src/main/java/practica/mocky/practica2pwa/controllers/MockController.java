package practica.mocky.practica2pwa.controllers;

import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import practica.mocky.practica2pwa.models.HttpStatusCode;
import practica.mocky.practica2pwa.models.Mock;
import practica.mocky.practica2pwa.models.dtos.MockInsert;
import practica.mocky.practica2pwa.services.HttpStatusCodeService;
import practica.mocky.practica2pwa.services.MockService;

import java.util.concurrent.TimeUnit;

@RestController
public class MockController {
    private final MockService mockService;
    private final HttpStatusCodeService httpStatusCodeService;

    public MockController(MockService mockService, HttpStatusCodeService httpStatusCodeService) {
        this.mockService = mockService;
        this.httpStatusCodeService = httpStatusCodeService;
    }

    @PostMapping("/mocky")
    @PreAuthorize("isAuthenticated()")
    public Mock createMock(@RequestBody MockInsert mock){
        return mockService.create(mock);
    }

    @PutMapping("/mocky/{id}")
    @PreAuthorize("isAuthenticated()")
    public Mock updateMock(@RequestBody Mock mock, @PathVariable Integer id){
          Mock old = mockService.findMockById(id);
          return mockService.update(old, mock);
    }

    @GetMapping("/mocky/{id}")
    @PreAuthorize("isAuthenticated()")
    public Mock search(@PathVariable String id){
        return mockService.findMockByMockId(id);
    }

    @RequestMapping(value = "/mock/{mockId}")
    public ResponseEntity<String> mockConsult(@PathVariable String mockId){
        Mock mock = mockService.findMockByMockId(mockId);
        MultiValueMap<String, String> headers = mock.headersList();
        HttpStatusCode httpStatusCode = httpStatusCodeService.findByName(mock.getHttpStatus());
        HttpStatus httpStatus = HttpStatus.valueOf(httpStatusCode.getCode());
        if (mock.getDelayResponse() > 0 && mock.getDelayResponse()!= null){
            try {
                TimeUnit.SECONDS.sleep(mock.getDelayResponse());
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        return new ResponseEntity<>(mock.getBodyMessage(), headers, httpStatus);
    }
}
