package wpd2.groupX.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import wpd2.groupX.model.Milestones;



public class TopicServlet extends BaseServlet {

    private static final String TOPIC_TEMPLATE = "topics.mustache";

    public TopicServlet() {}

    //helper method to create a Milestones object, which provides the data shown on the topic page
    private Object getObject() {
        List<String> messages = new ArrayList<String>();
        messages.add("Hi there. Good day.");
        messages.add("not really, got compile time errors");
        messages.add("rtfm");
        messages.add("need coffee");
        return messages;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showView(response, TOPIC_TEMPLATE, getObject());
    }
}
