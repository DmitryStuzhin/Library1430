package ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.SecursityControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.DTO.RegistrationUserDto;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.DTO.Request;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.Services.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody Request request) {
        return authService.createAuthToken(request);
    }
    @PostMapping("/registration")
    public ResponseEntity<?> registration (@RequestBody RegistrationUserDto userDto){
        return authService.registration(userDto);
    }
}

