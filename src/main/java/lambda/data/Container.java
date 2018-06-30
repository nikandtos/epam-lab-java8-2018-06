package lambda.data;


public class Container {

    Person person;

    String company;

    int duration;

    public Container(Person person, String company, int duration) {
        this.person = person;
        this.company = company;
        this.duration = duration;
    }

    public Container(Person person, String company) {
        this.person = person;
        this.company = company;
    }

    public Person getPerson() {

        return person;
    }

    public String getCompany() {
        return company;
    }

    public int getDuration() {
        return duration;
    }
}