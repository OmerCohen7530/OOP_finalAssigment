public class main {
    public static void main(String[] args){

        Airport BenGurion = new Airport("BenGurion");
        System.out.println();

        Airline israAir = new Airline("Israel_Air");
        Airline turkish = new Airline("Turkish_Air");
        Airline argentinaAir = new Airline("Argentina_Air");
        Airline sudan = new Airline("Sudan_Air");
        System.out.println();

        BenGurion.addAirline(israAir);
        BenGurion.addAirline(turkish);
        BenGurion.addAirline(argentinaAir);
        BenGurion.addAirline(sudan);

        Flight flight1 = new Flight("100","turkey", 1000,"OUT");
        israAir.addFlight(flight1);
        Flight flight2 = new Flight("101","turkey", 1200,"OUT");
        israAir.addFlight(flight2);
        Flight flight3 = new Flight("102","israel", 1300,"OUT");
        israAir.addFlight(flight3);
        Flight flight4 = new Flight("103","lebanon", 150,"IN");
        turkish.addFlight(flight4);
        Flight flight5 = new Flight("104","lebanon", 160,"IN");
        argentinaAir.addFlight(flight5);

        FlightSearchEngine SearchStrategy = new FlightSearchEngine();

        SearchStrategy.SetFlightSearchEngine(new PriceSearchStrategy());
        SearchStrategy.search(BenGurion,"300");
        System.out.println();

        SearchStrategy.SetFlightSearchEngine(new NumberFlightSearchStrategy());
        SearchStrategy.search(BenGurion,"105");
        System.out.println();

        SearchStrategy.SetFlightSearchEngine(new DestenationSearchStrategy());
        SearchStrategy.search(BenGurion,"turkey");
        System.out.println();

        turkish.addComponent(israAir);
        turkish.addComponent(argentinaAir);
        argentinaAir.addComponent(sudan);
        turkish.Info();
        System.out.println();

        Passenger omer = new Passenger("1","omer",true, false, false);
        Passenger alice = new Passenger("2", "Alice", true, true, false);
        Passenger bob = new Passenger("3", "Bob", false, true, true);

        Worker shachar = new Worker("1","shachar", true, "full stack", 27000);
        Worker emily = new Worker("2", "Emily", false, "backend", 32000);
        Worker jake = new Worker("3", "Jake", true, "frontend", 25000);
        Worker sophia = new Worker("4", "Sophia", false, "data analysis", 38000);

        israAir.addObserver(shachar);
        israAir.addObserver(emily);
        israAir.addObserver(bob);
        israAir.addObserver(jake);

        turkish.addObserver(sophia);
        turkish.addObserver(bob);
        System.out.println();

        turkish.discount(10);
        System.out.println();

        israAir.changeDest(flight4,"japan");
        System.out.println();

        turkish.cancelFlight(flight4);
        System.out.println();

        argentinaAir.addObserver(omer);
        argentinaAir.removeObserver(omer);
        argentinaAir.addObserver(omer);
        argentinaAir.addObserver(alice);
        System.out.println();

        argentinaAir.buyTicket(flight5,omer);
        argentinaAir.buyTicket(flight5,shachar);
        System.out.println();

        argentinaAir.cancelTicket(flight5,omer);
        argentinaAir.cancelTicket(flight5,omer);

        argentinaAir.cancelFlight(flight5);
        argentinaAir.cancelFlight(flight5);
        System.out.println();

        argentinaAir.buyTicket(flight5,shachar);
        System.out.println();

        Flight flight6 = new Flight("105","lebanon", 150,"OUT");
        argentinaAir.addFlight(flight6);
        System.out.println();
    }
}
