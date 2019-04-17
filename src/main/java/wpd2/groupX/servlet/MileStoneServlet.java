package wpd2.groupX.servlet;

import wpd2.groupX.model.MileStoneBoard;
import wpd2.groupX.model.Milestones;
import wpd2.groupX.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

public class MileStoneServlet extends BaseServlet {
    //good practice to declare the template that is populated as a constant, why?
    //declare your template here
    private static final String MESSAGE_BOARD_TEMPLATE = "ms.mustache";
    //servlet can be serialized
    private static final long serialVersionUID = 687117339002032958L;

    public MileStoneServlet() {
    }
    private Object getObject() throws ParseException {
        String name ="milestone 1";
        String desc = "test description";
        String date = "2019-01-01";

        Milestones t = new Milestones(name,desc,date);
        System.out.println(t.toString());
        return t.toString();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!authOK(request, response)){
            return;
        }
        String userName = User.getCurrentUser(request);
        showView(response, MESSAGE_BOARD_TEMPLATE, userName);
    }
}
