package com.bamboovir.muresearchboost.app.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {

    @Value("${token.secret}")
    private String secret;
    @Value("${token.expiration}")
    private Long expiration;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.secret.getBytes("UTF-8"))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public String generateToken(TokenDetail tokenDetail) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", tokenDetail.getUserName());
        claims.put("created", this.generateCurrentDate());
        return this.generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(this.generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, this.secret.getBytes("UTF-8"))
                    .compact();
        } catch (UnsupportedEncodingException ex) {
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(this.generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, this.secret)
                    .compact();
        }
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expiration * 1000);
    }

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        UserDetailImpl user = (UserDetailImpl) userDetails;
        final String username = this.getUsernameFromToken(token);
        final Date created = this.getCreatedDateFromToken(token);
        return (username.equals(user.getUsername()) &&
                !(this.isTokenExpired(token)) &&
                !(this.isCreatedBeforeLastPasswordReset(created, user.getLastPasswordReset())));
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            created = new Date((Long) claims.get("created"));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(this.generateCurrentDate());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }


}
