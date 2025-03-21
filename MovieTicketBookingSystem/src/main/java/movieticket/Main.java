package movieticket;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Book Ticket");
            System.out.println("4. View Available Movies");
            System.out.println("5. Add Theater");
          //  System.out.println("6. View Theaters");
         //   System.out.println("7. Add Show");
            System.out.println("6. View Shows");
            System.out.println("7. Generate Booking Report");
            System.out.println("8. Generate Payment Report");
            System.out.println("9. Generate Loyalty Report");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    UserService.registerUser();
                    break;
                case 2:
                    int userId = UserService.loginUser();
                    if (userId != -1) {
                        System.out.println("Logged in as User ID: " + userId);
                    }
                    break;
                case 3:
                    BookingService.bookTicket();
                    break;
                case 4:
                    MovieService.showAvailableMovies();
                    break;
                case 5:
                    TheaterService.addTheater();
                    break;
               // case 6:

                //    break;
                //case 7:
                //    ShowService.addShow();
                //    break;
                case 6:
                    ShowService.viewShows();
                    break;
                case 7:
                    ReportService.generateBookingReport();
                    break;
                case 8:
                    ReportService.generatePaymentReport();
                    break;
                case 9:
                    ReportService.generateLoyaltyReport();
                    break;
                case 10:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
