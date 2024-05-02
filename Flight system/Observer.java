/**
 * Interface for observers who listen for updates from a subject.
 */
public interface Observer {

    /**
     * Method called when the subject sends an update.
     */
    void update(String message);
}
