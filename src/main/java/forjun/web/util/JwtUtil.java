package forjun.web.util;


import forjun.web.exception.application.authentication.NotJwtGenerate;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secretKey}")
    private String secretKey;

    public String generateToken(String subject,String name, String authority) {
        try {
            return Jwts.builder()
                    .setSubject(subject)
                    .claim("userName",name)
                    .claim("authority",authority)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10시간 유효
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();

        } catch (Exception e){
            throw new NotJwtGenerate(e);
        }
    }

    public boolean validateToken(String token, String subject) {
        final String extractedSubject = extractSubject(token);
        return (extractedSubject.equals(subject) && !isTokenExpired(token));
    }

    public String extractSubject(String token) {
        return extractAllClaims(token).getSubject();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
