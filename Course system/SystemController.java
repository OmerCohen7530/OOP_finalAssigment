import java.util.ArrayList;
import java.util.List;

/**
 * The SystemController class implements the Singleton pattern to ensure only one instance of the controller is used throughout the system.
 * It manages user registration, course management, and user sessions.
 */
public class SystemController {
    private static SystemController instance; // Singleton instance of SystemController
    private RegistrationFacade facade; // Facade for handling registration operations
    private List<Course> courses = new ArrayList<>(); // List to store all courses
    private List<User> registered = new ArrayList<>(); // List of registered users
    private List<User> connected = new ArrayList<>(); // List of currently logged-in users

    // Private constructor to prevent instantiation from outside the class
    private SystemController() {}

    /**
     * Returns the singleton instance of SystemController, creating it if it doesn't already exist.
     * @return the singleton instance
     */
    public static synchronized SystemController getInstance() {
        if (instance == null) {
            instance = new SystemController();
        }
        return instance;
    }

    /**
     * Creates a new course and adds it to the system through the RegistrationFacade.
     * @param creator The user creating the course
     * @param id The course ID
     * @param name The course name
     * @param type The course type
     * @param capacity The maximum number of students allowed in the course
     */
    public Course createNewCourse(User creator, String id, String name, String type, int capacity) {
        this.facade = new RegistrationFacade();
        return facade.addCourse(creator, id, name, type, capacity);
    }

    /**
     * Registers a new user to the system if they are not already registered.
     * @param user The user to register
     */
    public void signUp(User user){
        if(!registered.contains(user)){
            for(User temp: registered){
                if(temp.getId().equals(user.getId())){
                    System.out.println("ERROR: There is already an identical ID card in the system");
                    return;
                }
            }
            registered.add(user);
        }else{
            System.out.println("You are already a registered user");
        }
    }

    /**
     * Attempts to log in a user by checking credentials through the RegistrationFacade.
     * @param id The user's ID
     * @param password The user's password
     */
    public void logIn(String id, String password) {
        this.facade = new RegistrationFacade();
        facade.login(id, password);
    }

    /**
     * Logs out a user by removing them from the list of connected users via the RegistrationFacade.
     * @param user The user to log out
     */
    public void logOut(User user){
        this.facade = new RegistrationFacade();
        facade.logOut(user);
    }

    /**
     * Adds a course to the system if it does not already exist.
     * @param course The course to add
     */
    public void addCourse(Course course) {
        if(!courses.contains(course)) {
            courses.add(course);
            System.out.println("The "+course.getName() + " course has been successfully added");
        }else {
            System.out.println("The " + course.getName() + " course already exist");
        }
    }

    /**
     * Registers a student for a course by ID through the RegistrationFacade.
     * @param student The student to register
     * @param id The course ID
     */
    public void registerForCourseById(Student student, String id) {
        this.facade = new RegistrationFacade();
        facade.registerForCourse(student, id);
    }

    /**
     * Unregisters a student from a course by ID through the RegistrationFacade.
     * @param student The student to unregister
     * @param id The course ID
     */
    public void UnregisterForCourseById(Student student, String id) {
        this.facade = new RegistrationFacade();
        facade.UnregisterForCourse(student, id);
    }

    /**
     * Retrieves a course by its ID.
     * @param id The course ID
     * @return The course with the specified ID, or null if not found
     */
    public Course getCourse(String id){
        for(Course course: courses){
            if(course.getId().equals(id)){
                return course;
            }
        }
        return null;
    }

    /**
     * Retrieves a user by their ID, searching both connected and registered lists.
     * @param id The user ID
     * @return The user with the specified ID, or null if not found
     */
    public User getUser(String id){
        for (User user: connected)
            if(user.getId().equals(id))
                return user;
        for (User user: registered)
            if(user.getId().equals(id))
                return user;
        return null;
    }

    // Getters for courses, connected users, and registered users
    public List<Course> getCourses() { return courses; }
    public List<User> getConnected() { return connected; }
    public List<User> getRegistered() { return registered; }

    /**
     * Checks if a user is currently logged in.
     * @param user The user to check
     * @return true if the user is logged in, false otherwise
     */
    public boolean is_logged_in(User user){
        for(User usr: connected){
            if(user.equals(usr)){
                return true;
            }
        }
        return false;
    }

    /**
     * Updates a course's details in the course list if the course exists.
     * @param course The course to update
     */
    public void update(Course course) {
        int indexToUpdate = -1;

        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(course.getId())) {
                indexToUpdate = i;
                break;
            }
        }

        if (indexToUpdate != -1) {
            courses.set(indexToUpdate, course);
        }
    }
}
