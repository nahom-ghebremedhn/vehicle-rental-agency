public class TimePeriod {
    private char unit;
    private int quantity;

    public TimePeriod(char unit, int quantity){
        this.unit = unit;
        this.quantity = quantity;
    }

    public char getUnit(){
        return this.unit;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public String toString(){
        return "Unit: "+ getUnit()+", "+ "Quantity: "+ getQuantity();
    }
}

    
