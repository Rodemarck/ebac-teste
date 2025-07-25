package ebac.rode.teste.controller;

import ebac.rode.teste.exceptions.TaskNotFoundException;
import ebac.rode.teste.exceptions.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@ControllerAdvice
public class ErroController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        var map = new HashMap<String,Object>();
        map.put("timestamp", LocalDateTime.now().toLocalTime());
        map.put("path",request.getRequestURI());
        map.put("status",new HashMap<>(){{
            put("code",400);
            put("erro","Bad Request");
        }});
        map.put("message","Erro de validação.");

        var mapErros = new HashMap<String,String>();
        BindingResult bindingResult = ex.getBindingResult();
        bindingResult.getFieldErrors().stream()
                .forEach(fieldError -> mapErros.put(fieldError.getField(),fieldError.getDefaultMessage()));

        map.put("Errors:",mapErros);

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Object> LoginException(LoginException ex, HttpServletRequest request) {
        var map = new HashMap<String,Object>();
        map.put("timestamp", LocalDateTime.now().toLocalTime());
        map.put("path",request.getRequestURI());
        map.put("status",new HashMap<>(){{
            put("code",401);
            put("erro","Unauthorized");
        }});
        map.put("message","Erro de validação.");


        var mapErros = new HashMap<String,String>(){{
            put("email","Email ou senha incorretos");
            put("password","Email ou senha incorretos");
        }};

        map.put("Errors:",mapErros);

        return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> RuntimeException(RuntimeException ex, HttpServletRequest request){
        return new ResponseEntity<>("faiou",HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Object> TaskNotFoundException(TaskNotFoundException ex, HttpServletRequest request) {
        var map = new HashMap<String,Object>();
        map.put("timestamp", LocalDateTime.now().toLocalTime());
        map.put("path",request.getRequestURI());
        map.put("status",new HashMap<>(){{
            put("code",404);
            put("erro","Not Found");
        }});
        map.put("message","Não encontrado");


        var mapErros = new HashMap<String,String>(){{
            put("task","Nenhuma task pertencente a você foi encontrada");
        }};

        map.put("Errors:",mapErros);

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> UserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        var map = new HashMap<String,Object>();
        map.put("timestamp", LocalDateTime.now().toLocalTime());
        map.put("path",request.getRequestURI());
        map.put("status",new HashMap<>(){{
            put("code",404);
            put("erro","Not Found");
        }});
        map.put("message","Não encontrado");


        var mapErros = new HashMap<String,String>(){{
            put("user","Nenhum usuario foi encontrada");
        }};

        map.put("Errors:",mapErros);

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex, HttpServletRequest request) {
        var map = new HashMap<String,Object>();
        map.put("timestamp", LocalDateTime.now().toLocalTime());
        map.put("path",request.getRequestURI());
        map.put("status",new HashMap<>(){{
            put("code",401);
            put("erro","Unauthorized");
        }});
        map.put("message","Erro de validação.");


        var mapErros = new HashMap<String,String>(){{
            put("email","Email já cadastrado");
        }};


        map.put("Errors:",mapErros);

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleNotReadableExceptions(HttpMessageNotReadableException ex, HttpServletRequest request){
        var map = new HashMap<String,Object>();
        map.put("timestamp", LocalDateTime.now().toLocalTime());
        map.put("path",request.getRequestURI());
        map.put("status",new HashMap<>(){{
            put("code",500);
            put("erro","Internal Server Error");
        }});
        map.put("message",ex.getMessage());
        return ResponseEntity.ok(map);
    }


//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> Exception(Exception ex, HttpServletRequest request) {
//        var map = new HashMap<String,Object>();
//        map.put("timestamp", LocalDateTime.now().toLocalTime());
//        map.put("path",request.getRequestURI());
//        map.put("status",new HashMap<>(){{
//            put("code",500);
//            put("erro","Internal Server Error");
//        }});
//        map.put("message",ex.getMessage());
//
//
//        var mapErros = new HashMap<String,Object>(){{
//
//            put("paramters",request.getParameterMap());
//        }};
//
//        map.put("Errors:",mapErros);
//
//        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
//    }



}
