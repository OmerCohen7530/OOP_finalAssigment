import java.util.List;

/**
 * Interface for defining different strategies for flight search.
 */
public interface FlightSearchStrategy {
    /**
     * Performs a flight search based on the provided list of flights and search criteria.
     */
    public void search(List<Flight> flights, String temp);
}
