package wpd2.groupX.model;

import java.util.ArrayList;
import java.util.List;

public class MileStoneBoard {
    private String name;
    private List<String> milestones;

    public MileStoneBoard() {

        this.milestones = new ArrayList<String>();
    }

    public String getName() {
        return this.name;
    }

    public List<String> getTopics() {
        return this.milestones;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setTopics(List<String> t) {
        this.milestones = t;
    }


    public String toString() {
        return ("name: " + name + ", topics: " + milestones.toString());
    }

}
