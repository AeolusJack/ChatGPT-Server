package com.erget.chatgpt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.io.Serializable;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtil implements Serializable, InitializingBean {

    private static final long serialVersionUID = -2550185165626007488L;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    @Value("${jwt.secretKey:MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCDPUq39eUFuTNfI5lcr+c73AFY6K6iM/35CuWG/IQgW6g==}")
    private String secretKey = "MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCDPUq39eUFuTNfI5lcr+c73AFY6K6iM/35CuWG/IQgW6g==";

    @Value("${jwt.accessKey:MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEjLbjosYspuWBJU20vmomI8l1TRSH+BgPwdoPqKvtNInN8GLz1boyS1BqEYebDyoaojvLdORhRAhkn625E4z0Yg==}")
    private String accessKey = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEjLbjosYspuWBJU20vmomI8l1TRSH+BgPwdoPqKvtNInN8GLz1boyS1BqEYebDyoaojvLdORhRAhkn625E4z0Yg==";

    public int getExpirationTime() {
        return expirationTime;
    }

    @Value("${jwt.expirationTime:3600}")
    private int expirationTime = 3 * 3600;




    @Override
    public void afterPropertiesSet() {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("ECDH", new BouncyCastleProvider());
            privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64Utils.decodeFromString(secretKey)));
            keyFactory = KeyFactory.getInstance("ECDH", new BouncyCastleProvider());
            publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(Base64Utils.decodeFromString(accessKey)));
        } catch (Exception e) {
            log.error("初始化秘钥失败:", e);
            System.exit(-1);
        }
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    public String generateToken(String username) {
        return generateToken(username, new HashMap<>(), this.expirationTime);
    }

    public String generateToken(String subject, Map<String, Object> claims, Integer expirationTime) {
        if (expirationTime == null) {
            expirationTime = this.expirationTime;
        }
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime * 1000))
                .signWith(SignatureAlgorithm.ES256, privateKey).compact();
    }

    public Boolean canTokenBeRefreshed(String token) {
        return (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public Boolean validateToken(String token, String username) {
        String usernameFromToken = getUsernameFromToken(token);
        return (usernameFromToken.equals(username) && !isTokenExpired(token));
    }

    public static void main(String[] args) {
        JwtUtil jwtUtil = new JwtUtil();
        jwtUtil.afterPropertiesSet();
        Map<String, Object> claims = new HashMap<>(8);

        claims.put("chat","erget");
        String s = jwtUtil.generateToken("pjq@163.com", claims, jwtUtil.expirationTime);
        System.out.println(s);
       // System.out.println(jwtUtil.getClaimsFromToken("eyJhbGciOiJFUzI1NiJ9.eyJ1aWQiOjQxOSwic3ViIjoibmJnQDg1MzEuY29tIiwiZ2lkIjoyMDYsImlpZCI6bnVsbCwiZXhwIjoxNjI3MjI2NjQwLCJpYXQiOjE2MjY2ODY2NDAsInRpZCI6MTAyfQ.BFSqhqzalSEzjd8tJQcx7kRGDm0FNgFHbUTS-YFRdVrrig3zgIPi74UFOKO51Sukj3lJ6ip9JSzDUzsOdz7mqQ"));
    }
}