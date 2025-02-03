package org.example.security

import io.jsonwebtoken.Claims
import org.example.dto.ClaimData
import org.springframework.stereotype.Component
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm

@Component
class JwtTokenUtil {

    private static final long serialVersionUID = -2550185543626007488L;

    public static final long JWT_TOKEN_VALIDITY =  60 * 60 * 60;

    private String secret = "rockingbci";

    public String generarToken(String toDecode) {
        return generarTokenDJwts(toDecode);
    }

    private String generarTokenDJwts(String subject) {
        Map<String, Object> claims = new HashMap<>();
        String jwtsResult = null;
        try{
            jwtsResult = Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                    .signWith(SignatureAlgorithm.HS512, secret).compact();
        } catch (Exception e){
            String a = '1'
        }


        return jwtsResult;
    }

    private ClaimData getAllClaimsFromToken(String token) {
        Claims response = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        ClaimData responseData = new ClaimData();
        responseData.setExpierationDate((Date) response.getExpiration());
        responseData.setEmail(response.getSubject());
        return responseData
    }

    public Boolean isTokenExpirado(String token) {
        ClaimData datos = getAllClaimsFromToken(token);
        final Date expiration = datos.getExpierationDate();
        return expiration.before(new Date());
    }

    public String getEmailFromJwt(String token) {
        ClaimData datos = getAllClaimsFromToken(token);
        return datos.getEmail();
    }

}
