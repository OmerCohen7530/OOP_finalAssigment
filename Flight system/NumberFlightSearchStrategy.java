import java.util.List;
//system search by number flight
public class NumberFlightSearchStrategy implements FlightSearchStrategy{
    public NumberFlightSearchStrategy(){
        System.out.print("system search by number flight of ");
    }
    @Override
    public void search(List<Flight> flights, String num) {
        for (Flight flight : flights) {
            if(flight.getFlightNumber() == num){
                flight.Info();
            }
        }
    }
}
