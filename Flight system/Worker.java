public class Worker extends PeopleObserver{
    private String job;
    private int salary;

    public Worker(String id, String name, boolean isMan, String job, int salary) {
        super(id, name, isMan);
        this.job = job;
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public String getJob() {
        return job;
    }
}
