import java.util.Scanner;

public class ManagerUI_Interface implements UserInterface {

    private boolean quit = false;

    @Override
    public void start(Scanner input) {
        int selection;

        while (!quit) {
            displayMenu();
            selection = getSelection(input);
            execute(selection, input);
        }
    }

    // -----------------------------------------------------
    // PRIVATE METHODS
    // -----------------------------------------------------

    private void displayMenu() {
        System.out.println("\n=========== MANAGER MENU ===========");
        System.out.println("1. Display current rates");
        System.out.println("2. Update current rates");
        System.out.println("3. Display all vehicles");
        System.out.println("4. Display all reservations");
        System.out.println("5. Display all transactions");
        System.out.println("6. Quit");
        System.out.println("====================================");
    }

    private int getSelection(Scanner input) {
        System.out.print("Enter choice (1 - 6): ");
        int choice = input.nextInt();
        input.nextLine(); // clear buffer

        while (choice < 1 || choice > 6) {
            System.out.print("Invalid choice. Enter 1 - 6: ");
            choice = input.nextInt();
            input.nextLine();
        }
        return choice;
    }

    private void execute(int selection, Scanner input) {
        int type;
        String[] results;

        switch (selection) {

            // (1) Display current rates
            case 1:
                type = getVehicleType(input);
                if (type == 1) results = SystemInterface.getCarRates();
                else if (type == 2) results = SystemInterface.getSUVRates();
                else results = SystemInterface.getMinivanRates();

                displayResults(results);
                break;

            // (2) Update rates
            case 2:
                type = getVehicleType(input);

                VehicleRates newRates = getRateInput(input);

                if (type == 1) results = SystemInterface.updateCarRates(newRates);
                else if (type == 2) results = SystemInterface.updateSUVRates(newRates);
                else results = SystemInterface.updateMinivanRates(newRates);

                displayResults(results);
                break;

            // (3) Display all vehicles
            case 3:
                results = SystemInterface.getAllVehicles();
                displayResults(results);
                break;

            // (4) Display all reservations
            case 4:
                results = SystemInterface.getAllReservations();
                displayResults(results);
                break;

            // (5) Display all transactions
            case 5:
                results = SystemInterface.getAllTransactions();
                displayResults(results);
                break;

            // (6) Quit
            case 6:
                quit = true;
                break;
        }
    }

    // -----------------------------------------------------
    // INPUT HELPERS
    // -----------------------------------------------------

    private int getVehicleType(Scanner input) {
        System.out.println("\nSelect vehicle type:");
        System.out.println("1. Car");
        System.out.println("2. SUV");
        System.out.println("3. Minivan");
        System.out.print("Enter choice (1 - 3): ");

        int type = input.nextInt();
        input.nextLine();

        while (type < 1 || type > 3) {
            System.out.print("Invalid. Enter 1–3: ");
            type = input.nextInt();
            input.nextLine();
        }
        return type;
    }

    // Asks manager for new rate numbers
    private VehicleRates getRateInput(Scanner input) {
        System.out.println("\nEnter new rate values:");

        System.out.print("Daily rate: ");
        double daily = input.nextDouble();

        System.out.print("Weekly rate: ");
        double weekly = input.nextDouble();

        System.out.print("Monthly rate: ");
        double monthly = input.nextDouble();

        System.out.print("Per-mile charge: ");
        double perMile = input.nextDouble();

        System.out.print("Daily insurance rate: ");
        double ins = input.nextDouble();
        input.nextLine();

        return new CarRates(daily, weekly, monthly, perMile, ins);
        // NOTE: CarRates/SUVRates/MinivanRates all share same constructor pattern.
        // We only need one here because updateCarRates() etc. don't care about subtype.
    }

    private void displayResults(String[] lines) {
        System.out.println("\n----------- RESULTS -----------");
        for (int i = 0; i < lines.length; i++) {
            System.out.println(lines[i]);
        }
        System.out.println("--------------------------------\n");
    }
}
