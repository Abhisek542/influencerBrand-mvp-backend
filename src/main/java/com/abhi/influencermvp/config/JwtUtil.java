package com.abhi.influencermvp.config;



import com.abhi.influencermvp.enums.Role;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET = "THIS_IS_A_SECRET_KEY_12345678901234567890";

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

  public String generateToken(String email, Role role) {

      return Jwts.builder()
              .setSubject(email)
              .claim("role", role.name())
              .setIssuedAt(new Date())
              .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) // 1 hour
              .signWith(key)
              .compact();
  }
  public String extractEmailFromToken(String token) {
       return Jwts.parserBuilder()
               .setSigningKey(key)
               .build()
               .parseClaimsJws(token).getBody().getSubject();
  }
  public Role extractRoleFromToken(String token) {
     String role = Jwts.parserBuilder()
             .setSigningKey(key)
             .build()
             .parseClaimsJws(token).getBody().get("role",String.class);
     return Role.valueOf(role);
  }
  public boolean validateToken(String token) {

      try{
          Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
          return true;
      }catch (JwtException e){
          return false;
      }

  }

}