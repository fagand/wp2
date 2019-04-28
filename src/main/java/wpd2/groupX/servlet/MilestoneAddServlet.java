package wpd2.groupX.servlet;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wpd2.groupX.db.UserDb;
import wpd2.groupX.model.Person;
import wpd2.groupX.model.User;
import wpd2.groupX.util.MustacheRenderer;
import wpd2.groupX.model.Milestones;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.List;

public class MilestoneAddServlet extends BaseServlet {
    static final Logger LOG = LoggerFactory.getLogger(MilestoneAddServlet.class);
    private static final String PAGE_TEMPLATE = "msadd.mustache";
    private final UserDb userDb;
    private final MustacheRenderer mustache;
    public MilestoneAddServlet(UserDb UserDb) {
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
        try {
            Milestones ms = new Milestones(msname, msdesc, duedate);
            userDb.addMilestone(ms);
            response.sendRedirect("/index.html");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
