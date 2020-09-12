package study.spring.ddd.common.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import study.spring.ddd.common.config.JwtTokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 헤더에서 JWT 를 받아옵니다.
        System.out.println("request : " + request);
        String accessToken = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        String refreshToken = jwtTokenProvider.resolveRefreshToken((HttpServletRequest) request);
        System.out.println("token : " + accessToken);
        System.out.println("request url : " + request.getRequestURI());


        if (accessToken != null) { // access 토큰
            // 유효한 access 토큰인지 확인합니다.
            if (jwtTokenProvider.validateToken(accessToken)) {
                // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
                Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
                System.out.println("authentication : " + authentication);
                // SecurityContext 에 Authentication 객체를 저장합니다.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else { // access 토큰이 만료인 경우
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(200);
                response.getWriter().write("{\"rt\":\"" + 403 + "\"}");
                return;
            }

        } else if (refreshToken != null) {// refresh 토큰
            if (jwtTokenProvider.validateToken(refreshToken)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);
                // SecurityContext 에 Authentication 객체를 저장합니다.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } else { // access, refresh Token 둘다 없는 경우
       /*     response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(200);
            response.getWriter().write("{\"rt\":\"" + 401 + "\"}");
            return;*/
        }
        filterChain.doFilter(request, response);
    }

}
