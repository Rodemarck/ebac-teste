package ebac.rode.teste;

import ebac.rode.teste.controller.dto.LoginRequestDto;
import ebac.rode.teste.controller.dto.RegisterRequestDto;
import ebac.rode.teste.entities.User;
import ebac.rode.teste.repositories.UserRepository;
import ebac.rode.teste.services.AuthService;
import ebac.rode.teste.services.JwtService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.Matchers.*;


import java.util.Optional;

@SpringBootTest
public class AuthControllerTest {
    @Mock
    private UserRepository userRepository;
    private AuthService authService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    private User user1;

    @LocalServerPort
    private int port;
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost"; // Sem precisar de porta fixada
    }

    @BeforeEach
    public void setupRestAssured() {
        RestAssured.port = port; // Usando a porta aleatória do servidor para os testes
    }

    @BeforeEach
    public void setUserRepository(){
        MockitoAnnotations.openMocks(this);
        authService = new AuthService(jwtService,userRepository,passwordEncoder);
        user1 = User.builder().email("teste@teste.teste").hash(passwordEncoder.encode("teste")).build();
    }

    @Test
    public void registerTest(){
        authService.register(new RegisterRequestDto(user1.getEmail(),user1.getHash()));
    }

    @Test
    public void testLoginWithWrongPassword(){
        when(userRepository.findByEmail("teste@teste.teste")).thenReturn(Optional.of(user1));
        RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(new LoginRequestDto(user1.getEmail(),"teste2"))
                .when()
                .post("/auth/login")
                .then()
                .statusCode(401);
    }

    @Test
    public void testSuccesfulyLogin(){
        when(userRepository.findByEmail("teste@teste.teste")).thenReturn(Optional.of(user1));
//        when(jwtService.generateToken(user1)).thenReturn("eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJFQkFDLVRBU0tFUiIsInN1YiI6InJvZGVAZW1haWwuY29tIiwiZXhwIjoxNzUzMzgyNzUyLCJpYXQiOjE3NTMzNzkxNTIsInBlcm1pc3Npb25zIjoiIDogIn0.WwBbx6jbu-UMnTV6B3Q3Fomvv7cbRrp5M00ivW9lFM7TQeTMi_aEkpGUb_F5USOJzb6tkrPJeTVdBhOnAbqJFnzyIa8_vVEuD0D3XS43AADujzaFnNoFgi-Il_8vSDotstd2swbofmPmKQytU17bziJBSxu4KoLJJMMwT6is-vGa4egnD1W1QaFA_JfS2EGWgz-CrX8lCqkzA1zZ_oWxV9ipqqRoN56NXFI-Nj7HNiwPKg2gO4AYOcDLGNEQHCsaJIVexn5K_GO_XLLLB-ojCznlNRaNnvNdKMHh8hjsiF9cs7mAZU9VZnmPEeoJmZ8SjZe1gdJ2TERrlTNSkuvccQ");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new LoginRequestDto(user1.getEmail(),"teste"))
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("expiresIn",equalTo((int)JwtService.EXPIRES_IN));

    }

    @Test
    public void testRegisterWithMultipleEmail(){
        when(userRepository.findByEmail("teste@teste.teste")).thenReturn(Optional.of(user1));
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new RegisterRequestDto(user1.getEmail(),"senha"))
                .when()
                .post("/auth/register")
                .then()
                .statusCode(401);
    }
}
