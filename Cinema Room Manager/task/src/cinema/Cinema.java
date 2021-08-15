package cinema;

import java.util.Scanner;

public class Cinema {

    public static boolean[][] initCinema(Scanner scanner) {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();
        System.out.println();
        return new boolean[rows][seatsInRow];
    }

    public static void printMenu(){
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        System.out.println();
    }

    public static void printSeats(boolean[][] cinema) {
        System.out.println("Cinema:");
        for (int i = 0; i <= cinema[0].length; i++) {
            System.out.print(((i == 0) ? " " : String.valueOf(i)) + " ");
        }
        System.out.println();
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                if (j == 0) {
                    System.out.print((i + 1) + " ");
                }
                System.out.print(cinema[i][j] ? "B" : "S");
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void buyTicket(boolean[][] cinema, Scanner scanner) {
        int rows = cinema.length;
        int seatsInRow = cinema[0].length;
        int seatRow = -1;
        int seatNumber = -1;
        while (seatRow == -1 || seatNumber == -1) {
            System.out.println("Enter a row number:");
            seatRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();
            if (seatRow <= rows && seatRow > 0 && seatNumber <= seatsInRow && seatNumber > 0) {
                if (cinema[seatRow - 1][seatNumber - 1]) {
                    System.out.println("That ticket has already been purchased!");
                    seatNumber = -1;
                }
            } else {
                System.out.println("Wrong input!");
                seatNumber = -1;
            }
        }

        System.out.printf("Ticket price: $%d%n", calculatePriceTicket(cinema, seatRow));
        System.out.println();
        cinema[seatRow - 1][seatNumber - 1] = true;
    }

    public static void printStatistics(boolean[][] cinema) {
        int tickets = 0;
        int currentIncome = 0;
        int totalIncome = 0;
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                if (cinema[i][j]) {
                    tickets++;
                    currentIncome += calculatePriceTicket(cinema, i+1);
                }
                totalIncome += calculatePriceTicket(cinema, i+1);
            }
        }
        double percentage = ((double) tickets * 100) / (cinema.length * cinema[0].length);
        System.out.printf("Number of purchased tickets: %d%n", tickets);
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
        System.out.println();

    }

    public static int calculatePriceTicket(boolean cinema[][], int row) {
        int rows = cinema.length;
        int seatsInRow = cinema[0].length;
        int seats = rows * seatsInRow;
        int firstHalfRows = rows / 2;
        int ticketPrice = 10;
        if (seats >= 60 && (firstHalfRows < row)) {
            ticketPrice = 8;
        }
        return ticketPrice ;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean[][] cinema = initCinema(scanner);
        int input = -1;
        while (input != 0) {
            printMenu();
            input = scanner.nextInt();
            switch (input) {
                case 1:
                    printSeats(cinema);
                    break;
                case 2:
                    buyTicket(cinema, scanner);
                    break;
                case 3:
                    printStatistics(cinema);
                    break;
                default:
                    break;
            }
        }
    }
}