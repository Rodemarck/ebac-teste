package ebac.rode.teste.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${app.jwt.key.private}")
    private RSAPrivateKey privateKey;

    @Value("${app.jwt.key.public}")
    private RSAPublicKey publicKey;





    @SneakyThrows
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()  // Permite acesso a qualquer URL que comece com /auth
                        .anyRequest().permitAll())  // Permite todas as requisições, sem necessidade de autenticação
                .csrf(csrf -> csrf.disable())  // Desabilita CSRF (não é recomendado em produção, mas para testes é ok)
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors -> cors.configurationSource(request -> {
                    var config = new org.springframework.web.cors.CorsConfiguration();
                    config.addAllowedOrigin("*"); // Permite qualquer origem
                    config.addAllowedMethod("*"); // Permite todos os métodos HTTP
                    config.addAllowedHeader("*"); // Permite todos os cabeçalhos
                    return config;
                }))
        ;
        return http.build();
    }

    @Bean
    public JwtEncoder jwtEncoder() throws IOException {
        return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(new RSAKey.Builder(publicKey).privateKey(privateKey).build())));
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
