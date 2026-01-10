import java.util.Scanner;

public class EmployeeGUI_Interface implements UserInterface {
	
	// No constructor needed, calls static methods of the SystemInterface.
	// Method start begins a command loop that repeatedly: (a) displays a menu of options, 
      // (b) gets the selected option from the user, and (c) executes the corresponding command.

	private boolean quit = false;
 	public void start(Scanner input) {

	int selection;

	// command loop
	while(!quit) {
		displayMenu();
		selection = getSelection(input);
		execute(selection, input);
	}
      }
	
     // ------- private methods

      private void execute(int selection, Scanner input) {

        int veh_type;
		String vin, creditcard_num;  
 		String[] display_lines;
		RentalDetails rental_details;  
		ReservationDetails reserv_details;

	switch(selection) {

		// display rental rates
		case 1: veh_type = getVehicleType(input);
				switch(veh_type) {
				    case 1: display_lines = SystemInterface.getCarRates(); break;
				    case 2: display_lines = SystemInterface.getSUVRates(); break;
			      	case 3: display_lines = SystemInterface.getMinivanRates(); break;
					default: display_lines = new String[]{"Invalid vehicle type."};
				}
				displayResults(display_lines);
				break;

		// display available vehicles
		case 2:	veh_type = getVehicleType(input);
				switch(veh_type) {
				    case 1: display_lines = SystemInterface.getAvailCars(); break;
				    case 2: display_lines = SystemInterface.getAvailSUVs(); break;
			      	case 3: display_lines = SystemInterface.getAvailMinivans(); break;
					default: display_lines = new String[]{"Invalid vehicle type."};
				}
				displayResults(display_lines);
				break;
		// display estimated rental cost
		case 3:	rental_details = getRentalDetails(input);
				display_lines = SystemInterface.calcRentalCost(rental_details);
				displayResults(display_lines);
				break;
		 		
		// make a reservation
		case 4:	reserv_details = getReservationDetails(input);
				display_lines = SystemInterface.makeReservation(reserv_details);
				displayResults(display_lines);
				break;

			// display a reservation
		case 5: vin = getVIN(input);
				display_lines = SystemInterface.getReservation(vin);
				displayResults(display_lines);
				break;

				// cancel a reservation
		case 6:	vin = getVIN(input);
				display_lines = SystemInterface.cancelReservation(vin);
				displayResults(display_lines);
				break;

		// process returned vehicle
		case 7:	creditcard_num = getCreditCardNum(input);
				vin = getVIN(input);
				System.out.print("Enter number of days used: ");
				int num_days = input.nextInt();
				System.out.print("Enter number of miles driven: ");
				int miles = input.nextInt();
				display_lines = SystemInterface.processReturnedVehicle(vin, 
 								       num_days, miles);
				displayResults(display_lines);
				break;

		// quit program
		case 8: quit = true;
				}			
	}

	private void displayMenu() {   
		// displays the user options
		System.out.println("======== EMPLOYEE MENU ==========");
		System.out.println("1. Display Rental Rates.");
		System.out.println("2. Display Available Vehicles.");
		System.out.println("3. Display Estimated Rental Cost.");
		System.out.println("4. Make a Reservation.");
		System.out.println("5. Display a reservation");
		System.out.println("6. Cancel a Reservation.");
		System.out.println("7. Process Returned Vehicles.");
		System.out.println("8. Quit.");


	}

	private int getSelection(Scanner input) {  
		// prompts user for selection from menu (continues to prompt if selection < 1 or selection > 8)

		int sel;
			System.out.println("Please Enter a Number from the Menu");
			sel = input.nextInt();
			input.nextLine();
		 	while (sel < 1 || sel > 8) {
				System.out.print("Inavlid choice. Try again");
				sel = input.nextInt();
				input.nextLine();
			}

		return sel;
	 }


	private String getVIN(Scanner input){
	// prompts user to enter VIN for a given vehicle (does not do any error checking on the input) {    }
		System.out.print("Enter VIN: ");
		return input.nextLine();
	}

	private int getVehicleType(Scanner input){
	// prompts user to enter 1, 2, or 3, and returns (continues to prompt user if invalid input given) {    }
		System.out.println("Enter a number 1 = Car, 2 = SUV, or 3 = Minivan ");
		int type = input.nextInt();
		while (type < 1 || type > 3){
			System.out.println("Enter a number 1, 2, or 3 ");
			type = input.nextInt();
			input.nextLine();
		}
		return type;
	}

	private String getCreditCardNum(Scanner input) {
		System.out.print("Enter credit card number: ");
		return input.nextLine();
		}

	private RentalDetails getRentalDetails(Scanner input){
	// prompts user to enter required information for an estimated rental cost (vehicle type, estimated  
 	// number of miles expected to be driven, expected rental period and optional insuranc, returning the
 	// result packaged as a RentalDetails object (to pass in method calls to the SystemInterface) {   }
		System.out.println("What type of vehicle are you looking for?(Car/SUV/Minivan): ");
		String vechType = input.next();
		input.nextLine();

		System.out.println("Enter estimated miles to drive: ");
		int miles = input.nextInt();
		input.nextLine();

		System.out.println("What is the expected rental period?(d(days)/w(week)/m(month)): ");
		char unit = input.next().charAt(0);
		input.nextLine();

		System.out.println("Enter quantity: ");
		int qty = input.nextInt();
		input.nextLine();

		System.out.println("Insurance?(true/false): ");
		boolean ins = input.nextBoolean();
		input.nextLine();

		TimePeriod tp = new TimePeriod(unit, qty);
		//String vtype = (type == 1 ? "Car" : type == 2 ? "SUV" : "Minivan");

		RentalDetails details = new RentalDetails(vechType, tp, miles, ins);
		return details;

	}
	private ReservationDetails getReservationDetails(Scanner input) {
	// prompts user to enter required information for making a reservation (VIN of vehicle to reserve, 
 	// credit card num, rental period, and optional insurance), returning the result packaged as a 
 	// ReservationDetails object (to pass in method calls to the SystemInterface)  {    }
			
		System.out.print("Enter Name: ");
		String name = input.nextLine();
		
		System.out.print("Enter VIN to reserve: ");
		String vin = input.nextLine();


		System.out.print("Enter credit card number: ");
		String card = input.nextLine();


		System.out.print("Enter rental period unit (d/w/m): ");
		char unit = input.next().charAt(0);
		input.nextLine();


		System.out.print("Enter quantity: ");
		int qty = input.nextInt();
		input.nextLine();


		System.out.print("Insurance? (true/false): ");
		boolean ins = input.nextBoolean();
		input.nextLine();


		TimePeriod tp = new TimePeriod(unit, qty);

		
		return new ReservationDetails(name, card, tp, ins, vin);

	}

	private void displayResults(String[] lines){
	// displays the array of strings passed, one string per screen line {    }
		System.out.println("\n----Results----");

		for (int i = 0; i < lines.length; i++) {
			System.out.println(lines[i]);
		}

		System.out.println("----------------\n");
	}
}

