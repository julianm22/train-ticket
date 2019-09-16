package model;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Ticket {
    private Train train;
    private static int counter = 100;
    private String pnr;
    private Date travelDate;
    private TreeMap<Passenger, Double> passengers = new TreeMap<>(new Comparator<Passenger>() {
        @Override
        public int compare(Passenger o1, Passenger o2) {
            return o1.getName().compareTo(o2.getName());
        }
    });


    public Ticket( Date travelDate, Train train) {
        this.train = train;
        this.travelDate = travelDate;
        pnr = generatePNR();
        counter++;
    }


    private String generatePNR(){
        pnr = "";
        pnr = pnr+this.train.getSource().charAt(0);
        pnr = pnr+this.train.getDestination().charAt(0);
        pnr = pnr+"_";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(this.travelDate);
        pnr = pnr+date;
        pnr = pnr+"_";
        pnr = pnr+this.counter;
        return pnr;
    }

    private double calcPassengerFare(Passenger p){
        double fare = 0;
        if(p.getAge()<=12)
        {
            fare = this.train.getTicketPrice()*.5;
        } else if(p.getAge()>=60)
        {
            fare = this.train.getTicketPrice()*.4;
        } else if(p.getGender()=='F'){
            fare = this.train.getTicketPrice()*.75;
        } else {
            fare = this.train.getTicketPrice();
        }
        return fare;
    }

    public void addPassenger(String name, int age, char gender){
        Passenger p = new Passenger(name, age, gender);
        double fare = this.calcPassengerFare(p);
        this.passengers.put(p, fare);
    }

    private double calculateTotalTicketPrice(){
        double sum = 0;
        for(double price : passengers.values())
        {
            sum+=price;
        }
        return sum;
    }

    private StringBuilder generateTicket(){
        StringBuilder str = new StringBuilder();
        str.append("PNR\t\t: ");
        str.append(this.pnr+"\n");
        str.append("Train no\t: ");
        str.append(this.train.getTrainNo()+"\n");
        str.append("Train Name\t: ");
        str.append(this.train.getTrainName()+"\n");
        str.append("From\t\t: ");
        str.append(this.train.getSource()+"\n");
        str.append("To\t\t: ");
        str.append(this.train.getDestination()+"\n");
        str.append("\n");
        str.append("Passengers: \n");
        str.append("Name\t\tAge\tGender\tFare\n");
        for(Map.Entry<Passenger,Double> entry : passengers.entrySet()) {
            Passenger p = entry.getKey();
            Double value = entry.getValue();
            String fare = new DecimalFormat("#,###.00").format(value);
            str.append(p.getName()+"\t\t"+p.getAge()+"\t"+p.getGender()+"\t"+fare+"\n");
        }
        String totalPrice = new DecimalFormat("#,###.00").format(calculateTotalTicketPrice());
        str.append("Total Price: "+totalPrice);
        return str;
    }

    public void writeTicket(){
        FileWriter fw;
        String strToFile;

        strToFile = this.generateTicket().toString();
        try {
            fw = new FileWriter("Ticket.txt");
            fw.write(strToFile);
            fw.flush();
            fw.close();
            System.out.println("Written data into character stream successfully...");
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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

    public TreeMap<Passenger, Double> getPassengers() {
        return passengers;
    }

    public void setPassengers(TreeMap<Passenger, Double> passengers) {
        this.passengers = passengers;
    }

}
