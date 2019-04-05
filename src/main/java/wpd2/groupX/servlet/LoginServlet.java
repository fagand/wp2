package wpd2.groupX.servlet;

import wpd2.groupX.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends BaseServlet{
    static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

    private final String LOGIN_TEMPLATE = "login.mustache";


    public LoginServlet() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = User.getCurrentUser(request);
        showView(response, LOGIN_TEMPLATE, userName);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter(User.USERNAME_PARAMETER);
        if (name != null && name.length() > 0) {
            User.setCurrentUser(request, name);
            String targetURL = User.getLoginRedirect(request);
            response.sendRedirect(response.encodeRedirectURL(targetURL));
        }
        // do nothing, we stay on the page,
        // could also display a warning message by passing parameter to /login on redirect
    }
}
