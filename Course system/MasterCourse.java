/**
 * MasterCourse is a concrete decorator extending the CourseDecorator to enforce master's level course requirements.
 * It modifies the base course's pass grade to reflect the more rigorous standards expected of master's level courses.
 */
public class MasterCourse extends CourseDecorator {

    /**
     * Constructs a MasterCourse with the specified Course.
     *
     * @param decoratedCourse The Course object that this MasterCourse is decorating.
     */
    public MasterCourse(Course decoratedCourse) {
        super(decoratedCourse);
    }

    /**
     * GradeForPass = 80
     */
    @Override
    public String getDescription() {
        // Setting the pass grade for the course to a higher standard
        decoratedCourse.setGradeForPass(80);
        // Retrieving the description from the decorated course object
        return decoratedCourse.getDescription();
    }
}
