public abstract class PeopleObserver implements Observer{
    private String name;
    private boolean isMan;
    private String id;

    public PeopleObserver(String id, String name, boolean isMan){
        this.id = id;
        this.name = name;
        this.isMan = isMan;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isMan() {
        return isMan;
    }
    @Override
    public void update(String message) {
        String ans  = "Hello " + name + ": " + message;
        System.out.println(ans);
    }
}
