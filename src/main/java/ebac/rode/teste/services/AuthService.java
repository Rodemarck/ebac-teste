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
    private JwtService jwtService;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AuthService(JwtService jwtService, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<RegisterResponseDto> register(RegisterRequestDto register) {
        userRepository.save(User.builder().email(register.email()).hash(passwordEncoder.encode(register.password())).build());
        System.out.println(register);
        return ResponseEntity.ok(new RegisterResponseDto());
    }

    public ResponseEntity<LoginResponseDto> login(LoginRequestDto login) throws LoginException {
        var user = userRepository.findByEmail(login.email());
        if(user.isEmpty() || !user.get().testLogin(login,passwordEncoder)){
            throw new LoginException();
        }

        return ResponseEntity.ok(new LoginResponseDto(jwtService.generateToken(user.get()),JwtService.EXPIRES_IN));
    }
}
