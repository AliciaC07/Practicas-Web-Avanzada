package practica.mocky.practica2pwa.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import practica.mocky.practica2pwa.config.JwGen;
import practica.mocky.practica2pwa.models.Mock;
import practica.mocky.practica2pwa.models.dtos.MockInsert;
import practica.mocky.practica2pwa.services.MockService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
public class MockController {
    private final MockService mockService;

    private JwGen jwGen = new JwGen();

    public MockController(MockService mockService) {
        this.mockService = mockService;
    }

    @PostMapping("/mocky")
    @PreAuthorize("isAuthenticated()")
    public Mock createMock(@RequestBody MockInsert mock){
        return mockService.create(mock);
    }

    @PutMapping("/mocky/{id}")
    @PreAuthorize("isAuthenticated()")
    public Mock updateMock(@RequestBody MockInsert mock, @PathVariable Integer id){
          Mock old = mockService.findMockById(id);
          return mockService.update(old, mock);
    }

    @GetMapping("/mocky/user/{id}")
    @PreAuthorize("isAuthenticated()")
    public Iterable<Mock> findMocksByUserId(@PathVariable Integer id){
        return mockService.findMocksByUserId(id);
    }

    @GetMapping("/mocky/{id}")
    @PreAuthorize("isAuthenticated()")
    public Mock search(@PathVariable String id){
        return mockService.findMockByMockId(id);
    }

    @RequestMapping(value = "/mock/{mockId}")
    public ResponseEntity<String> mockConsult(@PathVariable String mockId, HttpServletRequest httpServletRequest){
        Mock mock = mockService.findMockByMockId(mockId);
        if (mock.getExpirationDate().getTime() < System.currentTimeMillis()){
            return ResponseEntity.ok("The given endpoint has expired");
        }
        if (mock.getJwtValidationActive()){
            if (httpServletRequest.getHeader("jwt-validation") == null){
                return ResponseEntity.ok("Header for token was not found");
            }else {
                if (!httpServletRequest.getHeader("jwt-validation").isEmpty()){
                    if (Objects.equals(mock.getJwtValidation(), httpServletRequest.getHeader("jwt-validation"))){
                        if (jwGen.tokenVerify(mock.getNameMock(), mock.getJwtValidation())){
                            return mockService.responseForMock(mock);
                        }else {
                            return ResponseEntity.ok("The given token inst valid");
                        }
                    }else {
                        return ResponseEntity.ok("The given token inst valid");
                    }

                }else {
                    return ResponseEntity.ok("Token not found");
                }
            }

        }
        return mockService.responseForMock(mock);

    }
}
