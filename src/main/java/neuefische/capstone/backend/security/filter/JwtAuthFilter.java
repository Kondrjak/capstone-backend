package neuefische.capstone.backend.security.filter;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import neuefische.capstone.backend.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

/**
 * Setup for stateless authentication using  jwt
 */
@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Autowired
    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            @Nullable HttpServletRequest request,
            @Nullable HttpServletResponse response,
            @Nullable FilterChain filterChain) throws ServletException, IOException {

        String token = null;
        if (request != null) {
            token = this.getTokenString(request);
        }
        try {
            if (token != null && !token.isBlank()) {
                String username = jwtService.parseUsername(token);
                setSecurityContext(username);
            }
        } catch(Exception e){
            // throwing exception would be bad since controller advice not working in filter
            // throw new AccessDeniedException("No valid token! Access denied!", e);
            // response.sendError(HttpServletResponse.SC_FORBIDDEN);
            // log.error("No valid token!", e);
        }
        if (filterChain != null) {
            filterChain.doFilter(request, response);
        }
    }

    /**
     *
     * @param username - a string between 0 and 32 chars
     */
    private void setSecurityContext(String username) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        username,
                        "",
                        List.of());
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    /**
     *
     * @param request - example: todo
     * @return String - the content of the token, if there was one
     *  null - if there was no token present
     */
    private String getTokenString(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if(authHeader != null){
            return authHeader.replace("Bearer ", "").trim();
        }else {
            return null;
        }
    }

}