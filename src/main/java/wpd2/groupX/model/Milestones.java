package wpd2.groupX.model;

/**
 * Created by Administrator on 05/03/2019.
 */
import java.util.List;

public class Milestones {
    private String milestone;
    private List<String> messages;

    public Milestones(String ms, List<String> m) {
        milestone = ms;
        messages = m;
    }

    public String getMileStone() {
        return milestone;
    }

    public List<String> getMessages() {
        return messages;
    }

}
