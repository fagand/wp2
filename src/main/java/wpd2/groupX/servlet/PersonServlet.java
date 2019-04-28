package wpd2.groupX.servlet;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wpd2.groupX.db.UserDb;
import wpd2.groupX.model.Person;
import wpd2.groupX.model.User;
import wpd2.groupX.util.MustacheRenderer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class PersonServlet extends BaseServlet {
    static final Logger LOG = LoggerFactory.getLogger(PersonServlet.class);

    private final UserDb userDb;
    private final MustacheRenderer mustache;
    public PersonServlet(UserDb UserDb) {
        mustache = new MustacheRenderer();
        this.userDb = UserDb;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Person> persons = userDb.findPersons();
        String username = User.getCurrentUser(request);
        String html = mustache.render("index.mustache", new Result(persons.size(), persons, username));
        response.setContentType("text/html");
        response.setStatus(200);
        response.getOutputStream().write(html.getBytes(Charset.forName("utf-8")));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        String email = request.getParameter("email");
        Person person = new Person(first, last, email);
        userDb.addPerson(person);
        response.sendRedirect("/index.html");
    }

    @Data
    class Result {
        public int count;
        public List<Person> person;
        public String currentUsername;
        Result(int count, List<Person> person, String user) {
            this.count = count; this.person = person; this.currentUsername = user;}
    }
}
