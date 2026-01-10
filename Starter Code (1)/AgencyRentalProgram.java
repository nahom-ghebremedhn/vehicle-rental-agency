import java.util.Scanner;

public class AgencyRentalProgram {

public static void main(String[ ] args) {

	// Provide Hard-coded Current Agency Rates
	CurrentRates agency_rates = new CurrentRates(new VehicleRates(24.95, 169.95, 514.95, 0.16, 14.95),  // cars
                                                 new VehicleRates(29.95, 189.95, 679.95, 0.16, 14.95),  // SUVs
                                                 new VehicleRates(36.95, 224.95,687.95, 0.21, 19.95)); //minivans

	// Create an Initially Empty Vehicles Collection, and Populate
	Vehicles agency_vehicles = new Vehicles();
	populate(agency_vehicles);    // supporting private static method (to be added)

	// Create Initially Empty Transactions Object
	Transactions transactions = new Transactions();



	// Establish User Interface
	Scanner input = new Scanner(System.in);
	UserInterface ui;
	boolean quit = false;

	// Create Requested UI and Begin Execution 
	while(!quit) {  // (allows switching between Employee and Manager user interfaces while running)

		ui = getUI(input);

		if(ui == null)
			quit = true;
		else {
			// Init System Interface with Agency Data (if not already initialized)
			if(!SystemInterface.initialized())
			      SystemInterface.initSystem(agency_rates, agency_vehicles, transactions);

			// Start User Interface
			ui.start(input);
		}
	}
}

public static UserInterface getUI(Scanner input) {
	boolean valid_selection = false;
	UserInterface ui = null;
	int selection;

	while(!valid_selection) {
		System.out.print("1 - Employee, 2 - Manager, 3 - quit: ");

		selection = input.nextInt();
		if(selection == 1) {
			return new EmployeeGUI_Interface();
			
		}
		else if(selection == 2) {
			return new ManagerUI_Interface();
		}
		else
		if(selection == 3) {
			return null;
		}
		else
			System.out.println("Invalid selection - please reenter");
	}
	return ui;
}

	private static void populate(Vehicles v) {
		v.addVehicle(new Car("Toyota Prius", 57, 4, "AED456"));
		v.addVehicle(new Car("Honda Insight", 55 , 4, "DEF123"));
		v.addVehicle(new Car("Hyundai Elantra Hybrid", 53, 4, "JHK857"));
		v.addVehicle(new SUV("Toyota RAV4 Hybrid", 37, 39, "DPF450"));
		v.addVehicle(new SUV("Ford Explorer Hybrid", 31, 37, "WCH302"));
		v.addVehicle(new SUV("Honda Pilot Hybrid", 36, 31, "KSB698"));
		v.addVehicle(new SUV("Lexus NX 450h", 37, 25, "GEK334"));
		v.addVehicle(new Minivan("Toyota Sienna", 36, 75, "AGH890"));
		v.addVehicle(new Minivan("Chrysler Pacifica Hybrid:", 82, 140, "BFJ386"));
		v.addVehicle(new Minivan("Honda Odyssey", 22, 67, "KCM341"));
		v.addVehicle(new Minivan("Kia Carnival", 22, 78, "TSH580"));
	}

}