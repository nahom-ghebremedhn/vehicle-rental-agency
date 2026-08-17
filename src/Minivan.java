public class Minivan extends Vehicle {

    private int storage;
    
     public Minivan(String description, int mpg, int storage, String VIN){
        super(description, mpg, VIN);
        this.storage = storage;
    }

    public int getStorage(){
        return this.storage;
    }

      @Override
    public String toString() {
    return getDescription() +"(Minivan)"+ "  MPG: " + getMPG() + "  Cargo Storage: "+ getStorage()+ "  VIN: " + getVIN();
}
}
