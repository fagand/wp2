package wpd2.groupX.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wpd2.groupX.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends BaseServlet {

    static final Logger LOG = LoggerFactory.getLogger(LogoutServlet.class);

    public LogoutServlet() {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User.clearCurrentUser(request);
        response.sendRedirect(response.encodeRedirectURL(User.DEFAULT_LOGIN_REDIRECT));
    }
}
