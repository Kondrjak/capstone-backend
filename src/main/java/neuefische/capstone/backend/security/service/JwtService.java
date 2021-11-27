package neuefische.capstone.backend.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

/**
 * Handles creation and reading of jwTokens (jot's)
 * is configured by
 *      secret key string:      jwt.user.authentication.secret.string
 *      time till expiration:   jwt.user.authentication.hours.till.expired
 *      algorithm enum index:   jwt.service.signature.algorithm.enum.index
 */
@Service
public class JwtService {
    @Value("${jwt.service.secret.string}")
    private String JWT_SECRET;
    @Value("${jwt.service.hours.till.expired}")
    private short numberOfHours;
    @Value("${jwt.service.signature.algorithm}")
    private String algorithm;

    public String createToken(
            Map<String, Object> claims,
            String username) {
        final Date now = Date.from(Instant.now());
        final Date nowInSomeHours = Date
                .from(Instant.now().
                plus(Duration.ofHours(numberOfHours)));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(nowInSomeHours)
                .signWith(SignatureAlgorithm.forName(algorithm), JWT_SECRET)
                // compact method builds and serialises to json
                .compact();
    }

    public String parseUsername(String token) {
        // claims is a json mapper with predefined standard keys:
        // iss - issuer, who granted the token
        // sub - subject, who gets the token
        // aud - audience
        // exp - expiration date
        // nbf - not before
        // iat - issued at this date
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
