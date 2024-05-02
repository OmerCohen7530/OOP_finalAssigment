public class Passenger extends PeopleObserver{
    private boolean visa;
    private boolean isAccessibility;

    public Passenger(String id, String name, boolean isMan, boolean visa, boolean isAccessibility) {
        super(id, name, isMan);
        this.visa = visa;
        this.isAccessibility = isAccessibility;
    }

    public boolean isVisa() {
        return visa;
    }

    public boolean isAccessibility() {
        return isAccessibility;
    }
}
