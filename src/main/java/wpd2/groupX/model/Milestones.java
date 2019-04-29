package wpd2.groupX.model;

/**
 * Created by Administrator on 05/03/2019.
 */
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
public class Milestones {
    private String name;
    private String description;
    private Date duedate;
    private Date completedate;
    private boolean iscomplete;
    private String id;
    public Milestones(String name, String description, String date) throws ParseException {
        this.name = name;
        this.description = description;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.duedate  = dateFormat.parse(date);
        this.id = "0";
    }
    public Milestones(String name, String description, String date, String id) throws ParseException {
        this.name = name;
        this.description = description;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.duedate  = dateFormat.parse(date);
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String n){
        this.name = n;
    }

    public String getDescription() {
        return this.description;
    }
    public String getID() {
        return this.id;
    }

    public void setDescription(String d){
        this.description = d;
    }

    public String getDuedateString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String d = formatter.format(this.getDuedate());
        return d;
    }
    public Date getDuedate(){
        return this.duedate;
    }

    public void setDuedate(Date d){
        this.duedate = d;
    }
    public void setDuedate(String d) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.duedate  = dateFormat.parse(d);
    }

    public Date getCompleteDate(){
        return this.completedate;
    }

    public void setCompletedate(Date d){
        this.completedate = d;
    }

    public void setCompletedate(String d) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.completedate  = dateFormat.parse(d);
    }

    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String d = formatter.format(this.getDuedate());
        return (name + " " + description + " " + d);
    }

}
