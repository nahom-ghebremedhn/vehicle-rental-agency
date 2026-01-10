public class VehicleRates {
     
    private double daily_rate;
    private double weekly_rate;
    private double monthly_rate;
    private double per_mile_charge;
    private double daily_insurance;

    public VehicleRates(double daily_rate, double weekly_rate, double monthly_rate, 
                        double per_mile_charge,  double daily_insurance){
            this.daily_rate = daily_rate;
            this.weekly_rate = weekly_rate;
            this.monthly_rate = monthly_rate;
            this.per_mile_charge = per_mile_charge;
            this.daily_insurance = daily_insurance;
                

        }
        // Copy Constractor

    public VehicleRates(VehicleRates other){
            this.daily_rate = other.daily_rate;
            this.weekly_rate = other.weekly_rate;
            this.monthly_rate = other.monthly_rate;
            this.per_mile_charge = other.per_mile_charge;
            this.daily_insurance = other.daily_insurance;
    }

    public double getDailyRate(){ // cost per day
        return this.daily_rate;
    }
    public double getWeeklyRate(){  // cost per week
        return this.weekly_rate;
    } 
    public double getMonthlyRate(){  // cost per month
        return this.monthly_rate;
    } 
    public double getMileageChrg() {  // cost per mile, based on vehicle type
        return this.per_mile_charge;
    }
    public double getDailyInsurRate(){ // insurance cost (per day)
        return this.daily_insurance;
    } 
    public String toString(){
        
        return "Rates:\n" +
               "Daily: $" + getDailyRate() +
               ", Weekly: $" + getWeeklyRate() +
               ", Monthly: $" + getMonthlyRate() +
               ", Per Mile: $" + getMileageChrg() +
               ", Daily Insurance: $" + getDailyInsurRate();
    }

}