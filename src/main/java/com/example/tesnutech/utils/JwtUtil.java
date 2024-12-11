package com.example.tesnutech.utils;

import com.example.tesnutech.pojos.LoginPojo;
import com.example.tesnutech.pojos.UserPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.lang.reflect.Type;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    ObjectMapper mapper = new ObjectMapper();

    public String extractData(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(LoginPojo loginPojo) throws JsonProcessingException {
        return generateToken(new HashMap<>(), loginPojo);
    }

    public String generateToken(Map<String, Object> extraClaims, LoginPojo loginPojo) throws JsonProcessingException {
        return buildToken(extraClaims, loginPojo, jwtExpiration);
    }

    public long getExpirationTime() {
        return jwtExpiration;
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            LoginPojo loginPojo,
            long expiration
    ) throws JsonProcessingException {
        Map<String, Object> json = new HashMap<>();
        json.put("email", loginPojo.getEmail());
        json.put("password", loginPojo.getPassword());
        //convert json to string
        String data = mapper.writeValueAsString(json);

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(data)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserPojo userPojo) throws JsonProcessingException {
        final String data = extractData(token);
        final Map<String, Object> dataJson = toJson(data);
        return dataJson.get("email").equals(userPojo.getEmail()) && dataJson.get("password").equals(userPojo.getPassword()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Map<String, Object> toJson(String data) throws JsonProcessingException {
        Map<String, Object> toJson = mapper.readValue(data, new TypeReference<Map<String, Object>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        return toJson;
    }
}