package wpd2.groupX.model;

import java.util.ArrayList;
import java.util.List;

public class MileStoneBoard {
    private String name;
    private List<Milestones> milestones;

    public MileStoneBoard() {

        this.milestones = new ArrayList<Milestones>();
    }

    public String getName() {
        return this.name;
    }

    public List<Milestones> getMilestones() {
        return this.milestones;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setMilestones(List<Milestones> t) {
        this.milestones = t;
    }

    public void addMilestone(Milestones m){
        this.milestones.add(m);
    }
    public String toString() {
        return ("name: " + name + ", milestones: " + milestones.toString());
    }

}
