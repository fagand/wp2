package wpd2.groupX.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wpd2.groupX.db.UserDb;
import wpd2.groupX.model.Milestones;
import wpd2.groupX.model.User;
import wpd2.groupX.util.MustacheRenderer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

public class MilestoneUpdateServlet extends BaseServlet {
    static final Logger LOG = LoggerFactory.getLogger(MilestoneUpdateServlet.class);
    private static final String PAGE_TEMPLATE = "msupdate.mustache";
    private final UserDb userDb;
    private final MustacheRenderer mustache;
    public MilestoneUpdateServlet(UserDb UserDb) {
        mustache = new MustacheRenderer();
        this.userDb = UserDb;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = User.getCurrentUser(request);
        showView(response, PAGE_TEMPLATE, userName);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msname = request.getParameter("msname");
        String msdesc = request.getParameter("msdesc");
        String duedate = request.getParameter("duedate");
        String id = request.getParameter("id");
        try {
            Milestones ms = new Milestones(msname, msdesc, duedate, id);
            userDb.updateMilestone(ms);
            response.sendRedirect("/milestones");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
