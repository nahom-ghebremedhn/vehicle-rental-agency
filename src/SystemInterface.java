public class SystemInterface {

	private static CurrentRates agency_rates;
	private static Vehicles agency_vehicles;
	private static Transactions transactions_history;

	// used to init static variables (in place of a constructor)
	public static void initSystem(CurrentRates r, Vehicles v, Transactions t) {
		agency_rates = r;
		agency_vehicles = v;
		transactions_history = t;
	}
	
	// used to check if SystemInterface initialized
	public static boolean initialized() {
		return agency_rates != null;
	}
	
	// Note that methods updateXXXRates, makeReservation and cancelReservation return an
	// acknowledgement of successful completion of the requested action (e.g. “Vehicle ABC123
	// successfully reserved”). Method processReturnedVehicle returns the final cost for the returned 
	// vehicle (e.g., “Total charge for VIN ABC123 for 3 days, 233 miles @  0.15/mile and daily
	// insurance @ 14.95/day = $xxx.xx.)

	// Current Rates Related Methods
	public static String[ ] getCarRates() {
		return new String [] {
			"Current Car Rates:",
			agency_rates.getCarRates().toString()
		};

	 }
	public static String[ ] getSUVRates() {
		 return new String[]{
            "Current SUV Rates:",
            agency_rates.getSUVRates().toString()
        };
	 }
	public static String[ ] getMinivanRates() {
		return new String[]{
            "Current Minivan Rates:",
            agency_rates.getMinivanRates().toString()
        };
	 }

	public static String[ ] updateCarRates(VehicleRates r) {
			agency_rates.setCarRates(r);
        	return new String[]{
            "Car rates updated successfully."
        };
	 }
	public static String[ ] updateSUVRates(VehicleRates r) {
			agency_rates.setSUVRates(r);
        	return new String[]{
            "SUV rates updated successfully."
        };
	 }
	public static String[ ] updateMinivanRates(VehicleRates r) {
			agency_rates.setMinivanRates(r);
            return new String[]{
            "Minivan rates updated successfully."
        };
	 }

    public static String[ ] calcRentalCost(RentalDetails details) {
		int type = -1;
		if (details.getVehicleType().equalsIgnoreCase("car")){
			type = 0;
		}
		else if (details.getVehicleType().equalsIgnoreCase("suv")){
			type = 1;
		}
		else if (details.getVehicleType().equalsIgnoreCase("minivan")){
			type = 2;
		}
			 double cost = agency_rates.calcEstimatedCost(type,
            	details.getRentalPeriod(),
                details.getNumMiles(),
                details.getInsuranceSelected());

			return new String[]{
            "Estimated rental cost:","$" + String.format("%.2f", cost)
        };
	 }
	public static String[ ] processReturnedVehicle(String vin, int num_days_used, int num_miles_driven) {

		Vehicle v = agency_vehicles.getVehicle(vin);

		if (v == null) {
            return new String[]{"No vehicle found with VIN: " + vin};
        }

        if (!v.IsReserved()) {
            return new String[]{"Vehicle " + vin + " is not currently reserved."};
        }

        // cost uses the vehicle’s stored quoted rates + insurance option
        double final_cost = agency_rates.calcActualCost(
                v.getQuotedRates(),
                num_days_used,
                num_miles_driven,
                v.getReservation().getInsuranceSelected());

				
        // prepare transaction
        Transaction t = new Transaction(
            v.getReservation().getCreditCardNum(),
            v.getReservation().getCustomerName(),
            v.getClass().getSimpleName(),
            "" + num_days_used + " days",
            "" + num_miles_driven + " miles",
            "$" + String.format("%.2f", final_cost)
        );

        transactions_history.add(t);

        // cancel reservation
        try {
            v.cancelReservation();
        } catch (UnreservedVehicleException e) {
            // should never happen here
        }

        return new String[]{
            "Return processed successfully.",
            "Total cost for VIN " + vin + ": $" + String.format("%.2f", final_cost)
        };



	 }

	// Note that the rates to be used are retrieved from the VehicleRates object stored in the specific rented
	// vehicle object, and the daily insurance option is retrieved from the Reservation object of the rented
	// vehicle

	// Vehicle Related Methods
	public static String[ ] getAvailCars() {
		return getAvailableByType("Car");
	 }
	public static String[ ] getAvailSUVs() {
		return getAvailableByType("SUV");
	 }
	public static String[ ] getAvailMinivans() {
		return getAvailableByType("Minivan");
	 }

	 private static String[] getAvailableByType(String typeName) {
        agency_vehicles.reset();

        java.util.ArrayList<String> list = new java.util.ArrayList<>();

        while (agency_vehicles.hasNext()) {
            Vehicle v = agency_vehicles.getNext();
            if (!v.IsReserved() && v.getClass().getSimpleName().equalsIgnoreCase(typeName)) {
                list.add(v.toString());
            }
        }

        if (list.isEmpty()) {
            return new String[]{"No available " + typeName + "s."};
        }

        return list.toArray(new String[0]);
    }

	public static String[ ] getAllVehicles() {
		agency_vehicles.reset();

        java.util.ArrayList<String> list = new java.util.ArrayList<>();

        while (agency_vehicles.hasNext()) {
            list.add(agency_vehicles.getNext().toString());
        }

        if (list.isEmpty()) {
            return new String[]{"No vehicles in the system."};
        }

        return list.toArray(new String[0]);
    }

	public static String[ ] makeReservation(ReservationDetails details) {
		Vehicle v = agency_vehicles.getVehicle(details.getVIN());

        if (v == null) {
            return new String[]{"No vehicle found with VIN: " + details.getVIN()};
        }

        try {
            // attach reservation
            v.setReservation(details);

            // attach quoted rates depending on type
            VehicleRates rates = null;
            if (v instanceof Car) rates = new CarRates(agency_rates.getCarRates());
            else if (v instanceof SUV) rates = new SUVRates(agency_rates.getSUVRates());
            else if (v instanceof Minivan) rates = new MinivanRates(agency_rates.getMinivanRates());

            v.setQuotedRates(rates);

            return new String[]{
                "Reservation successful.",
                "Vehicle " + v.getVIN() + " reserved for " + details.getCustomerName()
            };

        } catch (ReservedVehicleException e) {
            return new String[]{e.getMessage()};
        }
    }

	public static String[ ] cancelReservation(String vin) {
		Vehicle v = agency_vehicles.getVehicle(vin);

        if (v == null) {
            return new String[]{"No vehicle found with VIN: " + vin};
        }

        try {
            v.cancelReservation();
            return new String[]{
                "Reservation cancelled successfully for VIN: " + vin
            };
        } catch (UnreservedVehicleException e) {
            return new String[]{e.getMessage()};
        }
    }
	public static String[ ] getReservation(String vin) {
		Vehicle v = agency_vehicles.getVehicle(vin);

        if (v == null) {
            return new String[]{"No vehicle found with VIN: " + vin};
        }
        if (!v.IsReserved()) {
            return new String[]{"Vehicle " + vin + " is not reserved."};
        }

        return new String[]{
            "Reservation details:",
            v.getReservation().toString()
        };
    }
	
	public static String[ ] getAllReservations() {
		agency_vehicles.reset();
        java.util.ArrayList<String> list = new java.util.ArrayList<>();

        while (agency_vehicles.hasNext()) {
            Vehicle v = agency_vehicles.getNext();
            if (v.IsReserved()) {
                list.add(v.getReservation().toString());
            }
        }

        if (list.isEmpty()) {
            return new String[]{"No active reservations."};
        }

        return list.toArray(new String[0]);
    }


	// transactions-related methods
	public static String[ ] addTransaction() {
		return new String[]{
            "Transactions can only be added through processReturnedVehicle()."
        };
	}  
	public static String[ ] getAllTransactions() {
		transactions_history.reset();
        java.util.ArrayList<String> list = new java.util.ArrayList<>();

        while (transactions_history.hasNext()) {
            list.add(transactions_history.getNext().toString());
        }

        if (list.isEmpty()) {
            return new String[]{"No transactions recorded."};
        }

        return list.toArray(new String[0]);
	}

}