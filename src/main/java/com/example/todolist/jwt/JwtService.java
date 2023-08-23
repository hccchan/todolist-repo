package com.example.todolist.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private final static String SECRET_KEY = "and0LXNlY3JldC1rZXktand0LXNlY3JldC1rZXktand0LXNlY3JldC1rZXktand0LXNlY3JldC1rZXk=";
    private final static String CLIENT_ID_AND_SECRET = "328948611846-jucgkq2nocam64gst4sbl3cnu7e6d8mk.apps.googleusercontent.com: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";

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaim(token);
        return claimsResolver.apply(claims);
    }

    public String extractSubject(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token){
        final String subject = extractSubject(token);
        return subject.equals(CLIENT_ID_AND_SECRET);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    private Claims extractAllClaim(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(){
        return Jwts.builder()
                .setClaims(null)
                .setSubject(CLIENT_ID_AND_SECRET)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
