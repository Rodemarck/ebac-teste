package ebac.rode.teste.services;

import ebac.rode.teste.controller.dto.LoginRequestDto;
import ebac.rode.teste.controller.dto.LoginResponseDto;
import ebac.rode.teste.controller.dto.RegisterRequestDto;
import ebac.rode.teste.controller.dto.RegisterResponseDto;
import ebac.rode.teste.entities.User;
import ebac.rode.teste.exceptions.LoginException;
import ebac.rode.teste.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public ResponseEntity<RegisterResponseDto> register(RegisterRequestDto register) {
        userRepository.save(User.builder().email(register.email()).hash(passwordEncoder.encode(register.password())).build());
        System.out.println(register);
        return ResponseEntity.ok(new RegisterResponseDto());
    }

    public ResponseEntity<LoginResponseDto> login(LoginRequestDto login) {
        var user = userRepository.findByEmail(login.email());
        if(user.isEmpty() || !user.get().testLogin(login,passwordEncoder)){
            throw new LoginException();
        }

        return ResponseEntity.ok(new LoginResponseDto(jwtService.generateToken(user.get()),JwtService.EXPIRES_IN));
    }
}
