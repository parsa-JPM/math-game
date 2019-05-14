package ir.codefather.game.filters;

import ir.codefather.game.helpers.Player;
import ir.codefather.game.models.User;
import ir.codefather.game.models.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Autowired
    UserRepo repo;

    @Autowired
    Player player;

    /**
     * check token in Authentication required endpoints
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (isNotAuthURL(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = request.getParameter("token");

        if(!checkTokenExist(token, response))
            return;

        if (!tokenValidation(token, response))
            return;

        filterChain.doFilter(servletRequest, servletResponse);
    }


    /**
     *
     * check url pattern and return true if that has been public url
     *
     * @param request
     *
     * @return boolean
     */
    private boolean isNotAuthURL(HttpServletRequest request) {
        return !request.getRequestURI().contains("/auth");
    }

    /**
     * check token parameter that hasn't been null
     *
     * @param token
     * @param response
     * @return boolean
     * @throws IOException
     */
    private boolean checkTokenExist(String token, HttpServletResponse response) throws IOException {
        if (token == null) {
            response.sendRedirect("/token/null");
            return false;
        }

        return true;
    }

    /**
     *
     * it try to find a user with given token. return false if hasn't been success
     *
     * @param token
     * @param response
     * @return boolean
     * @throws IOException
     */
    private boolean tokenValidation(String token, HttpServletResponse response) throws IOException {
        Optional<User> user = getUser(token);

        if (user.isEmpty()) {
            response.sendRedirect("/token/invalid");
            return false;
        } else
            player.setUser(user.orElse(null));

        return true;
    }

    /**
     * find user with token
     *
     *
     * @param token
     * @return
     */
    private Optional<User> getUser(String token) {
        Optional<User> user = repo.findByToken(token);
        return user;
    }

}