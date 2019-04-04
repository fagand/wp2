package wpd2.groupX.model;

/**
 * Created by Administrator on 05/03/2019.
 */
import java.util.List;

public class Milestones {
    private String topic;
    private List<String> messages;

    public Milestones(String t, List<String> m) {
        topic = t;
        messages = m;
    }

    public String getTopic() {
        return topic;
    }

    public List<String> getMessages() {
        return messages;
    }

}
