public class Lecturer extends User{
    @Override
    public boolean canCreateCourse() {
        return true;
    }

    public Lecturer(String id, String name, String password) {
        super(id,name, password);
    }
}
