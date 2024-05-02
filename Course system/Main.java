public class Main {

    // Check if the system can have more than 100 participants
    public static void test1(SystemController system) {
        String id = " ";
        for (int i = 0; i < 200; i++) {
            Student omer  = new Student(id+i,"omer","123");
            system.logIn(id+i,"123");
        }
    }

    // Check if the system can create duplicate courses
    // Courses are different by id! the rest can be identical
    public static void test2(SystemController system) {
        Lecturer lecturer = new Lecturer("0", "Yossi","123");
        system.logIn("0","123");
        system.createNewCourse(lecturer, "0", "OOP", "Seminar", 5);
        system.createNewCourse(lecturer, "0", "OOP", "Elective", 5);
    }

    // Check if a student can register to course
    // Run it after test2()
    public static void test3(SystemController system) {
        Student omer  = new Student("1" ,"omer","123");
        system.logIn("1","123");
        system.registerForCourseById(omer, "0");
    }

    // Check updates about course
    public static void test_observer(SystemController system) {
        Lecturer lecturer = new Lecturer("0", "Yossi","123");
        system.logIn("0","123");
        system.createNewCourse(lecturer, "0", "OOP", "Seminar", 1);

        Student omer  = new Student("1" ,"omer","123");
        Student michal  = new Student("2" ,"michal","123");
        system.logIn("1","123");
        system.logIn("2","123");

        system.registerForCourseById(omer, "0");
        system.registerForCourseById(michal, "0");

        system.UnregisterForCourseById(omer, "0");
        system.UnregisterForCourseById(omer, "0");
    }

    //check Decorator
    public static void test_decorator(SystemController system) {
        Lecturer amit  = new Lecturer("3","amit","123");
        system.logIn("3","123");
        Course OOP_COURSE = system.createNewCourse(amit,"1","OOP", "Elective",1);

        System.out.println(OOP_COURSE.getDescription());

        //Decorator
        OOP_COURSE = new MasterCourse(OOP_COURSE);
        system.update(OOP_COURSE);

        System.out.println(OOP_COURSE.getDescription());
    }
    public static void test_singelton(SystemController system){
        SystemController another_system = SystemController.getInstance();
        if(system == another_system){
            System.out.println("Singelton DP Done successfully");
        }else {
            System.out.println("ERROR: not singelton DP");
        }
    }
    public static void test_flyweight(SystemController system) {
        Lecturer lecturer = new Lecturer("0", "Yossi","123");
        system.logIn("0","123");

        Course course1 = system.createNewCourse(lecturer, "0", "OOP", "Seminar", 1);
        Course course2 = system.createNewCourse(lecturer, "0", "other OOP", "Seminar", 1);

        if(course1 == course2){
            System.out.println("Flyweight DP Done successfully");
        }else {
            System.out.println("ERROR: not Flyweight DP");
        }
    }
    public static void test_factory(SystemController system) {
        Lecturer lecturer = new Lecturer("0", "Yossi","123");
        system.logIn("0","123");

        Course course1 = system.createNewCourse(lecturer, "0", "OOP", "Seminar", 1);
        Course course2 = system.createNewCourse(lecturer, "1", "OOP", "Elective", 1);

        if(course1 instanceof SeminarCourse && course2 instanceof ElectiveCourse){
            System.out.println("Factory DP Done successfully");
        }else {
            System.out.println("ERROR: not Factory DP");
        }
    }

    public static void main(String[] args) {
        SystemController ArielUniversity = SystemController.getInstance();
//        test1(ArielUniversity);
//        test2(ArielUniversity);
//        test3(ArielUniversity);


//        test_decorator(ArielUniversity);
//        test_singelton(ArielUniversity);
//        test_observer(ArielUniversity);
//        test_flyweight(ArielUniversity);
//        test_factory(ArielUniversity);
        //test_Facade - It can be tested by looking at the implementation of the RegistrationFacade class
        
    }
}
