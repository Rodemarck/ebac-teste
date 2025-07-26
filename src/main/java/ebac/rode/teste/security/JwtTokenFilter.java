package ebac.rode.teste.security;

import ebac.rode.teste.exceptions.UserNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if(request.getRequestURI().startsWith("/auth")){
            filterChain.doFilter(request, response);
            return;
        }

        // Se o token não estiver presente ou estiver vazio
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            throw new UserNotFoundException();
        }

        // Continue com a validação do token (esse passo é feito pelo Spring Security com o JwtDecoder)
        filterChain.doFilter(request, response);
    }
}