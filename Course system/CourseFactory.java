import java.util.ArrayList;
import java.util.List;

/**
 * CourseFactory is a class that utilizes the Factory Method design pattern to create and manage instances of Courses.
 * It ensures that only unique instances of a course with a given ID are created within the system.
 */
public class CourseFactory {
    private static List<Course> courses  = new ArrayList<>(); // List to store all courses

    /**
     * Retrieves an existing course or creates a new course with the provided details.
     * If a course with the given ID already exists, it is returned instead of creating a new one.
     *
     * @param id        The unique identifier for the course.
     * @param name      The name of the course.
     * @param type      The type of the course (Seminar, Elective, Mandatory).
     * @param capacity  The capacity of the course.
     * @return          An instance of Course or null if the course type is invalid.
     */
    public Course getCourse(String id, String name, String type, int capacity) {
        // Check if a course with the same ID already exists
        // Flyweight Design Pattern
        for(Course course: courses){
            if(course.getId() == id){
                return course;
            }
        }

        // Create a new course based on the type specified
        Course course = switch (type) {
            case "Seminar" -> new SeminarCourse(id, name, type, capacity);
            case "Elective" -> new ElectiveCourse(id, name, type, capacity);
            case "Mandatory" -> new MandatoryCourse(id, name, type, capacity);
            default -> null;
        };

        // Check for null in case of invalid course type and inform user
        if(course == null){
            System.out.println("Invalid course creation");
        }
        courses.add(course);
        return course;
    }
}
