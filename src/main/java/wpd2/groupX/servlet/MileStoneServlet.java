package wpd2.groupX.servlet;

import lombok.Data;
import wpd2.groupX.db.UserDb;
import wpd2.groupX.model.MileStoneBoard;
import wpd2.groupX.model.Milestones;
import wpd2.groupX.model.Person;
import wpd2.groupX.model.User;
import wpd2.groupX.util.MustacheRenderer;
import wpd2.groupX.db.UserDb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class MileStoneServlet extends BaseServlet {
    //good practice to declare the template that is populated as a constant, why?
    //declare your template here
    private static final String MESSAGE_BOARD_TEMPLATE = "ms.mustache";
    //servlet can be serialized
    private static final long serialVersionUID = 687117339002032958L;
    private final UserDb userDb;
    private final MustacheRenderer mustache;

    public MileStoneServlet(UserDb UserDb) {
        mustache = new MustacheRenderer();
        this.userDb = UserDb;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!authOK(request, response)){
            return;
        }
        try {
            List<Milestones> ms = userDb.findMilestones();
            String username = User.getCurrentUser(request);
            String html = mustache.render("ms.mustache", new Result(ms, username));
            response.setContentType("text/html");
            response.setStatus(200);
            response.getOutputStream().write(html.getBytes(Charset.forName("utf-8")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Data
    class Result {
        public List<Milestones> milestones;
        public String currentUsername;
        Result(List<Milestones> milestones, String user) {
          this.milestones = milestones; this.currentUsername = user;}
    }
}
