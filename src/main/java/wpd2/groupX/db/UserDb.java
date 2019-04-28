package wpd2.groupX.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wpd2.groupX.model.Milestones;
import wpd2.groupX.model.Person;
import wpd2.groupX.model.User;


import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UserDb implements AutoCloseable {
    static final Logger LOG = LoggerFactory.getLogger(UserDb.class);

    public static final String MEMORY = "jdbc:h2:mem:shop";
    public static final String FILE = "jdbc:h2:~/shop";

    private Connection connection;

    static Connection getConnection(String db) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");  // ensure the driver class is loaded when the DriverManager looks for an installed class. Idiom.
        return DriverManager.getConnection(db, "sa", "");  // default password, ok for embedded.
    }

    public UserDb() {
        this(FILE);
    }

    public UserDb(String db) {
        try {
            connection = getConnection(db);
            loadResource("/person.sql");
            loadResource("/logins.sql");
            loadResource("/milestones.sql");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPerson(Person person) {
        final String ADD_PERSON_QUERY = "INSERT INTO person (first, last, email) VALUES (?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(ADD_PERSON_QUERY)) {
            ps.setString(1, person.getFirst());
            ps.setString(2, person.getLast());
            ps.setString(3, person.getEmail());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addMilestone(Milestones ms) {
        final String ADD_MILESTONE_QUERY = "INSERT INTO milestones (msname, msdesc, msduedate ) VALUES (?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(ADD_MILESTONE_QUERY)) {
            ps.setString(1, ms.getName());
            ps.setString(2, ms.getDescription());
            ps.setString(3, ms.getDuedateString());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> findPersons() {
        final String LIST_PERSONS_QUERY = "SELECT first, last, email  FROM person";
        List<Person> out = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(LIST_PERSONS_QUERY)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                out.add(new Person(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return out;
    }

    public List<Milestones> findMilestones() throws ParseException {
        final String LIST_MS_QUERY = "SELECT msname, msdesc, msduedate  FROM milestones";
        List<Milestones> out = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(LIST_MS_QUERY)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                out.add(new Milestones(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return out;
    }

    private void loadResource(String name) {
        try {
            String cmd = new Scanner(getClass().getResource(name).openStream()).useDelimiter("\\Z").next();
            PreparedStatement ps = connection.prepareStatement(cmd);
            ps.execute();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
