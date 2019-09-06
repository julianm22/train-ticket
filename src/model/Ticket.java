package model;

import java.util.Date;
import java.util.TreeMap;

public class Ticket {
    private Train train;
    private int counter;
    private String pnr;
    private Date travelDate;
    private TreeMap<Passenger, Integer> passengers;


    public Ticket( Date travelDate, Train train) {
        this.train = train;
        this.travelDate = travelDate;

    }


    private static String generatePNR(){
        return "";

    }

    private static double calcPassengerFare(Passenger p){

        return 0;
    }

    public static void addPassenger(String name, int age, char gender){

    }

    private static double calculateTotalTicketPrice(){
        return 0;
    }

    private static StringBuilder generateTicket(){
        return  new StringBuilder();
    }

    public static void writeTicket(){

    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public TreeMap<Passenger, Integer> getPassengers() {
        return passengers;
    }

    public void setPassengers(TreeMap<Passenger, Integer> passengers) {
        this.passengers = passengers;
    }

}
