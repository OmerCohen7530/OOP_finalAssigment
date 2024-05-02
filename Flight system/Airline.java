import java.util.ArrayList;
import java.util.List;

public class Airline implements AirlineComponent, Subject{
    private List<Flight> flightsOUT;
    private List<Flight> flightsIN;
    private List<AirlineComponent> components;
    private String name;
    private List<PeopleObserver> observers;
    public Airline(String name){
        this.name = name;
        this.flightsOUT = new ArrayList<>();
        this.flightsIN = new ArrayList<>();
        this.components = new ArrayList<>();
        this.observers = new ArrayList<>();
        System.out.println("new airline "+name+" was created");
    }
    //use for add sub company
    //using this method does not mean that the flights of the sub company is too at the same airport
    public void addComponent(AirlineComponent component) {
        if (!this.components.contains(component)){
            components.add(component);
        }
    }
    @Override
    public void Info() {
        String ans = "Airline: " + name;
        if(!components.isEmpty()) ans = "Parent "+ans;
        System.out.println(ans);
        for (AirlineComponent component : components) {
            component.Info();
        }
    }

    public void addFlight(Flight fl) {
        if(fl.getIN_OUT() == "IN") {
            if (!this.flightsIN.contains(fl)) {
                flightsIN.add(fl);
                notify("There is a new flight to "+ fl.getDestenation() + ". Hurry up to register before the places run out");
            }
        }else {
            if (!this.flightsOUT.contains(fl)) {
                flightsOUT.add(fl);
                notify("There is a new flight to "+ fl.getDestenation() + ". Hurry up to register before the places run out");
            }
        }
    }

    public List<Flight> getFlightsIN() {
        return flightsIN;
    }
    public List<Flight> getFlightsOUT() {
        return flightsOUT;
    }

    public List<AirlineComponent> getComponents() {
        return components;
    }

    //Anyone who buys a ticket automatically joins the list of updates
    public void buyTicket(Flight fl, PeopleObserver someone){
        if(fl.isActive()) {
            if (fl.buy(someone) == 1) {
                someone.update("Payment done. enjoy your flight!");
                addObserver(someone);
            } else {
                someone.update("The number of places is full. Please look for another flight");
            }
        }else{
            someone.update("You are trying to access a flight that does not exist");
        }
    }
    public void cancelTicket(Flight fl, PeopleObserver someone){
        if(fl.isActive()) {
            if (fl.cancel(someone) == 1) {
                someone.update("The cancellation is complete. See you again next time");
            } else {
                someone.update("There was no need to cancel because you are not registered for this flight");
            }
        }else{
            someone.update("You are trying to access a flight that does not exist");
        }
    }

    public void discount(double percent){
        double mult = percent/100;
        mult = 1-mult;
        for(Flight flight: this.flightsIN){
            double finalPrice = mult * flight.getPrice();
            flight.setPrice(finalPrice);
        }
        for(Flight flight: this.flightsOUT){
            double finalPrice = mult * flight.getPrice();
            flight.setPrice(finalPrice);
        }
        notify("All flights are now " + percent + " % off");
    }
    public void cancelFlight(Flight fl){
        if(fl.isActive()) {
            if (fl.getIN_OUT() == "IN" && this.flightsIN.contains(fl)) {
                flightsIN.remove(fl);
                notify("The flight " + fl.getFlightNumber() + " to " + fl.getDestenation() + " is canceled");
            } else if (fl.getIN_OUT() == "OUT" && this.flightsOUT.contains(fl)) {
                flightsOUT.remove(fl);
                notify("The flight " + fl.getFlightNumber() + " to " + fl.getDestenation() + " is canceled");
            }
        }else {
            System.out.println("The flight " + fl.getFlightNumber() + " is already canceled");
        }
        fl.setActive(false);
    }

    public void changeDest(Flight fl, String dest){
        if(fl.isActive()) {
            fl.setDestenation(dest);
            notify("The flight " + fl.getFlightNumber() + " changed destination to " + dest);
        }else{
            System.out.println("This flight is canceled");
        }
    }

    @Override
    public void addObserver(PeopleObserver observer) {
        if(!this.observers.contains(observer)){
            this.observers.add(observer);
            System.out.println(observer.getName()+" follows "+this.name+" updates");
        }
    }

    @Override
    public void removeObserver(PeopleObserver observer) {
        this.observers.remove(observer);
        System.out.println(observer.getName()+" unfollows "+this.name+" updates");
    }

    @Override
    public void notify(String message) {
        for (Observer observer : this.observers) {
            observer.update(message);
        }
    }
}
