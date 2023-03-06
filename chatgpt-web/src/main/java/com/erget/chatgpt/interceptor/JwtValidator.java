package com.erget.chatgpt.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.function.Function;

/**
 * Jwt认证器
 *
 * @author Ray
 * @date 2021-07-14 18:46
 */
@Component
@Slf4j
public class JwtValidator implements InitializingBean {

    private PublicKey publicKey;

    @Value("${jwt.accessKey:MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEjLbjosYspuWBJU20vmomI8l1TRSH+BgPwdoPqKvtNInN8GLz1boyS1BqEYebDyoaojvLdORhRAhkn625E4z0Yg==}")
    private String accessKey =
        "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEjLbjosYspuWBJU20vmomI8l1TRSH+BgPwdoPqKvtNInN8GLz1boyS1BqEYebDyoaojvLdORhRAhkn625E4z0Yg==";

    @Override
    public void afterPropertiesSet() {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("ECDH", new BouncyCastleProvider());
            publicKey = keyFactory
                .generatePublic(new X509EncodedKeySpec(Base64Utils.decodeFromString(accessKey)));
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

	public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Boolean validateToken(String token, String username) {
        String usernameFromToken = getUsernameFromToken(token);
        return (usernameFromToken.equals(username) && !isTokenExpired(token));
    }

    public static void main(String[] args) {
        JwtValidator validator = new JwtValidator();
        validator.afterPropertiesSet();

        String token =
            "eyJhbGciOiJFUzI1NiJ9.eyJzdWIiOiJuYmdAODUzMS5jb20iLCJ1aWQiOjQxOSwiZ2lkIjowLCJpaWQiOjAsImV4cCI6MTYzNjUxMjc5OCwiaWF0IjoxNjM2NTA5MTk4LCJ0aWQiOjEwMiwidXQiOjJ9.fkWi1P7kDCfd0oZklD6nwfV9l4t7LrqPE_mtS-ai63llD5GI0UtlUXsmuxCMbovR1fCFq9qN9y8cyc_vT8-Wtg";
        System.out.println(validator.validateToken(token, "nbg@8531.com"));
        System.out.println(validator.getClaimsFromToken(token));
    }
}