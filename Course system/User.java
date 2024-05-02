/**
 * The User class is an abstract representation of a user in the system. It implements the Observer interface,
 * allowing User instances to receive updates. Users can have different roles with different permissions, such as
 * the ability to create courses.
 */
public abstract class User implements Observer {
    protected String id; // Unique identifier for the user
    protected String password; // Password for user authentication
    protected String name; // Name of the user
    protected SystemController system; // Reference to the SystemController

    /**
     * Abstract method defining whether a User can create a course. This must be implemented by subclasses.
     *
     * @return boolean indicating whether the user can create a course.
     */
    public abstract boolean canCreateCourse();

    /**
     * Constructs a new User and registers them with the SystemController.
     *
     * @param id        The unique identifier for the user.
     * @param name      The name of the user.
     * @param password  The password for the user.
     */
    public User(String id, String name, String password) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.system = SystemController.getInstance();
        this.system.signUp(this);
    }

    // Accessor methods for user properties
    public String getId() { return id; }
    public String getName() { return name; }

    /**
     * Authenticates the user by comparing provided credentials with existing ones.
     *
     * @param id        The identifier to be matched for authentication.
     * @param password  The password to be matched for authentication.
     * @return boolean indicating if authentication is successful.
     */
    public boolean authenticate(String id, String password) {
        return this.id.equals(id) && this.password.equals(password);
    }

    /**
     * Updates the user with a message from the observed subject.
     *
     * @param message The message from the subject to be communicated to the user.
     */
    @Override
    public void update(String message) {
        String outputMessage = "Hello " + this.name + ": " + message;
        System.out.println(outputMessage);
    }
}
