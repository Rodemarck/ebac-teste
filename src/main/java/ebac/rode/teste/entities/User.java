package ebac.rode.teste.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ebac.rode.teste.controller.dto.LoginRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "Usuario")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonSerialize
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "O campo e-mail está em branco.")
    @Email(message = "O campo e-mail está mal-formatado.")
    private String email;

    @NotBlank(message = "O campo senha está em branco.")
    @JsonIgnore
    private String hash;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "cargo_usuario",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "cargo_id")
    )
    private Set<Role> roles;

    public boolean testLogin(LoginRequestDto login, PasswordEncoder encoder) {
        return encoder.matches(login.password(), hash);
    }
}
