package wpd2.groupX.model;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class User {
    static final Logger LOG = LoggerFactory.getLogger(User.class);

    public final static String USERNAME_PARAMETER = "userName";
    private final static String USER_NAME_KEY = "userName";
    private final static String LOGIN_REDIRECT_KEY = "redirectURL";
    final static String DEFAULT_LOGIN_REDIRECT = "/index.html";

    private User() {}

    public static void setCurrentUser(HttpServletRequest request, @NonNull String userName) {
        HttpSession session = request.getSession(true);
        session.setAttribute(USER_NAME_KEY, userName);
    }

    public static void clearCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.removeAttribute(USER_NAME_KEY);
    }


    /**
     * Find the current user, if any
     * @param request  The HTTP request object, containing the session, if any
     * @return The current user, or the empty string if none (note NOT null)
     */
    public static String getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "";
        }
        String val = (String)session.getAttribute(USER_NAME_KEY);
        return val == null ? "" : val;
    }

    public static String getLoginRedirect(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return DEFAULT_LOGIN_REDIRECT;
        }
        String redirectURL = (String)session.getAttribute(LOGIN_REDIRECT_KEY);
        session.removeAttribute(LOGIN_REDIRECT_KEY);
        return redirectURL == null ? DEFAULT_LOGIN_REDIRECT : redirectURL;
    }

    public static void setLoginRedirect(HttpServletRequest request) {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession(true);
        session.setAttribute(LOGIN_REDIRECT_KEY, uri);
    }
}
