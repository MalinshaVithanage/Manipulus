package com.Manipulus.arctic.auth.helpers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.Manipulus.arctic.auth.constants.JWTUtil.*;

@Component
public class JWTHelper {

    Algorithm algorithm = Algorithm.HMAC256(SECRET);
    public String generateAccessToken(String username, List<String> roles) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_ACCESS_TOKEN))
                .withIssuer(ISSUER)
                .withClaim("roles", roles)
                .sign(algorithm);
    }

    public String generateRefreshToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_REFRESH_TOKEN))
                .withIssuer(ISSUER)
                .sign(algorithm);
    }

    public String extractTokenFromHeaderIfExist(String authorizationHeader) {
        if(authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            return authorizationHeader.substring(BEARER_PREFIX.length());
        }
        return null;
    }

    public Map<String, String> getTokenMap(String jwtAccessToken, String jwtRefreshToken) {
        Map<String, String> idTokens = new HashMap<>();
        idTokens.put("accessToken", jwtAccessToken);
        idTokens.put("refreshToken", jwtRefreshToken);
        return  idTokens;
    }
}
