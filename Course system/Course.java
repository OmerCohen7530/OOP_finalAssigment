import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a Course, implementing the Subject interface as part of the Observer pattern.
 * This class manages course details and enrollment, along with notifying observers about changes.
 */
public abstract class Course implements Subject {
    private List<Observer> observers; // List of observers to be notified on changes
    private List<Student> students; // List of enrolled students
    private String name; // Name of the course
    private String id; // Unique identifier for the course
    private int capacity; // Maximum number of students that can enroll
    private String type; // Type of course, for example, "lecture" or "seminar"
    private int gradeForPass = 60; // Minimum grade required to pass the course

    /**
     * Constructor for Course.
     * @param id        Unique identifier for the course.
     * @param name      Name of the course.
     * @param type      Type of the course.
     * @param capacity  Maximum number of students allowed in the course.
     */
    public Course(String id, String name, String type, int capacity) {
        this.name = name;
        this.id = id;
        this.capacity = capacity;
        this.type = type;
        this.observers = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    // Standard getters and setters for course properties
    public String getId() { return id; }
    public int getCapacity() { return capacity; }
    public String getType() { return type; }
    public String getName() { return name; }

    /**
     * Sets the minimum grade required to pass the course.
     * @param n Minimum passing grade.
     */
    public void setGradeForPass(int n) {
        this.gradeForPass = n;
    }

    /**
     * Checks if the course capacity has been reached.
     * @return true if the course is full, false otherwise.
     */
    public boolean isFull(){
        return students.size() == capacity;
    }

    /**
     * Checks if a student is enrolled in the course.
     * @param student The student to check for enrollment.
     * @return true if the student is enrolled, false otherwise.
     */
    public boolean isStudent(Student student){
        return students.contains(student);
    }

    /**
     * Enrolls a student in the course if there is capacity.
     * @param student The student to add to the course.
     * @return true if the student was successfully added, false if they were already enrolled.
     */
    public boolean addStudent(Student student) {
        if(!students.contains(student) && !isFull()){
            students.add(student);
            return true;
        }
        return false;
    }

    /**
     * Notifies observers that a spot has become available if the course was previously full.
     */
    public void isNowAvailable(){
        if(students.size() == capacity-1){
            notifyObservers("The course "+this.name+" is now available");
        }
    }

    /**
     * Removes a student from the course and also removes them as an observer.
     * @param student The student to remove.
     * @return true if the student was successfully removed, false otherwise.
     */
    public boolean removeStudent(Student student) {
        if(students.contains(student)){
            students.remove(student);
            removeObserver(student);
            return true;
        }
        return false;
    }

    // Observer pattern methods
    @Override
    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    /**
     * Provides a description of the course with the minimum grade requirement.
     * @return A string describing the course and its grade requirement for passing.
     */
    @Override
    public String getDescription() {
        return "To pass the "+name+" course, you must get a grade of at least: " + gradeForPass;
    }
}
