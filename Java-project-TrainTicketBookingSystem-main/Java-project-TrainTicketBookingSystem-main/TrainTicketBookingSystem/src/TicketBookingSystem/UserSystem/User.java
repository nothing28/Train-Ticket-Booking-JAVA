package TicketBookingSystem.UserSystem;

import TicketBookingSystem.UserAdminTemplate;
import TicketBookingSystem.adminSystem.Train;
import TicketBookingSystem.adminSystem.TrainSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class User extends UserAdminTemplate {

    private Double accountBalance;

    private final HashMap<Integer, Ticket> bookingsList;

    Scanner scanner;


    public User() {
        bookingsList = new HashMap<>();
    }


    public User(String userName, String password, Double accountBalance){
        this();
        this.username = userName;
        this.password = password;
        this.accountBalance = accountBalance;
    }


    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double Acc) {
        this.accountBalance = Acc;
    }


    public void bookTicket(){
        scanner = new Scanner(System.in);
        System.out.print("Enter the train number: ");
        Train train = TrainSystem.isValid(scanner.nextInt());

        if(train == null){
            System.out.println("Invalid Train Number !!");
        }
        else{
            if(train.getAvailableSeats() > 0){
                if(accountBalance < train.getTrainFare()){
                    System.out.println("Sorry your account balance is insufficient");
                }
                else{
                    System.out.println("Your selected Train Details");
                    train.displayTrain();
                    System.out.print("confirm booking y/n: ");
                    switch (scanner.next()){
                        case "y":
                            Ticket ticket = new Ticket(train);
                            accountBalance -= train.getTrainFare();
                            System.out.println("Rs."+train.getTrainFare()+" debited from your account");
                            System.out.println("Current balance : "+getAccountBalance());
                            System.out.println("Booking successful");
                            bookingsList.put(ticket.getTicketNumber(), ticket);
                            ticket.printTicket();
                            break;
                        default:
                            System.out.println("Invalid option !!");
                        case "n":
                            System.out.println("Booking cancelled");
                    }
                }
            }
            else{
                System.out.println("Sorry, No Seats Available");
            }
        }
    }


    public void cancelBooking(){
        if(bookingsList.isEmpty()){
            System.out.println("sorry you have no bookings to cancel");
            return;
        }

        scanner = new Scanner(System.in);
        int ticketNo;
        System.out.print("Enter the ticket number of the booking you want to cancel: ");
        ticketNo = scanner.nextInt();
        if(bookingsList.containsKey(ticketNo)){
            bookingsList.remove(ticketNo);
            System.out.println("Booking cancelled");
        }
        else{
            System.out.println("Invalid ticket number !!");
        }
    }


    public void listBookings(){
        if(bookingsList.isEmpty()){
            System.out.println("------ Your booking list is empty ------");
            return;
        }

        System.out.println("--------------Your Bookings------------------");
        for (Map.Entry<Integer, Ticket> m : bookingsList.entrySet()){
            m.getValue().printTicket();
        }
    }
}