package com.marcinolek.mytimesheet.config.security.jwt;

import com.marcinolek.mytimesheet.constants.exception.WebApiExceptionType;
import com.marcinolek.mytimesheet.dto.jwt.BlockedJwtDTO;
import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.service.jwt.BlockedJwtService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Autowired
    private BlockedJwtService blockedJwtService;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationTime}")
    private long expirationDate;

    @Value("${jwt.expirationTime.multiplier}")
    private long multiplier;

    public String generateToken(UserDTO user) {
        return Jwts.builder()
                .setSubject(format("%s,%s", user.getId(), user.getUsername()))
                .setIssuer("com.marcinolek")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationDate * multiplier))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Long getUserId(String jwtToken) {
        jwtToken = getClearToken(jwtToken);
        return Long.parseLong(this.getClaims(jwtToken).getSubject().split(",")[0]);
    }

    public String getUsername(String jwtToken) {
        jwtToken = getClearToken(jwtToken);
        return this.getClaims(jwtToken).getSubject().split(",")[1];
    }

    public Date getExpirationDate(String jwtToken) {
        jwtToken = getClearToken(jwtToken);
        return this.getClaims(jwtToken).getExpiration();
    }

    public boolean validate(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken);
            return !this.blockedJwtService.isTokenBlocked(jwtToken);
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            try {
                this.blockedJwtService.blockToken(jwtToken);
            } catch (WebApiException e) {
                logger.error("Saving expired token to black list failed - {}", ex.getMessage());
            }
            logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

    private Claims getClaims(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    public static String getClearToken(String token) {
        return token.startsWith("Bearer ") ? token.substring(7) : token;
    }

}
