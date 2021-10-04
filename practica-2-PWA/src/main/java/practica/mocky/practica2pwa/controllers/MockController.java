package practica.mocky.practica2pwa.controllers;

import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import practica.mocky.practica2pwa.models.Headers;
import practica.mocky.practica2pwa.models.Mock;
import practica.mocky.practica2pwa.services.MockService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class MockController {
    private final MockService mockService;

    public MockController(MockService mockService) {
        this.mockService = mockService;
    }

    @PostMapping("/mocky")
    @PreAuthorize("isAuthenticated()")
    public Mock createMock(@RequestBody Mock mock){
        return mockService.create(mock);
    }

    @PutMapping("/mocky/{id}")
    @PreAuthorize("isAuthenticated()")
    public Mock updateMock(@RequestBody Mock mock, @PathVariable Integer id){
          Mock old = mockService.findMockById(id);
          return mockService.update(old, mock);
    }

    @RequestMapping(value = "/mock/{mockId}")
    public ResponseEntity<String> mockConsult(@PathVariable String mockId){
        Mock mock = mockService.findMockByMockId(mockId);
        MultiValueMap<String, String> headers = mock.headersList();
        HttpStatus httpStatus = HttpStatus.valueOf(mock.getHttpStatus());
        if (mock.getDelayResponse() > 0 && mock.getDelayResponse()!= null){
            try {
                TimeUnit.SECONDS.sleep(mock.getDelayResponse());
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        return new ResponseEntity<String>(mock.getBodyMessage(), headers, httpStatus);
    }
}
