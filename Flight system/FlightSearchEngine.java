import java.util.List;

/**
 * A FlightSearchEngine class that uses a strategy pattern to search for flights based on various criteria.
 * It allows for searching flights both in and out of an airport using a specified search strategy.
 */
public class FlightSearchEngine {
    private FlightSearchStrategy searchStrategy;  // The search strategy used for finding flights.

    /**
     * Sets the flight search strategy for this search engine.
     *
     * @param searchStrategy The search strategy to be used for finding flights.
     */
    public void SetFlightSearchEngine(FlightSearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    /**
     * Searches for flights based on the provided search criteria.
     * It searches through all airlines present at the airport, both for incoming and outgoing flights.
     *
     * @param airport The airport from which to base the search. It should have a list of airlines and their respective flights.
     * @param search  The search criteria (e.g., destination, date, flight number) used to find matching flights.
     */
    public void search(Airport airport, String search) {
        System.out.println(search + ":");  // Prints the search criteria to the console.

        // Retrieve a list of airlines at the airport
        List<Airline> airlines = airport.getAirlines();

        // Iterate over each airline to search their incoming and outgoing flights using the search strategy
        for (Airline airline : airlines) {
            // Get incoming flights and apply search
            List<Flight> flightsIn = airline.getFlightsIN();
            searchStrategy.search(flightsIn, search);

            // Get outgoing flights and apply search
            List<Flight> flightsOut = airline.getFlightsOUT();
            searchStrategy.search(flightsOut, search);
        }
    }
}
