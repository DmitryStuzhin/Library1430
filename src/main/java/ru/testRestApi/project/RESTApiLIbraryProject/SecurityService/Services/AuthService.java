package ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.DTO.JwtResponse;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.DTO.RegistrationUserDto;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.DTO.Request;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.DTO.UserDto;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.Exceptions.AppError;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.User;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.Services.ServiceUser;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.Utils.JwtTokensUtil;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ServiceUser userService;
    private final JwtTokensUtil jwtTokensUtil;
    private final AuthenticationManager auth;
    public ResponseEntity<?> createAuthToken(@RequestBody Request request) {
        try {
            auth.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                    request.getPassword()));
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect login or password"),HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = jwtTokensUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> registration (@RequestBody RegistrationUserDto userDto) {
        if(userService.findUserByUsername(userDto.getUsername()).isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        User user = userService.createNameUser(userDto);
        return ResponseEntity.ok(new UserDto(user.getId(), user.getUsername(), user.getClass_number()));
    }
}