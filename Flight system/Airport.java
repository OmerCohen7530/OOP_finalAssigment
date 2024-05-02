import java.util.ArrayList;
import java.util.List;

public class Airport {
    private List<Airline> Airlines;
    private String name;
    public Airport(String name){
        Airlines = new ArrayList<>();
        this.name = name;
        System.out.println("Welcome to " + name);
    }
    public void addAirline(Airline air){
        if (!this.Airlines.contains(air)){
            Airlines.add(air);
        }
    }

    public List<Airline> getAirlines() {
        return Airlines;
    }
}
