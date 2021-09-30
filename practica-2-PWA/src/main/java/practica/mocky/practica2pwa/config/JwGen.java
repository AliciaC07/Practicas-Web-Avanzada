package practica.mocky.practica2pwa.config;

import io.jsonwebtoken.*;
import practica.mocky.practica2pwa.models.Mock;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwGen {
    private static final String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93a";

    //Sample method to construct a JWT
    public static String createJWT(String id, String issuer, String subject, Integer ttSecs) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long  nowSecs = System.currentTimeMillis();
        Date now = new Date(nowSecs);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = Base64.getEncoder().encode(SECRET_KEY.getBytes());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttSecs >= 0) {
            long expSecs = nowSecs + TimeUnit.SECONDS.toMillis(60);
            Date exp = new Date(expSecs);
            System.out.println(exp.toString());
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static Claims decodeJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encode(SECRET_KEY.getBytes()))
                .parseClaimsJws(jwt).getBody();
    }

    public String tokenCreated(Mock mock){
        String jwtId = "PacoFish";
        String jwtIssuer = "JWT Gen";
        String jwtSubject = mock.getNameMock();
        int jwtTimeToLive = mock.getExpiration();

        return createJWT(
                jwtId, // claim = jti
                jwtIssuer, // claim = iss
                jwtSubject, // claim = sub
                jwtTimeToLive // used to calculate expiration (claim = exp)
        );
    }

    public Boolean tokenVerify(String mockName, String jwt){
        String jwtId = "PacoFish";
        String jwtIssuer = "JWT Gen";
        try{
            if (jwt != null){
                Claims claims = decodeJWT(jwt);
                if (claims.getId().equals(jwtId) && claims.getIssuer().equals(jwtIssuer)
                        && claims.getSubject().equals(mockName)){
                    return true;
                }else {
                    return false;
                }
            }


        }catch (JwtException e){
            System.out.println("Token is invalid");
        }

        return false;
    }
}
