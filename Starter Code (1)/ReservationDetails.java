public class ReservationDetails {
    private String customer_name;
    private String credit_card_num;
    private TimePeriod rental_period;
    private boolean insurance_selected; // set to true if optional daily insurance wanted
    private String VIN;

    public ReservationDetails(String customer_name, String credit_card_num, TimePeriod rental_period,
                                boolean insurance_selected, String VIN){
            this.customer_name = customer_name;
            this.credit_card_num = credit_card_num;
            this.rental_period = rental_period;
            this.insurance_selected = insurance_selected;
            this.VIN = VIN;
    
         }
    public String getCustomerName(){
        return this.customer_name;
    }

    public String getCreditCardNum(){
        return this.credit_card_num;
    }

    public TimePeriod getRentalPeriod(){
        return this.rental_period;
    }

    public boolean getInsuranceSelected(){
        return this.insurance_selected;
    }

    public String getVIN(){
        return this.VIN;
    }

    public String toString(){
        return "Customer Name: " + getCustomerName() + ",  Credit Card #: " + getCreditCardNum() + ", Rental Period: " + getRentalPeriod() +
                ", Insurance Selected: "+getInsuranceSelected() + ", VIN: " + getVIN();
    }

     
}
