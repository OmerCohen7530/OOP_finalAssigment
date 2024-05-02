public class Practitioners extends User{
    @Override
    public boolean canCreateCourse() {
        return true;
    }

    public Practitioners(String id, String name, String password) {
        super(id,name, password);
    }
}
