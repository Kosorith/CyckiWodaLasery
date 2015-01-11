package pl.p.lodz.ftims.server.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Bartłomiej Długosz
 */
@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String loginURL = request.getContextPath() + "/panel/login";
        String ressURL = "/css/";

        HttpSession session = request.getSession(false);
        
        boolean loggedIn = (session != null && session.getAttribute("userId") != null);
        boolean loginRequest = request.getRequestURI().startsWith(loginURL);
        boolean resourceRequest = request.getRequestURI().contains(ressURL);
        
        if (loggedIn || loginRequest || resourceRequest) {
            if (!resourceRequest) {
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.setHeader("Pragma", "no-cache");
                response.setDateHeader("Expires", 0);
            }
            
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURL);
        }
    }

    @Override
    public void destroy() {
    }
}
