package ebac.rode.teste.controller;

import ebac.rode.teste.controller.dto.LoginRequestDto;
import ebac.rode.teste.controller.dto.LoginResponseDto;
import ebac.rode.teste.controller.dto.RegisterRequestDto;
import ebac.rode.teste.controller.dto.RegisterResponseDto;
import ebac.rode.teste.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthContoller {
    @Autowired
    private AuthService authService;


    @GetMapping
    public String test(){
        return "como vai coleguinha?";
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto register){
        return authService.register(register);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto login){
        return authService.login(login);
    }

}
