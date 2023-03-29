package pl.dawidlisowski.OrganiserApp.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class LoginInterceptor implements HandlerInterceptor {

    public static final String PASSWORD_HEADER_API = "api-password";

    @Value("${api}")
    String ourApiKey;

    final UserSession userSession;

    @Autowired
    public LoginInterceptor(UserSession userSession) {
        this.userSession = userSession;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().contains("api")) {
            String passwordApi = request.getHeader(PASSWORD_HEADER_API);
            if (passwordApi == null || !passwordApi.equals(ourApiKey)) {
                System.out.println("null lub zły kod");
                response.sendError(403, "Access denied. Bad api key");
                return false;
            }
        }
//        if ((!request.getRequestURI().contains("login") &&
//            !request.getRequestURI().contains("register")) &&
//            !userSession.isLoggedIn()) {
//            System.out.println("Wlazło");
//            response.sendRedirect("/user/login");
//            return false;
//        }
        System.out.println("Jakieś zapytanie wpadło");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}

