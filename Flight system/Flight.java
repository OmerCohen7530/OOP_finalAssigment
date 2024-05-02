import java.util.ArrayList;
import java.util.List;

public class Flight implements AirlineComponent {
    private String flightNumber;
    private String destenation;
    private double price;
    private String IN_OUT;
    private double capacity; //capacity of the flight
    private List<PeopleObserver> clients;
    private boolean isActive;

    public Flight(String flightNumber, String destenation, double price,String IN_OUT){
        this.flightNumber = flightNumber;
        this.destenation = destenation;
        this.price = price;
        this.IN_OUT = IN_OUT;
        this.capacity = 30;
        this.clients = new ArrayList<>();
        this.isActive = true;
    }

    public List<PeopleObserver> getClients() {
        return clients;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }

    public int buy(PeopleObserver someone){
        if(capacity > 0){
            clients.add(someone);
            capacity--;
            return 1;
        }
        return 0;
    }
    public int cancel(PeopleObserver someone){
        if(!clients.contains(someone)){
            return 0;
        }
        if(capacity < 30 && capacity >= 0){
            clients.remove(someone);
            capacity++;
            return 1;
        }
        return 0;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getPrice() {
        return price;
    }

    public String getIN_OUT() {
        return IN_OUT;
    }

    public String getDestenation() {
        return destenation;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDestenation(String destenation) {
        this.destenation = destenation;
    }

    @Override
    public void Info() {
        String ans = "flight number: "+ flightNumber +" ; dest: "+ destenation+" ; cost: "+ price+" ; IN_OUT: "+ IN_OUT;
        System.out.println(ans);
    }
}
