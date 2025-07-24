package ebac.rode.teste.services;

import ebac.rode.teste.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@Service
public class JwtService {
    @Autowired
    JwtEncoder encoder;

    private static final String ISSUER = "EBAC-TASKER";
    public static final long EXPIRES_IN = 3_600L;

    public String generateToken(User user) {
        var creationTime = now();
        var permissions = " : ";
        var claims = JwtClaimsSet.builder()
                .issuer(ISSUER)
                .issuedAt(creationTime)
                .expiresAt(creationTime.plusSeconds(EXPIRES_IN))
                .subject(user.getEmail())
                .claim("permissions",permissions)
                .build();
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    private Instant now() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
    }

}