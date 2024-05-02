/**
 * CourseDecorator is an abstract class that extends Course and follows the Decorator design pattern.
 * It allows for dynamic extension of Course objects' responsibilities without modifying the original Course classes.
 * This is achieved by 'wrapping' Course objects with additional functionalities.
 */
public abstract class CourseDecorator extends Course {
    // The Course instance that is being decorated
    protected Course decoratedCourse;

    /**
     * Constructs a CourseDecorator with the specified Course.
     *
     * @param decoratedCourse The Course object that this decorator is wrapping.
     */
    public CourseDecorator(Course decoratedCourse) {
        // Initializes the decorator with information from the decorated course object
        super(decoratedCourse.getId(), decoratedCourse.getName(), decoratedCourse.getType(), decoratedCourse.getCapacity());
        this.decoratedCourse = decoratedCourse;
    }

    /**
     * Abstract method getDescription that will be implemented by concrete decorators to add additional information to
     * the course description.
     *
     * @return A string that describes the decorated course, including any additional responsibilities added by the decorator.
     */
    @Override
    public abstract String getDescription();
}
