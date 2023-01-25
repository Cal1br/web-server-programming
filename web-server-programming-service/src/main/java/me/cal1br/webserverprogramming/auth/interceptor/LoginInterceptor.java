package me.cal1br.webserverprogramming.auth.interceptor;

import me.cal1br.webserverprogramming.auth.jwt.JWTService;
import me.cal1br.webserverprogramming.domain.user.service.UserService;
import me.cal1br.webserverprogramming.exception.AuthException;
import me.cal1br.webserverprogramming.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);
    private final JWTService jwtService;
    private final UserService userService;

    public LoginInterceptor(final JWTService jwtService, final UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        final HandlerMethod handlerMethod;
        try {
            handlerMethod = (HandlerMethod) handler;
        } catch (Exception ex) {
            return false;
        }
        final LoginRequired loginRequired;
        if (handlerMethod.hasMethodAnnotation(LoginRequired.class)) {
            loginRequired = handlerMethod.getMethod().getAnnotation(LoginRequired.class);
        } else {
            loginRequired = handlerMethod.getBeanType().getAnnotation(LoginRequired.class);
        }
        if (loginRequired == null || !loginRequired.value()) {
            return true;
        }
        try {
            final String token = request.getHeader("Authorization");
            final Long idFromToken = jwtService.getIdFromToken(token);
            if (idFromToken == null) {
                throw new AuthException();
            }
            if (userService.findById(idFromToken).isPresent()) {
                request.setAttribute("user", userService.findById(idFromToken).get());
            } else {
                LOGGER.error("Critical error. Signed token received, but could not find user by id! Data deleted or connection problem?");
                throw new UserNotFoundException("User");
            }
        } catch (Exception e) {
            throw new AuthException();
        }

        return true;
    }
}