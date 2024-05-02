/**
 * The Subject interface is a core component of the Observer design pattern. It provides methods to attach and detach
 * Observers, as well as to notify all Observers of state changes. This interface can be implemented by any class that
 * wishes to be observed.
 */
public interface Subject {

    /**
     * Registers an Observer to be notified of changes.
     * @param observer The Observer to be added.
     */
    void registerObserver(Observer observer);

    /**
     * Removes an Observer from the notification list.
     * @param observer The Observer to be removed.
     */
    void removeObserver(Observer observer);

    /**
     * Notifies all registered Observers of a message.
     * @param message The message to be communicated to the Observers.
     */
    void notifyObservers(String message);

    /**
     * Provides a description or state representation of the Subject.
     * @return A string describing the current state or information of the Subject.
     */
    String getDescription();
}
