import java.util.List;

//system search by price
public class PriceSearchStrategy implements FlightSearchStrategy{
    public PriceSearchStrategy(){
        System.out.print("system search by price lower than ");
    }
    @Override
    public void search(List<Flight> flights, String price) {
        double priceDouble = Double.parseDouble(price);
        for (Flight flight : flights) {
            if(flight.getPrice() <= priceDouble){
                flight.Info();
            }
        }
    }
}
