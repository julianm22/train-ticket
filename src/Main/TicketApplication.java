package Main;

import dao.TrainDAO;
import model.Ticket;
import model.Train;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class TicketApplication {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int trainNo;
        int numOfPassengers;
        Date travelDate = null;
        String pName;
        int pAge;
        char pGender;
        Train train = null;

        boolean trainExists = false;
        while(!trainExists) {
            try {
                System.out.println("Enter the train number: ");
                trainNo = input.nextInt();
                input.nextLine();

                train = TrainDAO.findTrain(trainNo);
                if (train == null) {
                    throw new Exception("Train with given number does not exist");
                }
                trainExists = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        boolean isInvalidDate = true;
        while(isInvalidDate) {
            System.out.println("Enter the travel date in dd/MM/yyyy format: ");
            String date = input.nextLine();
            String format = "dd/MM/yyyy";
            SimpleDateFormat formatter1 = new SimpleDateFormat(format);
            try {
                travelDate = formatter1.parse(date);
                Date date1 = new Date();
                int validDate = travelDate.compareTo(date1);
                if (validDate == -1) {
                    throw new Exception("Travel date cannot be in the past. ");
                }
                isInvalidDate = false;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        Ticket ticket = new Ticket(travelDate, train);

        System.out.println("Enter the number of passengers: ");
        String numberOfPassengers = input.nextLine();
        numOfPassengers = Integer.parseInt(numberOfPassengers);

        for(int i=0;i<numOfPassengers;i++)
        {
            try {
                System.out.println("Enter info for passenger" + (i + 1) + "...");
                System.out.println("------------------------------------------");
                System.out.println("Enter passenger name: ");
                pName = input.nextLine();
                System.out.println(pName);
                System.out.println("Enter passenger age: ");
                String age = input.nextLine();
                pAge = Integer.parseInt(age);
                if(pAge<0||pAge>=120) {
                    throw new Exception("Not a valid age.");
                }
                System.out.println("Enter passenger gender (M/F): ");
                String gender = input.nextLine();
                pGender = gender.toUpperCase().charAt(0);
                if(pGender!='M'&&pGender!='F') {
                    throw new Exception("Not a valid gender.");
                }
                ticket.addPassenger(pName, pAge, pGender);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        ticket.writeTicket();

    }
}
