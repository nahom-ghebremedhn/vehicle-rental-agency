public class Car extends Vehicle {
    
    private int seating;

    public Car(String description, int mpg,int seating, String VIN){
        super(description, mpg, VIN);
        this.seating = seating;
    }

    public int getSeating(){
        return this.seating;
    }

        @Override
    public String toString(){
        return getDescription()+ "(Car)" + "  MPG: " + getMPG() + "  Seating: "+getSeating()+ "  VIN: " + getVIN();
    }
}
