package wpd2.groupX;

import wpd2.groupX.db.UserDb;
import wpd2.groupX.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class Runner {
    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(Runner.class);

    private static final int PORT = 9003;

    private final UserDb userDb;

    public Runner() { userDb = new UserDb(); }

    private void start() throws Exception {
        Server server = new Server(PORT);

        ServletContextHandler handler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        handler.setInitParameter("org.eclipse.jetty.servlet.Default." + "resourceBase", "src/main/resources/webapp");

        DefaultServlet ds = new DefaultServlet();
        handler.addServlet(new ServletHolder(ds), "/");

        PublicServlet publicServlet = new PublicServlet();
        handler.addServlet(new ServletHolder(publicServlet), "/public");

        TopicServlet topicServlet = new TopicServlet();
        handler.addServlet(new ServletHolder(topicServlet), "/topics");

        MileStoneServlet milestoneServlet = new MileStoneServlet();
        handler.addServlet(new ServletHolder(milestoneServlet), "/milestones");

        handler.addServlet(new ServletHolder(new LoginServlet()), "/login");

        handler.addServlet(new ServletHolder(new PersonServlet(userDb)), "/index.html");

        handler.addServlet(new ServletHolder(new PersonServlet(userDb)),"/add");


        server.start();
        LOG.info("Server started, will run until terminated");
        server.join();

    }

    public static void main(String[] args) {
        try {
            LOG.info("server starting...");
            Locale.setDefault(Locale.UK);
            new Runner().start();
        } catch (Exception e) {
            LOG.error("Unexpected error running: " + e.getMessage());
        }
    }
}
