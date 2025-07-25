package ebac.rode.teste;

import ebac.rode.teste.entities.Task;
import ebac.rode.teste.entities.User;
import ebac.rode.teste.repositories.TaskRepository;
import ebac.rode.teste.repositories.UserRepository;
import ebac.rode.teste.services.AuthService;
import ebac.rode.teste.services.JwtService;
import ebac.rode.teste.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;

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

@SpringBootTest
public class TaskControllerTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private TaskRepository taskRepository;
    private TaskService taskService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtEncoder jwtEncoder;
    private JwtService jwtService;

    @Autowired
    private JwtService jwtService2;
    private static User userA;
    private User userB;

    private Task task1;
    private Task task2;
    private Task task3;
    private Task task4;
    private Task task5;
    private Task task6;

    private static String jwt1 = "";

    @BeforeEach
    public void setUserRepository(){
        MockitoAnnotations.openMocks(this);
        jwtService = new JwtService(jwtEncoder);
        taskService = new TaskService(taskRepository,userRepository);
        userA = User.builder().email("teste@teste.teste").hash(passwordEncoder.encode("teste")).build();
        userB = User.builder().email("teste2@teste.teste").hash(passwordEncoder.encode("teste")).build();

        task1 = Task.builder().id(1L).user(userA).title("titulo 1").description("descricao").status(Task.Status.PENDING).deadLine(LocalDateTime.now().plusMonths(2)).build();
        task2 = Task.builder().id(2L).user(userA).title("titulo 2").description("descricao").status(Task.Status.PENDING).deadLine(LocalDateTime.now().plusMonths(2)).build();
        task3 = Task.builder().id(3L).user(userA).title("titulo 3").description("descricao").status(Task.Status.PENDING).deadLine(LocalDateTime.now().plusMonths(2)).build();
        task4 = Task.builder().id(4L).user(userA).title("titulo 4").description("descricao").status(Task.Status.PENDING).deadLine(LocalDateTime.now().plusMonths(2)).build();
        task5 = Task.builder().id(5L).user(userB).title("titulo 5").description("descricao").status(Task.Status.PENDING).deadLine(LocalDateTime.now().plusMonths(2)).build();
        task6 = Task.builder().id(6L).user(userB).title("titulo 6").description("descricao").status(Task.Status.PENDING).deadLine(LocalDateTime.now().plusMonths(2)).build();


        jwt1 = jwtService.generateToken(userA);
    }



    @Test
    public void tryListWithouAuthenticate(){
//        when(taskRepository.findByUser(userA)).thenReturn(Arrays.asList(task1,task2,task3,task4));
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/task/")
                .then()
                .statusCode(401);

    }

    @Test
    public void listTask(){
        when(taskRepository.findByUser(userA)).thenReturn(Arrays.asList(task1,task2,task3,task4));
        RestAssured.given()
                .header("Authorization", "Bearer " + jwt1)
                .contentType(ContentType.JSON)
                .when()
                .get("/task/")
                .then()
                .statusCode(401);
    }

}
