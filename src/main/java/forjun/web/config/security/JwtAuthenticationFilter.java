package forjun.web.config.security;


import forjun.web.exception.application.authentication.NotJwtGenerate;
import forjun.web.module.user.application.UserService;
import forjun.web.module.user.domain.User;
import forjun.web.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PublicUrlConfig publicUrlConfig;

    private final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String uri  = request.getRequestURI();
        String method = request.getMethod();
        return publicUrlConfig.publicUrls().getOrDefault(method, List.of()).stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, uri));
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException , NotJwtGenerate {

        final String authorizationHeader = request.getHeader("Authorization");

        String id = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            id = jwtUtil.extractSubject(jwt);
        }

        if (id != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userService.getUserByUserId(id);

            //권한부여
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            if(user.getAuthority() != null) {
                String[] userAuthoritiesList = user.getAuthority().split(",");
                for (int i = 0; i < userAuthoritiesList.length; i++) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(userAuthoritiesList[i]));
                }
            }else{
                grantedAuthorities.add(new SimpleGrantedAuthority("GUEST"));
            }

            CustomUserDetail userDetails = CustomUserDetail.builder()
                    .id(user.getId())
                    .userId(user.getUserId())
                    .authorities(grantedAuthorities)
                    .build();

            if (jwtUtil.validateToken(jwt, String.valueOf(userDetails.getUserId()))) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // 인증 안 된 경우 401 처리
        if (SecurityContextHolder.getContext().getAuthentication() == null && !shouldNotFilter(request)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
            return; // 필터 체인 중단
        }

        // 인증 성공 후 필터 체인 계속 진행
        filterChain.doFilter(request, response);
    }
}
