import java.util.List;
//system search by destination
public class DestenationSearchStrategy implements FlightSearchStrategy{
    public DestenationSearchStrategy(){
        System.out.print("system search by destination of ");
    }
    @Override
    public void search(List<Flight> flights, String dest) {
        for (Flight flight : flights) {
            if(flight.getDestenation() == dest){
                flight.Info();
            }
        }
    }
}
