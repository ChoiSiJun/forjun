package forjun.web.config.security;

import forjun.web.module.user.application.port.in.UserQuery;
import forjun.web.module.user.domain.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class AccessPageFilter extends OncePerRequestFilter {

    private final UserQuery userQuery;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String[] checkUrl = {"/web"};
        final String uri = request.getRequestURI();

        if (Arrays.stream(checkUrl).noneMatch(uri::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String requestUserId = request.getParameter("userId");

        // 이 시점은 본인이 아닌 다른 사용자의 페이지를 보려는 경우
        User user = userQuery.getUserByUserId(requestUserId);
        boolean isPrivate = false;
        if (uri.startsWith("/web/personal-statement")) {
            isPrivate = !"Y".equals(user.getPersonalPrivate());
        } else if (uri.startsWith("/web/histories")) {
            isPrivate = !"Y".equals(user.getHistoryPrivate());
        }
        if (isPrivate) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
