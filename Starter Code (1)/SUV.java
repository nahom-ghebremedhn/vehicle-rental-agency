public class SUV extends Vehicle {

    private int CargoStorage;

     public SUV (String description, int mpg, int CargoStorage, String VIN){
        super(description, mpg, VIN);
        this.CargoStorage = CargoStorage;
    }

    public int getStorage(){
        return this.CargoStorage;
    }
        @Override
    public String toString() {
    return getDescription()+"(SUV)" + "  MPG: " + getMPG() + "  Cargo Storage: "+ getStorage()+ "  VIN: " + getVIN();
}
}
