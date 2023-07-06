package com.company.davyc.security.jwt;

import com.company.davyc.StudyApplication;
import com.company.davyc.domain.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JWTService {

    @Value("${security.jwt.exptime}")
    private String expiracao;

    @Value("${security.jwt.signature}")
    private String chaveAssinatura;

    @SuppressWarnings("deprecation")
    public String gerarToken(Usuario usuario){
        long expTime = Long.parseLong(expiracao);
        LocalDateTime horaExpiracao = LocalDateTime.now().plusMinutes(expTime);
        Date data = Date.from(horaExpiracao.atZone(ZoneId.systemDefault()).toInstant());
        return Jwts
                .builder()
                .setSubject(usuario.getUsername())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS256, chaveAssinatura)
                .compact();
    }

    @SuppressWarnings("deprecation")
    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(chaveAssinatura)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validToken(String token){
        try {
            Claims claims = getClaims(token);
            Date date = claims.getExpiration();
            LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(localDateTime);
        }catch (Exception e){
            return false;
        }
    }

    public String getLoginUser(String token) throws ExpiredJwtException{
        return getClaims(token).getSubject();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StudyApplication.class);
        JWTService service = context.getBean(JWTService.class);
        Usuario usuario = Usuario.builder().username("fulano").build();
        String token = service.gerarToken(usuario);
        System.out.println(token);

        boolean isValidToken = service.validToken(token);
        if (isValidToken){
            System.out.println("Sim");
            System.out.println("Login: " + service.getLoginUser(token));

        }
        else {
            System.out.println("Não");
        }

    }
}
