package ksolve.virtualclassroom.jwt;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private final String secretKey = "daf66e01593f61a15b857cf433aae03a005812b31234e149036bcc8dee755dbb";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            if (checkJWTToken(request, response)) {
                Claims claims = validateToken(request);
                if (claims != null && claims.get("authorities") != null) {
                    setUpSpringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

    private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse response) {
    	  String authenticationHeader = request.getHeader(HEADER);
          if (authenticationHeader != null) {
              logger.info("Found Authorization header: " + authenticationHeader);
          } else {
              logger.warn("No Authorization header found in request");
          }
          return authenticationHeader != null && authenticationHeader.startsWith(PREFIX);
      }

	private Claims validateToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
            logger.info("Validating token: " + jwtToken);
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody();
        } catch (ExpiredJwtException e) {
            logger.error("JWT Token expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT Token: " + e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Malformed JWT Token: " + e.getMessage());
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: " + e.getMessage());
        }
        return null;
    }


    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List<String>) claims.get("authorities");
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

	/*
	 * private boolean checkJWTToken(HttpServletRequest request) { String
	 * authenticationHeader = request.getHeader(HEADER); if (authenticationHeader !=
	 * null) { logger.info("Found Authorization header: " + authenticationHeader); }
	 * else { logger.warn("No Authorization header found in request"); } return
	 * authenticationHeader != null && authenticationHeader.startsWith(PREFIX); }
	 */

}
