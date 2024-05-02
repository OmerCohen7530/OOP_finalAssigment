public class Student extends User{
    @Override
    public boolean canCreateCourse() {
        return false;
    }

    public Student(String id, String name, String password) {
        super(id,name, password);
    }
}
