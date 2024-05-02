/**
 * The Observer interface is part of the Observer design pattern. It defines the method that observers must implement
 * to get notified of changes in the subject they are observing.
 */
public interface Observer {

    /**
     * Updates the observer with a message from the subject. This method is called when the subject's state changes.
     *
     * @param message A string containing the update information from the subject.
     */
    void update(String message);
}
