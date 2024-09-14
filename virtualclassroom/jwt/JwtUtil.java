package ksolve.virtualclassroom.jwt;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtUtil {

	@Value("${bearer.token.expiration}")
	private Integer bearerTokenExpiration;

    private final String secretKey = "daf66e01593f61a15b857cf433aae03a005812b31234e149036bcc8dee755dbb";

    public String generateToken(String email, Collection<? extends GrantedAuthority> collection) {
        if (bearerTokenExpiration == null) {
            throw new IllegalStateException("bearer.token.expiration is not set.");
        }
        
        String token = Jwts.builder()
                .setId("loginJWT")
                .setSubject(email)
                .claim("authorities",
                        collection.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + bearerTokenExpiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        
        // Print the token
        System.out.println("Generated Token: " + token);
        
        return token;
    }

}

