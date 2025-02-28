package project.canteen.config.jwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.canteen.common.constant;
import project.canteen.repository.auth.accountRepository;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class jwtProvider {
    @Autowired
    accountRepository accountRepository;
    //	@Value("$(secret_key)")
//    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private String jwtSecret ="VUVNDUC050120011234567890QWERTYUIPASDFGHJKLZXCVBNMQASXCFVGBJHBFHCDGVCGNVCGVHCFHBBCAOKFLWJDJGKDJRVJFHJFHDJRHFHDSHRYCHDJSJFHDH"; // Base64.getEncoder().encodeToString(key.getEncoded());
    //	private final String LOCALHOST_IPV4 = "127.0.0.1";
//	private final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";
    public static final Long time_expire = 7200000L;

    public String generateToken(String userName) {
        Date date = new Date(System.currentTimeMillis() + jwtProvider.time_expire);
        String jwt = Jwts.builder().setSubject(userName).setExpiration(date).signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return jwt;
    }

    public boolean validateToken(String token) {

        if(accountRepository.findUserByUsername(getLoginFormToke(token)).getStatus() == constant.STATUS.DE_ACTIVE) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
        }
        return false;

    }

    public String getLoginFormToke(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
