package wpd2.groupX.servlet;

import wpd2.groupX.model.MileStoneBoard;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class MileStoneServlet extends BaseServlet {
    //good practice to declare the template that is populated as a constant, why?
    //declare your template here
    private static final String MESSAGE_BOARD_TEMPLATE = "ms.mustache";
    //servlet can be serialized
    private static final long serialVersionUID = 687117339002032958L;

    public MileStoneServlet()  {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showView(response, MESSAGE_BOARD_TEMPLATE, getObject());
    }

    private Object getObject() {
        MileStoneBoard ms = new MileStoneBoard();
        ms.setName("My Milestone Board");
        ms.setTopics(Arrays.asList("Milestone 1", "Milestone 2"));
        return ms;
    }
}
