/**
 * Interface for subjects that observers can subscribe to.
 */
public interface Subject {

    /**
     * Adds an observer to the list of subscribers.
     */
    void addObserver(PeopleObserver observer);

    /**
     * Removes an observer from the list of subscribers.
     */
    void removeObserver(PeopleObserver observer);

    /**
     * Notifies all observers about an event with a message.
     */
    void notify(String message);
}
