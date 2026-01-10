public class MinivanRates extends VehicleRates {

    public MinivanRates(double daily_rate, double weekly_rate, double monthly_rate, 
                        double per_mile_charge,  double daily_insurance){
            super(daily_rate,weekly_rate, monthly_rate,  per_mile_charge,daily_insurance );
    }

    public MinivanRates(VehicleRates other) {
    super(other);
}

    
    
}