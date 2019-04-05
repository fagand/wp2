package wpd2.groupX.model;

public class Person {

    private final String first;
    private final String last;
    private final String email;

    public Person(String first, String last, String email) {
        this.first = first;
        this.last = last;
        this.email = email;
    }

    public String getFirst(){
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getEmail() {
        return email;
    }
}
