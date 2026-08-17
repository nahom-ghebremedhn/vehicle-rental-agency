public class CurrentRates {

    private VehicleRates[] rates = new VehicleRates[3];

    public CurrentRates(VehicleRates car, VehicleRates SUV, VehicleRates minivan){
        rates[0] = car;
        rates[1] = SUV;
        rates[2] = minivan;
    }

    public VehicleRates getCarRates(){
        return rates[0];
    }

     public VehicleRates getSUVRates(){
        return rates[1];
    }

     public VehicleRates getMinivanRates(){
        return rates[2];
    }

    public void setCarRates(VehicleRates r){
        rates[0] = r;
    }

    public void setSUVRates(VehicleRates r){
        rates[1] = r;
    }

    public void setMinivanRates(VehicleRates r){ 
        rates[2] = r;
    }

    public double calcEstimatedCost(int vehicleType, TimePeriod estimatedRentalPeriod, int estimatedNumMiles, 
                                        boolean dailyInsur){                                
        
            // Select the correct VehicleRates object
            VehicleRates vehicleRate = rates[vehicleType];

            double baseCost = 0;

             // Determine which rate applies (day/week/month)

             char unit = estimatedRentalPeriod.getUnit();
             int quantity = estimatedRentalPeriod.getQuantity();

             if(unit == 'd') {
                baseCost = vehicleRate.getDailyRate() * quantity;
             }

             else if (unit == 'w') {
                baseCost = vehicleRate.getWeeklyRate() * quantity;
             }

             else if (unit == 'm') {
                baseCost = vehicleRate.getMonthlyRate() * quantity;
             }

             //Milage Cost
             double milageCost = estimatedNumMiles * vehicleRate.getMileageChrg();


             // Optional daily insurance
                double insuranceCost = 0;
                
                if (dailyInsur && unit == 'd') {
                insuranceCost = vehicleRate.getDailyInsurRate() * quantity;
                }
                else if (dailyInsur && unit == 'w'){
                insuranceCost = vehicleRate.getDailyInsurRate() * (quantity * 7);
                }
                else if (dailyInsur && unit == 'm'){
                insuranceCost = vehicleRate.getDailyInsurRate() * (quantity * 30);
                }

            return baseCost + milageCost + insuranceCost;


    }

    public double calcActualCost(VehicleRates rates, int num_days_used, int NumMilesDriven, 
                                        boolean dailyInsur){  
                          
                // Rental Period Cost
            double rentalCost = num_days_used * rates.getDailyRate();

                // Miles Driven 
            double milageCost = NumMilesDriven * rates.getMileageChrg();

            double insuranceCost = 0;
                // Daily insurace if selected
            if (dailyInsur){
                insuranceCost = num_days_used * rates.getDailyInsurRate();
            }

         return rentalCost + milageCost + insuranceCost;

    }

}
