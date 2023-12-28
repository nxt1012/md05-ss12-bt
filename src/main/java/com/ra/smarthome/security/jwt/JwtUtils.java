package com.ra.smarthome.security.jwt;

import com.ra.smarthome.security.userdetails.UserDetailsImpl;
import io.jsonwebtoken.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.util.Date;

@Component
@Slf4j
public class JwtUtils {
    @Value("${nxt.app.jwtSecret}")
    private String jwtSecret;
    @Value("${nxt.app.jwtExpirationMs}")
    private int jwtExpirationMs;
    @Value("${nxt.app.jwtCookieName}")
    private String jwtCookie;
    @Value("${server.servlet.context-path}")
    private String apiPath;

    // GET TOKEN FROM COOKIE
    public String getTokenFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }
    // GENERATE TOKEN
    public ResponseCookie generateCookie(UserDetailsImpl userPrincipal) {
        String token = generateTokenFromUsername(userPrincipal.getUsername());
        return ResponseCookie.from(jwtCookie, token).path(apiPath).maxAge(24 * 60 * 60).httpOnly(true).build();
    }
    // FIXME: truyền null vào vị trí được đánh dấu @NotNull
    public ResponseCookie getCleanCookie() {
        return ResponseCookie.from(jwtCookie, null).path(apiPath).build();
    }
    public String generateTokenFromUsername(String username) {
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // VALIDATE TOKEN
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Authentication failed - Invalid Token Format: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Authentication failed - Unsupported Token Format: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Authentication failed - Expired Token: {}", e.getMessage());
        } catch (SignatureException e) {
            log.error("Authentication failed - Invalid Signature: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("Authentication failed - Empty Token: {}", e.getMessage());
        }
        return false;
    }

    // GENERATE USERNAME FROM TOKEN
    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
}
