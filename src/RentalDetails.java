public class RentalDetails {

    private String vehicle_type;
    private TimePeriod rental_period;
    private int num_miles_driven;
    private boolean insurance_selected;

    public RentalDetails(String vehicle_type, TimePeriod rental_period, int num_miles_driven,  boolean insurance_selected){
            this.vehicle_type = vehicle_type;
            this.rental_period = rental_period;
            this.num_miles_driven = num_miles_driven;
            this.insurance_selected = insurance_selected;
    }

    public String getVehicleType(){
        return this.vehicle_type;
    }

    public TimePeriod getRentalPeriod(){
        return this.rental_period;
    }

    public int getNumMiles(){
        return this.num_miles_driven;
    }

    public boolean getInsuranceSelected(){
        return this.insurance_selected;
    }

    public String toString(){
        return "Vehicle Type: " + getVehicleType() + ", Rental Period: " + getRentalPeriod() + ",  Miles Driven: " + getNumMiles() + 
                ", Insurance Selected: "+getInsuranceSelected();
    }
}
