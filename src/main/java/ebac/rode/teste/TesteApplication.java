package ebac.rode.teste;

import ebac.rode.teste.entities.User;
import ebac.rode.teste.repositories.TaskRepository;
import ebac.rode.teste.repositories.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = "ebac.rode.teste")
public class TesteApplication {

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(TesteApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Autowired
	TaskRepository taskRepository;
//	@SneakyThrows
//    @EventListener(ApplicationReadyEvent.class)
//	public void loadData()
//	{
//		User user1,user2;
//		System.out.println("IMPRIMINDO AQUI NO INICIOZINHO");
//		user2 = userRepository.findByEmail("teste2@teste.teste").orElseGet(() -> userRepository.save(User.builder().email("teste2@teste.teste").hash(passwordEncoder.encode("teste")).build()));
//
//		Arrays.asList(user1,user2).forEach(user->{
//			if(taskRepository.findByUser(user1).isEmpty()){
//
//			}
//		});
//	}

}
