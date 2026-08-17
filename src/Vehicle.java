public abstract class Vehicle {

    private String description;
    private int mpg;
    private String vin;
    private ReservationDetails resv;
    private VehicleRates rates;

    public Vehicle(String description, int mpg, String vin){
        this.description = description;
        this.mpg = mpg;
        this.vin = vin;
        this.resv = null;
        this.rates = null;
    }

    public String getDescription(){
        return description;
    }

    public int getMPG(){
        return mpg;
    }

    public String getVIN(){
        return vin;
    }

    public ReservationDetails getReservation(){
        return resv;
    }
    
    public VehicleRates getQuotedRates() {
        return rates;
    }

    public boolean IsReserved(){
        return this.resv != null;
    }

    public void setReservation(ReservationDetails r) throws ReservedVehicleException {
        if(IsReserved()){
            throw new ReservedVehicleException ("Vehicle is Already Reserved.");
        }
         this.resv = r;

    }

    public void setQuotedRates(VehicleRates cost){
        this.rates = cost;
    }

    public void cancelReservation() throws UnreservedVehicleException {
        if(!IsReserved()){
            throw new UnreservedVehicleException("Vehicle is not currently reserved.");
        }
        // cancel reservation
        this.resv = null;
        this.rates = null;
    }


    public abstract String toString();
}