package TicketBookingSystem.adminSystem;

import TicketBookingSystem.UserAdminTemplate;
import java.util.Scanner;


public class Admin extends UserAdminTemplate {

    public Admin(){
        Train t1 = new Train("Train1", "station1", "station2", 500.00, 5);
        TrainSystem.trainList.put(t1.getTrainNumber(), t1);

        Train t2 = new Train("Train2", "station2", "station3", 500.00, 1);
        TrainSystem.trainList.put(t2.getTrainNumber(), t2);

        Train t3 = new Train("Train3", "station4", "station5", 500.00, 5);
        TrainSystem.trainList.put(t3.getTrainNumber(), t3);
    }


    public void addTrain() {
        Train t = new Train();
        Scanner scanner;
        scanner = new Scanner(System.in);

        System.out.print("Enter Train Name: ");
        t.setTrainName(scanner.next());

        System.out.print("Enter Train's Destination city: ");
        t.setDestinationCity(scanner.next());


        System.out.print("Enter Train's Departure city: ");
        t.setDestinationCity(scanner.next());


        System.out.print("Enter Train's Fare: ");
        t.setTrainFare(scanner.nextDouble());


        System.out.print("Enter Train's Seat Capacity: ");
        t.setTotalNumberOfSeats(scanner.nextInt());
        t.setAvailableSeats();


        TrainSystem.trainList.put(t.getTrainNumber(), t);
        System.out.println("New train successfully added to the list!");
    }


    public void updateTrain() {

        if(TrainSystem.trainList.isEmpty()){
            System.out.println("sorry list is empty");
            return;
        }

        int trainNo;
        Scanner scanner;
        scanner = new Scanner(System.in);

        System.out.println("Enter the train's number u want to update");
        trainNo = scanner.nextInt();

        if (TrainSystem.trainList.containsKey(trainNo)) {
            boolean check = true;
            int option;
            Train train = TrainSystem.trainList.get(trainNo);
            do {
                System.out.println("\n-------Choose your option to update-------");
                System.out.println("1. Fare");
                System.out.println("2. Departure city");
                System.out.println("3. Destination city");
                System.out.println("4. Total Number of Seats");
                System.out.println("5. exit");
                option = scanner.nextInt();
                switch (option) {
                    case 1 -> {
                        System.out.println("Old value of train fare: " + train.getTrainFare());
                        System.out.print("Enter new train fare: ");
                        train.setTrainFare(scanner.nextDouble());
                        System.out.println("Train fare updated");
                    }
                    case 2 -> {
                        System.out.println("Old departure city: " + train.getDepartureCity());
                        System.out.print("Enter new departure city: ");
                        train.setDepartureCity(scanner.next());
                        System.out.println("Departure city updated");
                    }
                    case 3 -> {
                        System.out.println("Old destination city: " + train.getDestinationCity());
                        System.out.print("Enter new destination city: ");
                        train.setDestinationCity(scanner.next());
                        System.out.println("Destination city updated");
                    }
                    case 4 -> {
                        System.out.println("Old value of total number of seats: " + train.getTotalNumberOfSeats());
                        System.out.print("Enter new value for total number of seats: ");
                        train.setTotalNumberOfSeats(scanner.nextInt());
                        System.out.println("Total number of seats updated");
                    }
                    case 5 -> {
                        check = false;
                        System.out.println("Exited from update menu");
                    }
                    default -> System.out.println("Invalid option !!");
                }
            } while (check);
        } else {
            System.out.println("Invalid train number !!");
        }
    }


    public void removeTrain() {
        if(TrainSystem.trainList.isEmpty()){
            System.out.println("list is already empty");
            return;
        }

        int trainNo;
        Scanner scanner;
        scanner = new Scanner(System.in);
        System.out.println("Enter the train's number you want to remove");
        trainNo = scanner.nextInt();
        if (TrainSystem.trainList.containsKey(trainNo)) {
            System.out.println("---- Selected Train details ----");
            TrainSystem.trainList.get(trainNo).displayTrain();
            System.out.print("Confirm removal y/n: ");
            switch (scanner.next()){
                case "y": TrainSystem.trainList.remove(trainNo);
                    System.out.println("Successfully removed the train");
                    break;
                default:
                    System.out.println("Invalid option !!");
                case "n":
                    System.out.println("Train removal cancelled");
            }
        }
        else {
            System.out.println("Invalid train number !!");
        }
    }
}
