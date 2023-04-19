package com.javaproject.ecommerce.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "lokesh";

    private static final  int TOKEN_VALIDITY = 3600 *5;

    public String getUserNameFormToken(String token){
        return getClaimFormToken(token , Claims::getSubject);
    }

    public  <T> T getClaimFormToken(String token , Function<Claims ,T> claimResolver){
        final Claims claims = getAllClaimsFormToken(token);
        return claimResolver.apply(claims);
    }

    private Claims getAllClaimsFormToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token , UserDetails userDetails){

        final String userName = getUserNameFormToken(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFormToken(token);
        return expiration.before(new Date());

    }

    private Date getExpirationDateFormToken(String token){
        return getClaimFormToken(token , Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails){
        Map<String , Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
