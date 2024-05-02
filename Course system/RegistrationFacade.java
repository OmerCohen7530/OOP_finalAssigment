import java.util.Scanner;

/**
 * RegistrationFacade serves as an interface to simplify the interactions between the client and the underlying system
 * processes involved in user registration and course management.
 */
public class RegistrationFacade {
    // Reference to the SystemController to manage user sessions and courses.
    private SystemController system = SystemController.getInstance();

    /**
     * Attempts to log a user into the system using the provided credentials.
     * Notifies the user of the login status or potential errors.
     *
     * @param id       The user's identifier.
     * @param password The user's password.
     */
    public void login(String id, String password) {
        User someUser = system.getUser(id);
        if(system.is_logged_in(someUser)){
            System.out.println(someUser.getName() + " is already logged in");
            return;
        }
        if(system.getConnected().size() < 100){
            for(User user: system.getRegistered()) {
                if(user.authenticate(id, password)){
                    system.getConnected().add(user);
                    System.out.println(user.getName() + " is logged in");
                    return;
                }
            }
            System.out.println("ID or password is incorrect");
        } else {
            System.out.println("System is full");
        }
    }

    /**
     * Logs a user out of the system and updates the system state accordingly.
     *
     * @param user The user to log out.
     */
    public void logOut(User user){
        if(system.getConnected().contains(user)){
            system.getConnected().remove(user);
            System.out.println(user.getName() + " is logged out");
        } else {
            System.out.println(user.getName() + " is already logged out");
        }
    }

    /**
     * Handles the registration of a student for a specific course, including waiting list management.
     *
     * @param student The student attempting to register.
     * @param course  The course to register for.
     */
    public void registerForCourse(Student student, Course course) {
        // Verify that the student is logged in before proceeding.
        if(!system.is_logged_in(student)){
            System.out.println("ERROR: Must log in before");
            return;
        }

        // Check if the student is already registered for the course.
        if(course.isStudent(student)) {
            student.update("You are already registered for this course");
            return;
        }

        // If the course is full, offer to register the student for notifications.
        if (course.isFull()){
            Scanner input = new Scanner(System.in);
            student.update("The course is full. Would you like to receive an update if a space becomes available? [y/n]: ");
            String choice = input.nextLine();
            if ("y".equalsIgnoreCase(choice)) {
                course.registerObserver(student);
            }
        } else {
            // If there is space, register the student and notify them.
            if(course.addStudent(student)){
                student.update("You have successfully registered for the course");
            }
        }
    }

    /**
     * Handles the unregistration of a student from a specific course.
     *
     * @param student The student attempting to unregister.
     * @param course  The course to unregister from.
     */
    public void UnregisterForCourse(Student student, Course course) {
        // Verify that the student is logged in before proceeding.
        if(!system.is_logged_in(student)){
            System.out.println("ERROR: Must log in before");
            return;
        }

        // Check if the student is not already registered for the course.
        if(!course.isStudent(student)) {
            student.update("You are not registered for this course");
            return;
        }

        // Unregister the student and notify them.
        if(course.removeStudent(student)){
            student.update("You have successfully unregistered from the course");
            course.isNowAvailable();
        }
    }

    /**
     * Facilitates registration for a course by course ID, ensuring the course exists before attempting to register.
     *
     * @param student The student attempting to register.
     * @param id The unique identifier for the course.
     */
    public void registerForCourse(Student student, String id){
        Course course = system.getCourse(id);
        if(course != null){
            registerForCourse(student, course);
        }else{
            System.out.println("ERROR: This course does not exist");
        }
    }

    /**
     * Facilitates unregistration from a course by course ID, ensuring the course exists before attempting to unregister.
     *
     * @param student The student attempting to unregister.
     * @param id The unique identifier for the course.
     */
    public void UnregisterForCourse(Student student, String id){
        Course course = system.getCourse(id);
        if(course != null){
            UnregisterForCourse(student, course);
        }else{
            System.out.println("ERROR: This course does not exist");
        }
    }

    /**
     * Manages the addition of a new course into the system by validating the creator's permissions and leveraging the
     * CourseFactory to create and register the course if it doesn't already exist.
     *
     * @param creator  The user attempting to create the course, must have permissions to create courses.
     * @param id       The unique identifier for the new course.
     * @param name     The name of the course.
     * @param type     The type of the course (e.g., Seminar, Elective, Mandatory).
     * @param capacity The maximum number of students that the course can accommodate.
     */
    public Course addCourse(User creator, String id, String name, String type, int capacity) {
        // Ensure the creator is logged in before proceeding.
        if(!system.is_logged_in(creator)){
            System.out.println("ERROR: Must log in before attempting to create a course");
            return null;
        }

        // Check if the creator has the necessary permissions to create a course.
        if (!creator.canCreateCourse()) {
            System.out.println("Unauthorized attempt to create a course");
            return null;
        }

        // Use the CourseFactory to create or retrieve an existing course.
        CourseFactory factory = new CourseFactory();
        Course course = factory.getCourse(id, name, type, capacity);
        if(course == null) {
            System.out.println("Invalid course type specified");
            return null;
        }

        // Add the new or existing course to the system.
        system.addCourse(course);
        return course;
    }

}
