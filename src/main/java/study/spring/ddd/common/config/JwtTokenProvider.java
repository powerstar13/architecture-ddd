package study.spring.ddd.common.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secretKey}")
    private String secretKey;

    // access 토큰 유효시간 2시간
    private static final long accessTokenValidTime = 2 * 60 * 60 * 1000L;
    // refresh 토 유효시간 2주
    private static final long refreshTokenValidTime = 2 * 7 * 24 * 60 * 60 * 1000L;

    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    // access 토큰 생성 요청
    public String createAccessToken(String userPK/*, List<String> roles*/) {
        return createToken(accessTokenValidTime, userPK/*, roles*/);
    }

    // refresh 토큰 생성 요청
    public String createRefreshToken(String userPK/*, List<String> roles*/) {
        return createToken(refreshTokenValidTime, userPK/*, roles*/);
    }

    // JWT 토큰 인증정보 생성
    public String createToken(long tokenValidTime, String userPK/*, List<String> roles*/) {
        Claims claims = Jwts.claims().setSubject(userPK); // JWT playload에 저장되는 정보 단위
        claims.put("roles", "ROLE_USER"); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();
        return Jwts.builder()
            .setClaims(claims) // 정보 저장
            //.setIssuer("http://localhost:8080")
            .setIssuedAt(now) // 토큰 발행시간 정보
            .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time (유효기간)
            .signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과 signature 에 들어갈 secret 값 셋팅
            .compact();
    }

    // JWT 토큰 인증정보 조회
    public Authentication getAuthentication(String token) {
        System.out.println("getAuthentication token = " + token);
        System.out.println("getAuthentication getUserPk = " + getUserPk(token));
        /*UserDetails userDetails = (UserDetails) */
        //User user = userDetailsService.findByUserId(Integer.parseInt((getUserPk(token))));

        Claims claims = getClaims(token);


        System.out.println("claims = " + claims);

        List<String> roles = new ArrayList<>();
        roles.add(claims.get("roles", String.class));


        Collection<? extends GrantedAuthority> getAuthorities = roles.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(claims, "", getAuthorities);
        //return new UsernamePasswordAuthenticationToken(claims, "", getAuthorities);
        //return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    // Request의 Header에서 access token 값을 가져옵니다. "Authorization" : "access TOKEN값"
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // Request의 Header에서 refresh token 값을 가져옵니다. "refreshToken" : "refresh TOKEN값"
    public String resolveRefreshToken(HttpServletRequest request) {
        return request.getHeader("refreshToken");
    }

    // 토큰 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }


}
